package jp.co.soramitsu.iroha.java.subscription;

import io.reactivex.Observable;
import iroha.protocol.Endpoint.ToriiResponse;
import jp.co.soramitsu.iroha.java.IrohaAPI;

/**
 * Wait until Iroha calls onComplete once. Does not resubscribe.
 */
public class WaitUntilCompleted implements SubscriptionStrategy {

  @Override
  public Observable<ToriiResponse> subscribe(IrohaAPI api, byte[] txhash) {
    return api.txStatus(txhash);
  }
}
