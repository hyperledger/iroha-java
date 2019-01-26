package jp.co.soramitsu.iroha.java.detail;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import iroha.protocol.Endpoint.ToriiResponse;
import jp.co.soramitsu.iroha.java.TransactionStatusObserver;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

/**
 * Builder, which can be used to build custom observers from lambdas.
 *
 * {@code
 *
 * <pre>
 * TransactionStatusBuilder.builder()
 *  .onTransactionSent(() -> handleSent())
 *  .onTransactionFailed((t) -> handleFailure(t))
 *  .onTransactionCommit((t) -> handleCommit(t))
 *  .build();
 * </pre>
 * }
 */
@NoArgsConstructor
@AllArgsConstructor
public class InlineTransactionStatusObserver extends TransactionStatusObserver {
  private Action onTransactionSent = Functions.EMPTY_ACTION;
  private Action onComplete = Functions.EMPTY_ACTION;
  private Consumer<? super ToriiResponse> onTransactionFailed = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onTransactionCommitted = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onStatelessValidationSuccess = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onStatefulValidationSuccess = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onNotReceived = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onUnrecognizedStatus = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onMstExpired = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onRejected = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onEnoughSignaturesCollected = Functions.emptyConsumer();
  private Consumer<? super ToriiResponse> onMstPending = Functions.emptyConsumer();
  private Consumer<? super Throwable> onError = Functions.emptyConsumer();

  public static InlineTransactionStatusObserverBuilder builder() {
    return new InlineTransactionStatusObserverBuilder();
  }

  @Override
  public void onTransactionSent() {
    try {
      this.onTransactionSent.run();
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onTransactionFailed(ToriiResponse t) {
    try {
      this.onTransactionFailed.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onStatelessValidationSuccess(ToriiResponse t) {
    try {
      this.onStatelessValidationSuccess.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onStatefulValidationSuccess(ToriiResponse t) {
    try {
      this.onStatefulValidationSuccess.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onTransactionCommitted(ToriiResponse t) {
    try {
      this.onTransactionCommitted.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onNotReceived(ToriiResponse t) {
    try {
      this.onNotReceived.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onMstExpired(ToriiResponse t) {
    try {
      this.onMstExpired.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onUnrecognizedStatus(ToriiResponse t) {
    try {
      this.onUnrecognizedStatus.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onMstPending(ToriiResponse t) {
    try {
      this.onMstPending.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onRejected(ToriiResponse t) {
    try {
      this.onRejected.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onEnoughSignaturesCollected(ToriiResponse t) {
    try {
      this.onEnoughSignaturesCollected.accept(t);
    } catch (Exception e) {
      onError(e);
    }
  }

  @Override
  public void onError(Throwable t) {
    try {
      this.onError.accept(t);
    } catch (Exception e) {
      Exceptions.propagate(e);
    }
  }

  @Override
  public void onComplete() {
    try {
      this.onComplete.run();
    } catch (Exception e) {
      onError(e);
    }
  }

  public static class InlineTransactionStatusObserverBuilder {

    private Action onTransactionSent = Functions.EMPTY_ACTION;
    private Action onComplete = Functions.EMPTY_ACTION;
    private Consumer<? super ToriiResponse> onTransactionFailed = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onTransactionCommitted = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onStatelessValidationSuccess = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onStatefulValidationSuccess = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onNotReceived = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onUnrecognizedStatus = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onMstExpired = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onRejected = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onEnoughSignaturesCollected = Functions.emptyConsumer();
    private Consumer<? super ToriiResponse> onMstPending = Functions.emptyConsumer();
    private Consumer<? super Throwable> onError = Functions.emptyConsumer();

    InlineTransactionStatusObserverBuilder() {
    }

    public InlineTransactionStatusObserverBuilder onTransactionSent(
        Action onTransactionSent) {
      this.onTransactionSent = onTransactionSent;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onComplete(
        Action onComplete) {
      this.onComplete = onComplete;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onTransactionFailed(
        Consumer<? super ToriiResponse> onTransactionFailed) {
      this.onTransactionFailed = onTransactionFailed;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onTransactionCommitted(
        Consumer<? super ToriiResponse> onTransactionCommitted) {
      this.onTransactionCommitted = onTransactionCommitted;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onStatelessValidationSuccess(
        Consumer<? super ToriiResponse> onStatelessValidationSuccess) {
      this.onStatelessValidationSuccess = onStatelessValidationSuccess;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onStatefulValidationSuccess(
        Consumer<? super ToriiResponse> onStatefulValidationSuccess) {
      this.onStatefulValidationSuccess = onStatefulValidationSuccess;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onNotReceived(
        Consumer<? super ToriiResponse> onNotReceived) {
      this.onNotReceived = onNotReceived;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onUnrecognizedStatus(
        Consumer<? super ToriiResponse> onUnrecognizedStatus) {
      this.onUnrecognizedStatus = onUnrecognizedStatus;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onMstExpired(
        Consumer<? super ToriiResponse> onMstExpired) {
      this.onMstExpired = onMstExpired;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onRejected(
        Consumer<? super ToriiResponse> onRejected) {
      this.onRejected = onRejected;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onEnoughSignaturesCollected(
        Consumer<? super ToriiResponse> onEnoughSignaturesCollected) {
      this.onEnoughSignaturesCollected = onEnoughSignaturesCollected;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onMstPending(
        Consumer<? super ToriiResponse> onMstPending) {
      this.onMstPending = onMstPending;
      return this;
    }

    public InlineTransactionStatusObserverBuilder onError(
        Consumer<? super Throwable> onError) {
      this.onError = onError;
      return this;
    }

    public InlineTransactionStatusObserver build() {
      return new InlineTransactionStatusObserver(onTransactionSent, onComplete, onTransactionFailed,
          onTransactionCommitted, onStatelessValidationSuccess, onStatefulValidationSuccess,
          onNotReceived, onUnrecognizedStatus, onMstExpired, onRejected,
          onEnoughSignaturesCollected,
          onMstPending, onError);
    }
  }
}
