package jp.co.soramitsu.iroha.testcontainers.detail;

import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LoggerConfig {

  @Builder.Default
  private Verbosity level = Verbosity.INFO;

  private LoggerPattern patterns;
  
  private Map<String, LoggerConfig> children;

}
