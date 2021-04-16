package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.CompactBigIntWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.U128;

public class U128WriterCompact implements ScaleWriter<U128> {

  @Override
  public void write(ScaleCodecWriter writer, U128 value) throws IOException {
    writer.write(new CompactBigIntWriter(), value.getValue());
  }
}
