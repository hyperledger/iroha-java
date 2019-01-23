package jp.co.soramitsu.iroha.java.routers;

import iroha.protocol.Endpoint.ToriiResponse;
import iroha.protocol.Endpoint.TxStatus;
import jp.co.soramitsu.iroha.java.detail.router.Router;

public class TxStatusRouter extends Router<ToriiResponse, TxStatus> {

  public TxStatusRouter() {
    super(ToriiResponse::getTxStatus);
  }
}
