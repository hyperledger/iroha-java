package jp.co.soramitsu.iroha.java;

public class ErrorResponseException extends RuntimeException {

  public ErrorResponseException(int errorCode, String reason, String message) {
    super("Error code: " + errorCode + ". Reason: " + reason + ". Message: " + message);
  }
}
