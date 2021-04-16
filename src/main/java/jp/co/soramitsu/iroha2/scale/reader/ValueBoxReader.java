package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import jp.co.soramitsu.iroha2.model.ValueBox;

public class ValueBoxReader implements ScaleReader<ValueBox> {

    private static final UnionReader<ValueBox> VALUE_READER = new UnionReader<>(
            new U32Reader(), // 0 - U32
            new BoolReader(), // 1 - Bool
            new StringValueReader(), // 2 - String
            new VectorReader(), // 3 - Vec<Value>
            new IdReader(), // 4 - IdBox
            new IdentifiableReader(), // 5 - Identifiable
            new PublicKeyReader(), // 6 - Public Key
            new ParameterReader(), // 7 - Parameter
            new SignatureCheckConditionReader() // 8 - Parameter
    );

    @Override
    public ValueBox read(ScaleCodecReader reader) {
        return VALUE_READER.read(reader).getValue();
    }

}
