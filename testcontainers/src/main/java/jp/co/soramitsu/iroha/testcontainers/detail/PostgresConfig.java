package jp.co.soramitsu.iroha.testcontainers.detail;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.IOException;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.val;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class PostgresConfig implements Cloneable {

  private static final Pattern pattern = Pattern
      .compile("host=(.+) port=(.+) user=(.+) password=(.+)");

  @Builder.Default
  @NonNull
  private String host = "iroha.postgres";

  @Builder.Default
  private int port = 5432;

  @Builder.Default
  @NonNull
  private String user = "postgres";

  @Builder.Default
  @NonNull
  private String password = "postgres";

  @Override
  @JsonValue
  public String toString() {
    return String
        .format("host=%s port=%d user=%s password=%s",
            host,
            port,
            user,
            password
        );
  }

  @JsonCreator
  public PostgresConfig(String str) {
    val m = pattern.matcher(str);
    if (!m.find()) {
      throw new IllegalArgumentException(str);
    }

    int i = 0;
    this.host = m.group(++i);
    this.port = Integer.parseInt(m.group(++i));
    this.user = m.group(++i);
    this.password = m.group(++i);
  }

  @Override
  public PostgresConfig clone() {
    try {
      return new DeepCloner<>(PostgresConfig.class).clone(this);
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
  }
}
