package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.expression.Expression;
import jp.co.soramitsu.iroha2.scale.writer.EnumerationUnionValue;

public class ExpressionWriter implements ScaleWriter<Expression> {

  private static final UnionWriter<Expression> EXPRESSION_WRITER = new UnionWriter<>(
      new AddWriter(), // 0 - Add
      new SubtractWriter(), // 1 - Subtract
      new MultiplyWriter(), // 2 - Multiply
      new DivideWriter(), // 3 - Divide
      new ModWriter(), // 4 - Mod
      new RaiseToWriter(), // 5 - RaiseTo
      new GreaterWriter(), // 6 - Greater
      new LessWriter(), // 7 - Less
      new EqualWriter(), // 8 - Equal
      new NotWriter(), // 9 - Not
      new AndWriter(), // 10 - And
      new OrWriter(), // 11 - Or
      new IfWriter(), // 12 - If
      new RawWriter(), // 13 - Raw
      new QueryWriter(), // 14 - Query
      new ContainsWriter(), // 15 - Contains
      new ContainsAllWriter(), // 16 - ContainsAll
      new ContainsAnyWriter(), // 17 - ContainsAny
      new WhereWriter(), // 18 - Where
      new ContextValueWriter() // 19 - ContextValue
  );

  @Override
  public void write(ScaleCodecWriter writer, Expression value) throws IOException {
    writer.write(EXPRESSION_WRITER, new EnumerationUnionValue<>(value));
  }

}
