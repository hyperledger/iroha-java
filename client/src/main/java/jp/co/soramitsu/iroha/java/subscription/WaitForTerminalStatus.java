package jp.co.soramitsu.iroha.java.subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import iroha.protocol.Endpoint.ToriiResponse;
import iroha.protocol.Endpoint.TxStatus;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import jp.co.soramitsu.iroha.java.routers.TxStatusRouter;
import jp.co.soramitsu.iroha.java.timeout.OneSecondTimeout;
import jp.co.soramitsu.iroha.java.timeout.TimeoutStrategy;
import lombok.NoArgsConstructor;
import lombok.val;


/**
 * This is an implementation of a transaction status subscription strategy, when subscriber does
 * re-subscription unless it receives terminal status from Iroha.
 *
 * If Iroha status stream ends with onComplete or onError AND terminal status has not yet been
 * received, subscriber executes {@link TimeoutStrategy#nextTimeout()} to possibly wait some time,
 * then re-subscribes again.
 *
 * onComplete is executed when Iroha sends terminal status only.
 *
 * When terminating status is received, RPC call is not cancelled and this may leak resources if
 * Iroha does not close stream after the terminal status.
 */
@NoArgsConstructor
public class WaitForTerminalStatus implements SubscriptionStrategy {

  private AtomicBoolean terminated = new AtomicBoolean(false);

  private TimeoutStrategy timeoutStrategy = new OneSecondTimeout();

  private Consumer<Throwable> onError = e -> {
    /* default: ignore onError from iroha status stream */
  };
  private Action onComplete = () -> {
    /* default: ignore onComplete from iroha status stream */
  };

  /**
   * Custom timeout strategy.
   */
  public WaitForTerminalStatus(TimeoutStrategy timeoutStrategy) {
    this.timeoutStrategy = timeoutStrategy;
  }

  /**
   * Executed when Iroha sends onError. You can add your listener to log errors or handle them
   * somehow.
   */
  public WaitForTerminalStatus doOnError(Consumer<Throwable> consumer) {
    this.onError = consumer;
    return this;
  }

  /**
   * Executed when Iroha sends onComplete. You can add your listener to log/handle stream
   * completions.
   */
  public WaitForTerminalStatus doOnComplete(Action action) {
    this.onComplete = action;
    return this;
  }


  /**
   * We received terminal status, push onNext result back to user, then complete observable
   */
  private Consumer<ToriiResponse> onTerminal(ObservableEmitter<ToriiResponse> o) {
    return (ToriiResponse t) -> {
      o.onNext(t); // send terminal status back to client
      o.onComplete(); // complete client observable
      terminate(); // terminate re-subscription loop
    };
  }

  private void terminate() {
    this.terminated.set(true);
  }

  private static List<TxStatus> terminal = Arrays.asList(
      TxStatus.STATELESS_VALIDATION_FAILED,
      TxStatus.STATEFUL_VALIDATION_FAILED,
      TxStatus.COMMITTED,
      TxStatus.MST_EXPIRED,
      TxStatus.NOT_RECEIVED,
      TxStatus.REJECTED,
      TxStatus.UNRECOGNIZED
  );

  @Override
  public Observable<ToriiResponse> subscribe(IrohaAPI api, byte[] txhash) {
    TxStatusRouter router = new TxStatusRouter();
    return Observable.create(o -> {
      val callback = this.onTerminal(o);

      terminal.forEach(status -> router.handle(status, callback));

      // as soon as we get non-terminal status, pass it to the caller
      router.handleDefault(o::onNext);

      // re-subscription loop
      do {
        api.txStatus(txhash)
            .subscribeOn(Schedulers.single())
            .blockingSubscribe(router::process, this.onError::accept, this.onComplete);
      } while (!terminated.get() && timeoutStrategy.nextTimeout());

    });
  }
}
