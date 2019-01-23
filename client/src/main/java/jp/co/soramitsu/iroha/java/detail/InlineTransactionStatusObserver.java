package jp.co.soramitsu.iroha.java.detail;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import iroha.protocol.Endpoint.ToriiResponse;
import jp.co.soramitsu.iroha.java.TransactionStatusObserver;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InlineTransactionStatusObserver extends TransactionStatusObserver {

  @Default
  private Action onTransactionSent = Functions.EMPTY_ACTION;

  @Default
  private Action onComplete = Functions.EMPTY_ACTION;

  @Default
  private Consumer<? super ToriiResponse> onTransactionFailed = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onTransactionCommitted = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onStatelessValidationSuccess = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onStatefulValidationSuccess = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onNotReceived = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onUnrecognizedStatus = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onMstExpired = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onRejected = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onEnoughSignaturesCollected = Functions.emptyConsumer();

  @Default
  private Consumer<? super ToriiResponse> onMstPending = Functions.emptyConsumer();

  @Default
  private Consumer<? super Throwable> onError = Functions.emptyConsumer();

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
}
