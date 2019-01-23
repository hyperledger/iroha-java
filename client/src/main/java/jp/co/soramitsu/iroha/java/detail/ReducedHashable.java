package jp.co.soramitsu.iroha.java.detail;

public interface ReducedHashable {

  byte[] getReducedHash();

  String getReducedHashHex();
}
