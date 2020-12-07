package jp.co.soramitsu.iroha.java.debug;

import iroha.protocol.Endpoint.ToriiResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import jp.co.soramitsu.iroha.java.TransactionStatusObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestTransactionStatusObserver extends TransactionStatusObserver {

  private RuntimeException fail(String format, Object... args) {
    return new RuntimeException(String.format(format, args));
  }

  private AtomicInteger sent = new AtomicInteger(0);
  private AtomicInteger committed = new AtomicInteger(0);
  private AtomicInteger failed = new AtomicInteger(0);
  private AtomicBoolean completed = new AtomicBoolean(false);
  private Throwable errored;

  public TestTransactionStatusObserver assertNTransactionsSent(int n) {
    if (sent.get() != n) {
      throw fail("assertNTransactionsSent: sent %d, expected %d", sent.get(), n);
    }

    return this;
  }

  public TestTransactionStatusObserver assertNTransactionsCommitted(int n) {
    if (committed.get() != n) {
      throw fail("assertNTransactionsCommitted: committed %d, expected %d", committed.get(), n);
    }

    return this;
  }

  public TestTransactionStatusObserver assertNTransactionsFailed(int n) {
    if (failed.get() != n) {
      throw fail("assertNTransactionsFailed: failed %d, expected %d", failed.get(), n);
    }

    return this;
  }

  public TestTransactionStatusObserver assertNoTransactionFailed() {
    return assertNTransactionsFailed(0);
  }

  public TestTransactionStatusObserver assertNoTransactionCommitted() {
    if (sent.get() == 0) {
      throw fail("No transactions have been sent");
    }
    return assertNTransactionsCommitted(0);
  }

  public TestTransactionStatusObserver assertAllTransactionsFailed() {
    if (sent.get() == 0) {
      throw fail("No transactions have been sent");
    }
    return assertNTransactionsFailed(sent.get());
  }

  public TestTransactionStatusObserver assertAllTransactionsCommitted() {
    if (sent.get() == 0) {
      throw fail("No transactions have been sent");
    }
    return assertNTransactionsCommitted(sent.get());
  }

  public TestTransactionStatusObserver assertComplete() {
    if (!completed.get()) {
      throw fail("Observable is not completed");
    }
    return this;
  }

  public TestTransactionStatusObserver assertError() {
    if (errored == null) {
      throw fail("Expected error, got noerr");
    }
    return this;
  }

  public TestTransactionStatusObserver assertNoErrors() {
    if (errored != null) {
      throw fail("Expected no errors, got %s", errored);
    }
    return this;
  }

  public <T> TestTransactionStatusObserver assertError(Class<T> e) {
    if (errored.getClass().isInstance(e)) {
      throw fail("Errored with %s, expected with %s", errored.getClass().toString(),
          e.toString());
    }
    return this;
  }

  @Override
  public String toString() {
    return "TestTransactionStatusObserver{" +
        "sent=" + sent +
        ", committed=" + committed +
        ", failed=" + failed +
        ", completed=" + completed +
        ", errored=" + errored +
        '}';
  }

  @Override
  public void onTransactionSent() {
    log.info("[onTransactionSent]");
    sent.incrementAndGet();
  }

  @Override
  public void onTransactionFailed(ToriiResponse t) {
    log.info("[onTransactionFailed] " + t.toString());
    failed.incrementAndGet();
  }

  @Override
  public void onRejected(ToriiResponse t) {
    log.info("[onRejected] " + t.toString());
    failed.incrementAndGet();
  }

  @Override
  public void onTransactionCommitted(ToriiResponse t) {
    log.error("[onNext] " + t.toString());
    committed.incrementAndGet();
  }

  @Override
  public void onError(Throwable e) {
    log.info("[onError] " + e.toString());
    errored = e;
  }

  @Override
  public void onComplete() {
    log.info("[onComplete]");
    completed.set(true);
  }
}
