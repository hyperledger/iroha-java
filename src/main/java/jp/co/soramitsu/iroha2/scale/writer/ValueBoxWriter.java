package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.ValueBox;

public class ValueBoxWriter implements ScaleWriter<ValueBox> {

  private static final UnionWriter<ValueBox> VALUE_WRITER = new UnionWriter<ValueBox>(
      new U32Writer(), // 0 - U32
      new BoolWriter(), // 1 - Bool
      new StringValueWriter(), // 2 - String
      new VectorWriter(), // 3 - Vec
      new IdWriter(), // 4 - Id
      new IdentifiableWriter(), // 5 - Identifiable
      new PublicKeyWriter(), // 5 - PublicKey
      new ParameterWriter(), // 6 - Parameter
      new SignatureCheckConditionWriter(), // 7 - SignatureCheckCondition
      new TransactionValueWriter() // 8 - TransactionValue
  );

  @Override
  public void write(ScaleCodecWriter writer, ValueBox value) throws IOException {
    writer.write(VALUE_WRITER, new EnumerationUnionValue<>(value));
  }

}
