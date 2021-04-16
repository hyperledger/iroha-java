package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByName;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByNameReader implements ScaleReader<FindAssetsByName> {

  private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

  @Override
  public FindAssetsByName read(ScaleCodecReader reader) {
    return new FindAssetsByName(reader.read(EXPRESSION_READER));
  }

}
