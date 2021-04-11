package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAccountById;
import jp.co.soramitsu.iroha2.scale.writer.AccountIdWriter;

import java.io.IOException;


/**
 * Scale writer that writes nothing.
 */
class FindAccountByIdWriter implements ScaleWriter<FindAccountById> {

  private static AccountIdWriter ID_WRITER = new AccountIdWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAccountById value) throws IOException {
    writer.write(ID_WRITER, value.getId());
  }
}
