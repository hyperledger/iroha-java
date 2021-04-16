package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.*;

import java.io.IOException;

public class DomainWriter implements ScaleWriter<Domain> {

    private static final MapWriter<AccountId, Account> ACCOUNTS_MAP_WRITER = new MapWriter<>(
            new AccountIdWriter(),
            new AccountWriter());
    private static final MapWriter<DefinitionId, AssetDefinitionEntry> ASSET_DEFINITION_MAP_WRITER = new MapWriter<>(
            new DefinitionIdWriter(),
            new AssetDefinitionEntryWriter());

    @Override
    public void write(ScaleCodecWriter writer, Domain value) throws IOException {
        writer.writeAsList(value.getName().getBytes());
        ACCOUNTS_MAP_WRITER.write(writer, value.getAccounts());
        ASSET_DEFINITION_MAP_WRITER.write(writer, value.getAssetDefinitions());
    }

}
