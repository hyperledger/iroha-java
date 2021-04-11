package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jdk.jshell.spi.ExecutionControl;
import jp.co.soramitsu.iroha2.model.SignatureCheckCondition;

import java.io.IOException;

public class SignatureCheckConditionWriter implements ScaleWriter<SignatureCheckCondition> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, SignatureCheckCondition signatureCheckCondition) throws IOException {
        throw new RuntimeException();
    }
}
