package jp.co.soramitsu.iroha.testcontainers.detail;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ToriiTlsConfig implements Cloneable {

  @Builder.Default
  @NonNull
  private int port = 55552;

  @Builder.Default
  @NonNull
  private String key_pair_path = "/opt/iroha_data/server";

  @Override
  public ToriiTlsConfig clone() {
    try {
      return new DeepCloner<>(ToriiTlsConfig.class).clone(this);
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
  }
}
