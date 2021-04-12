package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.expression.RaiseTo;

public class RaiseToReader implements ScaleReader<RaiseTo> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public RaiseTo read(ScaleCodecReader reader) {
        return new RaiseTo(reader.read(EXPRESSION_READER), reader.read(EXPRESSION_READER));
    }
}
