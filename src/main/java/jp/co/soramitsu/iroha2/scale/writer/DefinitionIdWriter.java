package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.DefinitionId;

import java.io.IOException;

public class DefinitionIdWriter implements ScaleWriter<DefinitionId> {

    @Override
    public void write(ScaleCodecWriter writer, DefinitionId value) throws IOException {
        writer.writeAsList(value.getName().getBytes());
        writer.writeAsList(value.getDomainName().getBytes());
    }
}
