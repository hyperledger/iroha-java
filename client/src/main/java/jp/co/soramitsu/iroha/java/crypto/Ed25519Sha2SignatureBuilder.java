package jp.co.soramitsu.iroha.java.crypto;

import iroha.protocol.Primitive;
import jp.co.soramitsu.iroha.java.Utils;
import jp.co.soramitsu.iroha.java.detail.Hashable;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;

import java.security.KeyPair;
import java.security.MessageDigest;

public class Ed25519Sha2SignatureBuilder implements SignatureBuilder {

    @Override
    public Primitive.Signature sign(Hashable toSign, KeyPair keyPair) {
        try {
            byte[] encodedHash = toSign.payload();

            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
            // SHA-512
            java.security.Signature sgr = new EdDSAEngine(MessageDigest.getInstance(spec.getHashAlgorithm()));
            sgr.initSign(keyPair.getPrivate());

            sgr.update(encodedHash);
            byte[] rawSignature = sgr.sign();
            return Primitive.Signature.newBuilder()
                    .setSignature(Utils.toHex(rawSignature))
                    .setPublicKey(getHexPublicKey(keyPair))
                    .build();

        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public String getHexPublicKey(KeyPair keyPair) {
        return "ed0120" + Utils.toHex(((EdDSAPublicKey) keyPair.getPublic()).getAbyte());
    }
}
