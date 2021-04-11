package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.ListWriter;
import jp.co.soramitsu.iroha2.model.NewAccount;
import jp.co.soramitsu.iroha2.model.PublicKey;

import java.io.IOException;

/**
 * Scale writer that writes nothing.
 */
public class NewAccountWriter implements ScaleWriter<NewAccount> {

    private static final AccountIdWriter ACCOUNT_ID_WRITER = new AccountIdWriter();
    private static final ListWriter<PublicKey> PUBLIC_KEY_LIST_WRITER = new ListWriter<>(
            new PublicKeyWriter());
    private static final MetadataWriter METADATA_WRITER = new MetadataWriter();

    @Override
    public void write(ScaleCodecWriter writer, NewAccount value) throws IOException {
        ACCOUNT_ID_WRITER.write(writer, value.getId());
        PUBLIC_KEY_LIST_WRITER.write(writer, value.getSignatories());
        METADATA_WRITER.write(writer, value.getMetadata());
    }
}
