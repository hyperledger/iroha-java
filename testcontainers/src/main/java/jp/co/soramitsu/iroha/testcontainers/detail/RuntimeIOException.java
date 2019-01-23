package jp.co.soramitsu.iroha.testcontainers.detail;

import java.io.IOException;

public class RuntimeIOException extends RuntimeException {

  public RuntimeIOException(IOException e) {
    super(e);
  }
}
