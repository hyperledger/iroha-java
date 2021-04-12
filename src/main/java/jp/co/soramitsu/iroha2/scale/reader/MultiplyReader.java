package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.expression.Multiply;

public class MultiplyReader implements ScaleReader<Multiply> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public Multiply read(ScaleCodecReader reader) {
        return new Multiply(reader.read(EXPRESSION_READER), reader.read(EXPRESSION_READER));
    }
}
