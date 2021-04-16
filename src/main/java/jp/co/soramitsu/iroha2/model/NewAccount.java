package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class NewAccount implements IdentifiableBox {

    @NonNull
    private AccountId id;
    @NonNull
    private List<PublicKey> signatories;
    @NonNull
    private Metadata metadata;

    public NewAccount(AccountId id) {
        this.id = id;
        this.signatories = new ArrayList<>();
        this.metadata = new Metadata(new HashMap<>());
    }

    public NewAccount(AccountId id, List<PublicKey> signatories, Metadata metadata) {
        this.id = id;
        this.signatories = signatories;
        this.metadata = metadata;
    }

    @Override
    public int getIndex() {
        return 1;
    }
}
