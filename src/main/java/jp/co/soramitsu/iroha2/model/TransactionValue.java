package jp.co.soramitsu.iroha2.model;

import lombok.Data;

@Data
public class TransactionValue implements ValueBox {
    @Override
    public int getIndex() {
        return 9;
    }
}
