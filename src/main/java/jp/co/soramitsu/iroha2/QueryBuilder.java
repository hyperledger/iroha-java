package jp.co.soramitsu.iroha2;

import static jp.co.soramitsu.iroha2.Utils.currentTimestamp;

import jp.co.soramitsu.iroha2.model.AccountId;
import jp.co.soramitsu.iroha2.model.AssetId;
import jp.co.soramitsu.iroha2.model.DefinitionId;
import jp.co.soramitsu.iroha2.model.Id;
import jp.co.soramitsu.iroha2.model.Value;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.query.FindAssetQuantityById;
import jp.co.soramitsu.iroha2.model.query.Query;
import jp.co.soramitsu.iroha2.model.query.SignedQueryRequest;

public class QueryBuilder {

  private final SignedQueryRequest query = new SignedQueryRequest();

  public QueryBuilder() {
    query.setTimestamp(currentTimestamp());
  }

  public QueryBuilder setTimestamp(long timestamp) {
    query.setTimestamp(currentTimestamp());
    return this;
  }

  public UnsignedQuery setQuery(Query query) {
    this.query.setQuery(query);
    return new UnsignedQuery(this.query);
  }

  public UnsignedQuery findAssetQuantityById(String assetName, String assetDomain,
      String accountName,
      String accountDomain) {
    AccountId accountId = new AccountId(accountName, accountDomain);
    DefinitionId definitionId = new DefinitionId(assetName, assetDomain);
    AssetId assetId = new AssetId(definitionId, accountId);
    FindAssetQuantityById request = new FindAssetQuantityById(new Raw(new Value(new Id(assetId))));
    query.setQuery(request);
    return new UnsignedQuery(this.query);
  }

}
