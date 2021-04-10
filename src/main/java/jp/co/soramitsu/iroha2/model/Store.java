package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Store implements AssetValue {

    @NonNull
    private Metadata metadata;

    @Override
    public int getIndex() {
        return 2;
    }
}