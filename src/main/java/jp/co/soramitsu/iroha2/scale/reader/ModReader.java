package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.expression.Mod;

public class ModReader implements ScaleReader<Mod> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public Mod read(ScaleCodecReader reader) {
        return new Mod(reader.read(EXPRESSION_READER), reader.read(EXPRESSION_READER));
    }
}
