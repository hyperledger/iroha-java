package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.*;

import java.util.Map;

public class DomainReader implements ScaleReader<Domain> {

    private static final MapReader<AccountId, Account> ACCOUNTS_MAP_READER = new MapReader<>(
            new AccountIdReader(),
            new AccountReader());
    private static final MapReader<DefinitionId, AssetDefinitionEntry> ASSET_DEFINITION_MAP_READER = new MapReader<>(
            new DefinitionIdReader(),
            new AssetDefinitionEntryReader());

    @Override
    public Domain read(ScaleCodecReader reader) {
        String domain = reader.readString();
        Map<AccountId, Account> accounts = reader.read(ACCOUNTS_MAP_READER);
        Map<DefinitionId, AssetDefinitionEntry> assets = reader.read(ASSET_DEFINITION_MAP_READER);
        return new Domain(domain, accounts, assets);
    }
}
