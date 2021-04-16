package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Vector implements ValueBox {

    @NonNull
    private List<Value> vector;

    @Override
    public int getIndex() {
        return 3;
    }
}
