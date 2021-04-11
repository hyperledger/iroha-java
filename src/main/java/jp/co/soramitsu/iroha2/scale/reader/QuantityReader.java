package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.Quantity;

public class QuantityReader implements ScaleReader<Quantity> {
    @Override
    public Quantity read(ScaleCodecReader scaleCodecReader) {
        return new Quantity(scaleCodecReader.read(new U32Reader()));
    }
}
