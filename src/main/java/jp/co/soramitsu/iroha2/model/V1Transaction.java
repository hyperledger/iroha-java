package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class V1Transaction implements VersionedTransaction {

    @NonNull
    private Transaction transaction;

    @Override
    public int getIndex() {
        return 1;
    }
}
