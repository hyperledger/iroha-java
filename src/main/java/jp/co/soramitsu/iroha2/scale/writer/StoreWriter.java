package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.AssetValue;
import jp.co.soramitsu.iroha2.model.Store;

import java.io.IOException;

public class StoreWriter implements ScaleWriter<Store>  {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, Store store) throws IOException {
        scaleCodecWriter.write(new MetadataWriter(), store.getMetadata());
    }
}
