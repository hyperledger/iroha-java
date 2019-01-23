package jp.co.soramitsu.iroha.java.timeout;

public class OneSecondTimeout implements TimeoutStrategy {

  @Override
  public boolean nextTimeout() throws InterruptedException {
    Thread.sleep(1000);
    return true;
  }
}
