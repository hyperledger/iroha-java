package jp.co.soramitsu.iroha2.model;

import lombok.Data;

@Data
 public class Quantity implements AssetValueType {
    @Override
    public int getIndex() {
        return 0;
    }
}