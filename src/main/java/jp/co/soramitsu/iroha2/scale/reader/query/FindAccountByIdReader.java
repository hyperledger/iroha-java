package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAccountById;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAccountByIdReader implements ScaleReader<FindAccountById> {

  private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

  @Override
  public FindAccountById read(ScaleCodecReader reader) {
    return new FindAccountById(reader.read(EXPRESSION_READER));
  }
}
