package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountId;
import jp.co.soramitsu.iroha2.scale.writer.AccountIdWriter;

import java.io.IOException;

class FindAssetByAccountIdWriter implements ScaleWriter<FindAssetsByAccountId> {

  private static final AccountIdWriter ACCOUNT_ID_WRITER = new AccountIdWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAssetsByAccountId value) throws IOException {
    writer.write(ACCOUNT_ID_WRITER, value.getAccountId());
  }
}
