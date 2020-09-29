package jp.co.soramitsu.iroha.java.crypto;

import iroha.protocol.Primitive;
import jp.co.soramitsu.iroha.java.detail.Hashable;

import java.security.KeyPair;

public interface SignatureBuilder {
    Primitive.Signature sign(Hashable toSign, KeyPair keyPair);

    String getHexPublicKey(KeyPair keyPair);

    class CryptoException extends RuntimeException {
        CryptoException(Exception e) {
            super(e);
        }
    }
}
