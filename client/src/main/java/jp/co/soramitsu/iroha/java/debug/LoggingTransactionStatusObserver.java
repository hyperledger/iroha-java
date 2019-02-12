package jp.co.soramitsu.iroha.java.debug;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import iroha.protocol.Endpoint.ToriiResponse;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoggingTransactionStatusObserver implements Observer<ToriiResponse> {

  private final Consumer<Object> log;

  @Override
  public void onSubscribe(Disposable d) {
    log.accept("[onSubscribe]");
  }

  @Override
  public void onNext(ToriiResponse toriiResponse) {
    log.accept("[onNext] " + toriiResponse.toString().replace("\n", "\t"));
  }

  @Override
  public void onError(Throwable e) {
    log.accept("[onError] " + e.toString().replace("\n", "\t"));
  }

  @Override
  public void onComplete() {
    log.accept("[onComplete]");

  }
}
