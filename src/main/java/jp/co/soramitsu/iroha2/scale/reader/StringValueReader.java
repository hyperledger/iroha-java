package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.StringValue;

public class StringValueReader implements ScaleReader<StringValue> {
    @Override
    public StringValue read(ScaleCodecReader scaleCodecReader) {
        return new StringValue(scaleCodecReader.read(new StringReader()));
    }
}
