package jp.co.soramitsu.iroha.java;

import iroha.protocol.QryResponses.ErrorResponse;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ErrorResponseException extends RuntimeException {

  @NonNull
  private ErrorResponse errorResponse;

  public ErrorResponseException(ErrorResponse errorResponse) {
    super("Error code: " + errorResponse.getErrorCode() + ". Reason: " + errorResponse.getReason()
        + ". Message: " + errorResponse.getMessage());
    this.errorResponse = errorResponse;
  }
}
