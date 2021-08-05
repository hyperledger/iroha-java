package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAccountsByDomainName;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAccountsByDomainNameReader implements ScaleReader<FindAccountsByDomainName> {

  private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

  @Override
  public FindAccountsByDomainName read(ScaleCodecReader reader) {
    return new FindAccountsByDomainName(reader.read(EXPRESSION_READER));
  }
}
