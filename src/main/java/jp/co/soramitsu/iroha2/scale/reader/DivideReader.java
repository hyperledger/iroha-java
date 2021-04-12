package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.expression.Divide;

public class DivideReader implements ScaleReader<Divide> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public Divide read(ScaleCodecReader reader) {
        return new Divide(reader.read(EXPRESSION_READER), reader.read(EXPRESSION_READER));
    }
}
