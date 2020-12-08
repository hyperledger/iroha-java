package jp.co.soramitsu.iroha.testcontainers.detail;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IrohaConfig implements Cloneable {

  // this one must match config name passed to iroha entrypoint.sh
  public static final String defaultConfigFileName = "config.docker";

  @Builder.Default
  @NonNull
  private String block_store_path = "/blocks";

  @Builder.Default
  private int torii_port = 50051;

  @Builder.Default
  private ToriiTlsConfig torii_tls_params = null;

  @Builder.Default
  private int internal_port = 10001;

  @Builder.Default
  @NonNull
  private PostgresConfig pg_opt = PostgresConfig.builder().build();

  @Builder.Default
  private int max_proposal_size = 1000;

  @Builder.Default
  private int proposal_delay = 1000;

  @Builder.Default
  private int vote_delay = 1000;

  @Builder.Default
  private int load_delay = 1000;

  @Builder.Default
  private boolean mst_enable = false;

  @Builder.Default
  private int mst_expiration_time = 99999;

  @Builder.Default
  private int max_rounds_delay = 3000;

  @Builder.Default
  private int stale_stream_max_rounds = 2;
  
  private LoggerConfig log;

  @Override
  public IrohaConfig clone() {
    try {
      return new DeepCloner<>(IrohaConfig.class).clone(this);
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
  }
}
