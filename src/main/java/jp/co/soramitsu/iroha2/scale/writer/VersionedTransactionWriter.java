package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import jp.co.soramitsu.iroha2.model.VersionedTransaction;

import java.io.IOException;

public class VersionedTransactionWriter implements ScaleWriter<VersionedTransaction> {

    private static UnionWriter<VersionedTransaction> VERSIONED_TX_UNION_WRITER = new UnionWriter<>(
            new NopWriter<>(), // 0 - must not be used
            new V1TransactionWriter() // 1 - V1
    );

    @Override
    public void write(ScaleCodecWriter writer, VersionedTransaction value) throws IOException {
        writer.write(VERSIONED_TX_UNION_WRITER, new EnumerationUnionValue<>(value));
    }
}
