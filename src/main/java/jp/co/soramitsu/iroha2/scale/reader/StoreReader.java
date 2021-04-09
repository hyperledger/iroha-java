package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.Store;

public class StoreReader implements ScaleReader<Store> {
    @Override
    public Store read(ScaleCodecReader scaleCodecReader) {
        return new Store();
    }
}
