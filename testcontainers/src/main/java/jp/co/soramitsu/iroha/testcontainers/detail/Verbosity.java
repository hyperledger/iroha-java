package jp.co.soramitsu.iroha.testcontainers.detail;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Verbosity {
  TRACE("trace"),
  DEBUG("debug"),
  INFO("info"),
  WARN("warn"),
  ERROR("error"),
  CRITICAL("critical");

  @JsonValue
  private final String level;

  public static final String CONFIG_FILE = "config_file";
}
