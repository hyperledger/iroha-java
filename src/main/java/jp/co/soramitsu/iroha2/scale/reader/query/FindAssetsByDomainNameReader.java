package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainName;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByDomainNameReader implements ScaleReader<FindAssetsByDomainName> {

  private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

  @Override
  public FindAssetsByDomainName read(ScaleCodecReader reader) {
    return new FindAssetsByDomainName(reader.read(EXPRESSION_READER));
  }
}
