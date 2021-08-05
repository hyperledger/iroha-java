package jp.co.soramitsu.iroha2.model;

import java.util.Map;
import lombok.Data;
import lombok.NonNull;

@Data
public class Metadata {

  @NonNull
  private Map<String, Value> map;
}
