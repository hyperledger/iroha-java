package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.AccountId;

import java.io.IOException;

/**
 * Scale writer that writes Accunt id.
 */
public class AccountIdWriter implements ScaleWriter<AccountId> {

    @Override
    public void write(ScaleCodecWriter writer, AccountId value) throws IOException {
        writer.writeAsList(value.getName().getBytes());
        writer.writeAsList(value.getDomainName().getBytes());
    }
}
