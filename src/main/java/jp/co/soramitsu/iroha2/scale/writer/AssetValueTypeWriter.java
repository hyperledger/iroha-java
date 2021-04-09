package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.AssetValueType;

import java.io.IOException;

public class AssetValueTypeWriter  implements ScaleWriter<AssetValueType> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, AssetValueType valueType) throws IOException {
        scaleCodecWriter.write(new NopWriter<>(), new EnumerationUnionValue<>(valueType));
    }
}
