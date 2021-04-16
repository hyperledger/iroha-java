package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import jp.co.soramitsu.iroha2.model.*;

public class AccountReader implements ScaleReader<Account> {

    private static final AccountIdReader ACCOUNT_ID_READER = new AccountIdReader();
    private static final MapReader<AssetId, Asset> ASSETS_READER = new MapReader<>(
            new AssetIdReader(),
            new AssetReader());
    private static final ListReader<PublicKey> PUBLIC_KEY_LIST_READER = new ListReader<>(
            new PublicKeyReader());
    private static final ListReader<PermissionRaw> PERMISSION_RAW_LIST_READER = new ListReader<>(
            new PermissionRawReader());
    private static final SignatureCheckConditionReader SIGNATURE_CHECK_CONDITION_READER = new SignatureCheckConditionReader();
    private static final MetadataReader METADATA_READER = new MetadataReader();

    @Override
    public Account read(ScaleCodecReader reader) {
        return new Account(
                reader.read(ACCOUNT_ID_READER),
                reader.read(ASSETS_READER),
                reader.read(PUBLIC_KEY_LIST_READER),
                reader.read(PERMISSION_RAW_LIST_READER),
                reader.read(SIGNATURE_CHECK_CONDITION_READER),
                reader.read(METADATA_READER)
        );
    }
}
