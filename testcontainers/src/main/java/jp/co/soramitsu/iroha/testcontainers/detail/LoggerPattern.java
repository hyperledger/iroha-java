package jp.co.soramitsu.iroha.testcontainers.detail;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LoggerPattern {

  private String trace;
  private String debug;
  private String info;
  private String warn;
  private String error;
  private String critical;

}
