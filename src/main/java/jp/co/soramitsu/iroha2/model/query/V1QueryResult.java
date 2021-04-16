package jp.co.soramitsu.iroha2.model.query;

import lombok.Data;
import lombok.NonNull;

@Data
public class V1QueryResult implements VersionedQueryResult {

  @NonNull
  private QueryResult queryResult;

  @Override
  public int getIndex() {
    return 1;
  }
}
