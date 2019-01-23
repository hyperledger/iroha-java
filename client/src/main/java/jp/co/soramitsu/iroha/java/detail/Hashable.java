package jp.co.soramitsu.iroha.java.detail;

import com.google.protobuf.GeneratedMessageV3.Builder;
import org.spongycastle.jcajce.provider.digest.SHA3;

public abstract class Hashable<T extends Builder<T>> {

  private SHA3.Digest256 digest = new SHA3.Digest256();

  private T proto;

  public Hashable(T t) {
    this.proto = t;
  }

  public T getProto() {
    return proto;
  }

  public byte[] hash() {
    return digest.digest(payload());
  }

  public byte[] payload() {
    return proto.buildPartial().toByteArray();
  }

}
