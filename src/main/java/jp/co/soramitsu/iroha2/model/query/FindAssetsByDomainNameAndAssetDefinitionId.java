package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAssetsByDomainNameAndAssetDefinitionId implements Query {

  @NonNull
  private Expression domainName;
  @NonNull
  private Expression assetDefinitionId;

  @Override
  public int getIndex() {
    return 13;
  }
}
