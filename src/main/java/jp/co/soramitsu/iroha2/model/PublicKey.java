package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

import javax.xml.bind.DatatypeConverter;

@Data
public class PublicKey implements ValueBox {

    @NonNull
    private String digestFunction;
    @NonNull
    private byte[] payload;

    @Override
    public int getIndex() {
        return 6;
    }

    @Override
    public String toString() {
        return "PublicKey{" +
                "digestFunction='" + digestFunction + '\'' +
                ", payload='0x" + DatatypeConverter.printHexBinary(payload) + "'}";
    }
}
