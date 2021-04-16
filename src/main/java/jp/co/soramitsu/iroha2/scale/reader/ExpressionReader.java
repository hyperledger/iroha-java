package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import jp.co.soramitsu.iroha2.model.expression.Expression;

public class ExpressionReader implements ScaleReader<Expression> {

  private static final UnionReader<Expression> EXPRESSION_UNION_READER = new UnionReader<>(
      new AddReader(), // 0 - Add
      new SubtractReader(), // 1 - Subtract
      new MultiplyReader(), // 2 - Multiply
      new DivideReader(), // 3 - Divide
      new ModReader(), // 4 - Mod
      new RaiseToReader(), // 5 - RaiseTo
      new GreaterReader(), // 6 - Greater
      new LessReader(), // 7 - Less
      new EqualReader(), // 8 - Equal
      new NotReader(), // 9 - Not
      new AndReader(), // 10 - And
      new OrReader(), // 11 - Or
      new IfReader(), // 12 - If
      new RawReader(), // 13 - Raw
      new QueryReader(), // 14 - Query
      new ContainsReader(), // 15 - Contains
      new ContainsAllReader(), // 16 - ContainsAll
      new ContainsAnyReader(), // 17 - ContainsAny
      new WhereReader(), // 18 - Where
      new ContextValueReader() // 19 - ContextValue
  );

  @Override
  public Expression read(ScaleCodecReader reader) {
    return reader.read(EXPRESSION_UNION_READER).getValue();
  }

}

