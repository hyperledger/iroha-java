package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import jp.co.soramitsu.iroha2.model.query.Query;
import jp.co.soramitsu.iroha2.scale.writer.EnumerationUnionValue;
import jp.co.soramitsu.iroha2.scale.writer.NopWriter;

import java.io.IOException;

public class QueryWriter implements ScaleWriter<Query> {

  private static final NopWriter<Query> NOP_WRITER = new NopWriter<>();

  /**
   * Scale writers for queries, position in list must be an id in union value.
   */
  private static UnionWriter<Query> QUERY_WRITER = new UnionWriter<>(
      NOP_WRITER, // 0 FindAllAccounts
      new FindAccountByIdWriter(), // 1
      NOP_WRITER, // 2 todo fix me, // 2
      new FindAccountsByNameWriter(), // 3
      new FindAccountsByDomainNameWriter(), // 4
      NOP_WRITER, // 5 FindAllAssets
      NOP_WRITER, // 6 FindAllAssetsDefinitions
      new FindAssetByIdWriter(), // 7
      new FindAssetsByNameWriter(), // 8
      new FindAssetByAccountIdWriter(), // 9
      new FindAssetsByAssetDefinitionIdWriter(), // 10
      new FindAssetsByDomainNameWriter(), // 11
      new FindAssetsByAccountIdAndAssetDefinitionIdWriter(), // 12
      new FindAssetsByDomainNameAndAssetDefinitionIdWriter(), // 13
      new FindAssetQuantityByIdWriter(), // 14
          NOP_WRITER, // 2 todo fix me, // 15
          NOP_WRITER, // 16 FindAllDomains
      new FindDomainByNameWriter(), // 17 FindDomainByName
      NOP_WRITER, // 18 FindAllPeers
       NOP_WRITER // 19 FindAllParameters
  );

  @Override
  public void write(ScaleCodecWriter writer, Query value) throws IOException {
    writer.write(QUERY_WRITER, new EnumerationUnionValue<>(value));
  }

}
