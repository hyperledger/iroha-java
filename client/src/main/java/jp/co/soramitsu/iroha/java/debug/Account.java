package jp.co.soramitsu.iroha.java.debug;

import java.security.KeyPair;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3;
import lombok.Value;

@Value
public class Account {

  private static Ed25519Sha3 crypto = new Ed25519Sha3();

  private String id;
  private KeyPair keyPair;

  public static Account create(String id) {
    return new Account(id, crypto.generateKeypair());
  }
}
