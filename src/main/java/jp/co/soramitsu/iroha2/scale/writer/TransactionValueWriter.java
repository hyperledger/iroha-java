package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.TransactionValue;

public class TransactionValueWriter implements ScaleWriter<TransactionValue> {

  @Override
  public void write(ScaleCodecWriter scaleCodecWriter, TransactionValue transactionValue)
      throws IOException {
    throw new RuntimeException();
  }
}
