package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import jp.co.soramitsu.iroha2.model.AssetValue;

import java.io.IOException;

public class AssetValueWriter implements ScaleWriter<AssetValue> {

    private static final UnionWriter<AssetValue> VALUE_WRITER = new UnionWriter<AssetValue>(
            new QuantityWriter(), // 0
            new BigQuantityWriter(), // 1
            new StoreWriter() // 2
    );

    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, AssetValue valueType) throws IOException {
        scaleCodecWriter.write(VALUE_WRITER, new EnumerationUnionValue<>(valueType));
    }
}
