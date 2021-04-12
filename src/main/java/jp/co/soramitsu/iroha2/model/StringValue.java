package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class StringValue implements ValueBox {

    @NonNull
    private String value;

    @Override
    public int getIndex() {
        return 2;
    }
}
