package jp.co.soramitsu.iroha2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Account implements IdentifiableBox {

    @NonNull
    private AccountId id;
    @NonNull
    private Map<AssetId, Asset> assets;
    @NonNull
    private List<PublicKey> signatories;
    @NonNull
    private List<PermissionRaw> permissions;
    @NonNull
    private SignatureCheckCondition signatureCheckCondition;
    @NonNull
    private Metadata metadata;

    @Override
    public int getIndex() {
        return 0;
    }
}
