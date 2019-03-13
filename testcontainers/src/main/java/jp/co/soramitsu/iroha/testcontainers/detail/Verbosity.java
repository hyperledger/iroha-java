package jp.co.soramitsu.iroha.testcontainers.detail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Verbosity {
  TRACE(0),
  DEBUG(1),
  INFO(2),
  WARN(3),
  ERROR(4),
  CRITICAL(5),
  OFF(6);

  private final int level;
}
