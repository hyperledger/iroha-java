package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.BigQuantity;
import jp.co.soramitsu.iroha2.model.Quantity;

public class BigQuantityReader implements ScaleReader<BigQuantity> {
    @Override
    public BigQuantity read(ScaleCodecReader scaleCodecReader) {
        return new BigQuantity(scaleCodecReader.read(new U128Reader()));
    }
}
