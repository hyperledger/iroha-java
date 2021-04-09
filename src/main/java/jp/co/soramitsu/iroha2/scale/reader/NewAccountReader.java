package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import jp.co.soramitsu.iroha2.model.Asset;
import jp.co.soramitsu.iroha2.model.AssetId;
import jp.co.soramitsu.iroha2.model.NewAccount;
import jp.co.soramitsu.iroha2.model.PublicKey;

public class NewAccountReader implements ScaleReader<NewAccount> {

    private static final AccountIdReader ACCOUNT_ID_READER = new AccountIdReader();
    private static final MapReader<AssetId, Asset> ASSETS_READER = new MapReader<>(
            new AssetIdReader(),
            new AssetReader());
    private static final ListReader<PublicKey> PUBLIC_KEY_LIST_READER = new ListReader<>(
            new PublicKeyReader());
    private static final MetadataReader METADATA_READER = new MetadataReader();

    @Override
    public NewAccount read(ScaleCodecReader reader) {
        return new NewAccount(
                reader.read(ACCOUNT_ID_READER),
                reader.read(PUBLIC_KEY_LIST_READER),
                reader.read(METADATA_READER)
        );
    }
}
