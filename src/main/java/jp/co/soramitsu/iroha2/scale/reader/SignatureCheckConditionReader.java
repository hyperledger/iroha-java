package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.SignatureCheckCondition;

public class SignatureCheckConditionReader implements ScaleReader<SignatureCheckCondition> {

    @Override
    public SignatureCheckCondition read(ScaleCodecReader scaleCodecReader) {
        return new SignatureCheckCondition(scaleCodecReader.read(new ExpressionReader()));
    }
}
