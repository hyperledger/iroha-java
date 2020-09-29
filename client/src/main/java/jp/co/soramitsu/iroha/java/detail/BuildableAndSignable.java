package jp.co.soramitsu.iroha.java.detail;

import java.security.KeyPair;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.CryptoException;
import jp.co.soramitsu.iroha.java.crypto.SignatureBuilder;

public interface BuildableAndSignable<T> {

  /**
   * An old version of transaction sign. Uses Iroha builtin Ed25519/Sha3 signature implicitly. Prefer explicit version
   * sign(KeyPair keyPair, SignatureBuilder signatureBuilder)
   */
  @Deprecated
  BuildableAndSignable<T> sign(KeyPair keyPair) throws CryptoException;

  BuildableAndSignable<T> sign(KeyPair keyPair, SignatureBuilder signatureBuilder) throws CryptoException;

  T build();
}
