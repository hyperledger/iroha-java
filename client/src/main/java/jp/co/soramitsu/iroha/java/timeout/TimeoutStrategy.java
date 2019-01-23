package jp.co.soramitsu.iroha.java.timeout;

public interface TimeoutStrategy {

  /**
   * Execute next timeout.
   *
   * @return true, if you want to continue re-subscription loop, false otherwise
   * @throws InterruptedException when subscriber thread is killed during timeout
   */
  boolean nextTimeout() throws InterruptedException;
}
