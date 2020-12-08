package jp.co.soramitsu.iroha.java.debug;

import java.security.*;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3;
import jp.co.soramitsu.iroha.java.Utils;
import lombok.Value;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.spec.*;
import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import static jp.co.soramitsu.crypto.ed25519.spec.EdDSANamedCurveTable.ED_25519;

@Value
public class Account {

  private static Ed25519Sha3 edSha3Crypto = new Ed25519Sha3();

  private String id;
  private KeyPair keyPair;

  public KeyPair getKeyPair() {
    return keyPair;
  }

  public static Account create(String id) {
    return new Account(id, edSha3Crypto.generateKeypair());
  }

  public static Account createWithEd25519Sha2(String id) {
    byte[] pkb = parseHexBinary("99fe8969acdafb09bfdd0046370e2fa2580b0c2591a2363625250da14d771b63");
    EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(ED_25519);
    net.i2p.crypto.eddsa.EdDSAPrivateKey pk = new EdDSAPrivateKey(new EdDSAPrivateKeySpec(pkb, spec));
    EdDSANamedCurveSpec keySpecs = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
    EdDSAPublicKeySpec pubKey = new EdDSAPublicKeySpec(pk.getA(), keySpecs);
    KeyPair kp = new KeyPair(new EdDSAPublicKey(pubKey), pk);

    return new Account(id, kp);
  }

  public String getHexPublicKey() {
    if (keyPair.getPublic() instanceof EdDSAPublicKey)
      return "ed0120" + Utils.toHex(((EdDSAPublicKey) keyPair.getPublic()).getAbyte());
    return Utils.toHex(keyPair.getPublic().getEncoded());
  }
}
