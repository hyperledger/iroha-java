package jp.co.soramitsu.iroha2.model;

import lombok.Data;

@Data
public class TransactionValue implements ValueBox {

    private TransactionValueVariant valueVariant;

    @Override
    public int getIndex() {
        return 9;
    }
}
