package jp.co.soramitsu.iroha2.model;

import lombok.Data;

@Data
public class BigQuantity implements AssetValueType {
    @Override
    public int getIndex() {
        return 1;
    }
}