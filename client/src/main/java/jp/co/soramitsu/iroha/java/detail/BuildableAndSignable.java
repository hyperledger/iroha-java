package jp.co.soramitsu.iroha.java.detail;

import java.security.KeyPair;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.CryptoException;

public interface BuildableAndSignable<T> {

  BuildableAndSignable<T> sign(KeyPair keyPair) throws CryptoException;

  T build();
}
