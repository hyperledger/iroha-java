package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.TransactionValue;

import java.io.IOException;

public class TransactionValueWriter implements ScaleWriter<TransactionValue> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, TransactionValue transactionValue) throws IOException {
        throw new RuntimeException();
    }
}
