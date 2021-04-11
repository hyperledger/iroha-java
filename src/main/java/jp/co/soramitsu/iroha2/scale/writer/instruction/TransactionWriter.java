package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.ListWriter;
import jp.co.soramitsu.iroha2.model.Signature;
import jp.co.soramitsu.iroha2.model.Transaction;
import jp.co.soramitsu.iroha2.scale.writer.SignatureWriter;

import java.io.IOException;

public class TransactionWriter implements ScaleWriter<Transaction> {

  private static final PayloadWriter PAYLOAD_WRITER = new PayloadWriter();
  private static final ListWriter<Signature> SIGNATURES_WRITER = new ListWriter<>(
      new SignatureWriter());

  @Override
  public void write(ScaleCodecWriter writer, Transaction value) throws IOException {
    writer.write(PAYLOAD_WRITER, value.getPayload());
    writer.write(SIGNATURES_WRITER, value.getSignatures());
  }
}
