package jp.co.soramitsu.iroha2.model.expression;

import lombok.Data;

@Data
public class Mod implements Expression {
    @Override
    public int getIndex() {
        return 4;
    }
}
