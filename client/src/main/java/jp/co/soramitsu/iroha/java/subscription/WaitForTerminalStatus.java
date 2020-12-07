package jp.co.soramitsu.iroha.java.subscription;

import io.reactivex.Observable;
import iroha.protocol.Endpoint.ToriiResponse;
import iroha.protocol.Endpoint.TxStatus;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.ThreadSafe;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import jp.co.soramitsu.iroha.java.routers.TxStatusRouter;
import lombok.AllArgsConstructor;
import lombok.val;


/**
 * This is an implementation of a transaction status subscription strategy, when subscriber does
 * re-subscription unless it receives terminal status from Iroha or Error.
 *
 * If Iroha status stream ends with onComplete AND terminal status has not yet been received,
 * subscriber immediately resubscribes.
 *
 * When terminal status is received, RPC call is not cancelled and this may leak resources if Iroha
 * does not close stream after the terminal status.
 */
@AllArgsConstructor
@ThreadSafe
public class WaitForTerminalStatus implements SubscriptionStrategy {

  private List<TxStatus> terminal;

  public WaitForTerminalStatus() {
    this(Arrays.asList(
        TxStatus.STATELESS_VALIDATION_FAILED,
        TxStatus.COMMITTED,
        TxStatus.MST_EXPIRED,
        TxStatus.NOT_RECEIVED,
        TxStatus.REJECTED,
        TxStatus.UNRECOGNIZED
    ));
  }

  @Override
  public Observable<ToriiResponse> subscribe(IrohaAPI api, byte[] txhash) {
    val router = new TxStatusRouter();
    val terminated = new AtomicBoolean(false);

    terminal.forEach(status -> router.handle(status, (tr) -> terminated.set(true)));

    return api.txStatus(txhash)
        .doOnNext(router::process)
        .repeatUntil(terminated::get)
        .takeUntil((ToriiResponse tr) -> terminal.contains(tr.getTxStatus()));
  }
}
