package jp.co.soramitsu.iroha.java.subscription;

import io.reactivex.Observable;
import iroha.protocol.Endpoint.ToriiResponse;
import jp.co.soramitsu.iroha.java.IrohaAPI;

public interface SubscriptionStrategy {

  Observable<ToriiResponse> subscribe(IrohaAPI api, byte[] txhash);
}
