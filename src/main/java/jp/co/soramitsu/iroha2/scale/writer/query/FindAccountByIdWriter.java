package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAccountById;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;


/**
 * Scale writer that writes nothing.
 */
class FindAccountByIdWriter implements ScaleWriter<FindAccountById> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, FindAccountById value) throws IOException {
        writer.write(EXPRESSION_WRITER, value.getId());
    }
}
