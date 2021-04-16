package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import jp.co.soramitsu.iroha2.model.IdentifiableBox;

public class IdentifiableBoxReader implements ScaleReader<IdentifiableBox> {

    private static final UnionReader<IdentifiableBox> IDENTIFIABLE_BOX_UNION_READER = new UnionReader<>(
            new AccountReader(), // 0 Account
            new NewAccountReader(), // 1 NewAccount
            new AssetReader(), // 2 Asset
            new AssetDefinitionReader(), // 3 AssetDefinition
            new DomainReader(), // 4 Domain
            new PeerReader(), // 5 Peer
            new WorldReader() // 6 World
    );

    @Override
    public IdentifiableBox read(ScaleCodecReader reader) {
        return reader.read(IDENTIFIABLE_BOX_UNION_READER).getValue();
    }
}
