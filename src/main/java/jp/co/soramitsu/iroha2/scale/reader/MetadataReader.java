package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.Metadata;
import jp.co.soramitsu.iroha2.model.Value;

public class MetadataReader implements ScaleReader<Metadata>  {

    private static final MapReader<String, Value> METADATA_MAP_READER = new MapReader<>(
            new StringReader(),
            new ValueReader()
    );

    @Override
    public Metadata read(ScaleCodecReader scaleCodecReader) {
        return new Metadata(METADATA_MAP_READER.read(scaleCodecReader));
    }
}
