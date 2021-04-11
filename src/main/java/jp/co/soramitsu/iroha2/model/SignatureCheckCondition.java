package jp.co.soramitsu.iroha2.model;

import lombok.Data;

@Data
public class SignatureCheckCondition implements ValueBox {
    @Override
    public int getIndex() {
        return 8;
    }
}
