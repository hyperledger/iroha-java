package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;

@Data
public class U128 {

    @NonNull
    private BigInteger value;
}
