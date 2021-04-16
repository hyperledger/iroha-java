package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetById;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetByIdReader implements ScaleReader<FindAssetById> {

  private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

  @Override
  public FindAssetById read(ScaleCodecReader reader) {
    return new FindAssetById(reader.read(EXPRESSION_READER));
  }
}
