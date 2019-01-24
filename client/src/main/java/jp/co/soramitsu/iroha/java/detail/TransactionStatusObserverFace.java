package jp.co.soramitsu.iroha.java.detail;

import iroha.protocol.Endpoint.ToriiResponse;

public interface TransactionStatusObserverFace {

  /**
   * Transaction has been successfully sent.
   */
  default void onTransactionSent() {
  }

  /**
   * Transaction is invalid. Statelessly or statefully.
   *
   * @param t defails, why this transaction is invalid
   */
  default void onTransactionFailed(ToriiResponse t) {
  }

  /**
   * Transaction passed stateless validation.
   */
  default void onStatelessValidationSuccess(ToriiResponse t) {
  }

  /**
   * Transaction passed stateful validation.
   */
  default void onStatefulValidationSuccess(ToriiResponse t) {
  }

  /**
   * Transaction has been committed. This means that transaction is written in the blockchain.
   */
  default void onTransactionCommitted(ToriiResponse t) {
  }

  /**
   * Iroha answers with NotReceived status only if transaction with given hash is unknown to this
   * peer.
   */
  default void onNotReceived(ToriiResponse t) {
  }

  /**
   * MultiSignatureTransaction failed to pass validastion.
   */
  default void onMstExpired(ToriiResponse t) {
  }

  /**
   * If you get this status, most likely this is Iroha bug.
   */
  default void onUnrecognizedStatus(ToriiResponse t) {
  }

  /**
   * Multisignature transaction awaits for more signatures.
   */
  default void onMstPending(ToriiResponse t) {
  }

  default void onRejected(ToriiResponse t) {
  }

  /**
   * Multisignature trasaction has enough signatures.
   */
  default void onEnoughSignaturesCollected(ToriiResponse t) {
  }
}
