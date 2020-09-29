package jp.co.soramitsu.iroha.java.crypto;

import iroha.protocol.Primitive;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3;
import jp.co.soramitsu.iroha.java.Utils;
import jp.co.soramitsu.iroha.java.detail.Hashable;

import java.security.KeyPair;

/**
 * Signs with Ed25519/Sha3
 */
public class Ed25519Sha3SignatureBuilder implements SignatureBuilder {

    @Override
    public Primitive.Signature sign(Hashable toSign, KeyPair keyPair) {
        byte[] rawSignature = new Ed25519Sha3().rawSign(toSign.hash(), keyPair);
        return Primitive.Signature.newBuilder()
                .setSignature(Utils.toHex(rawSignature))
                .setPublicKey(getHexPublicKey(keyPair))
                .build();
    }

    @Override
    public String getHexPublicKey(KeyPair keyPair) {
        return Utils.toHex(keyPair.getPublic().getEncoded());
    }

}
