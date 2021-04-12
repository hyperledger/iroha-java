package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.V1Transaction;
import jp.co.soramitsu.iroha2.scale.writer.instruction.TransactionWriter;

import java.io.IOException;

public class V1TransactionWriter implements ScaleWriter<V1Transaction> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, V1Transaction v1) throws IOException {
        scaleCodecWriter.write(new TransactionWriter(), v1.getTransaction());
    }
}
