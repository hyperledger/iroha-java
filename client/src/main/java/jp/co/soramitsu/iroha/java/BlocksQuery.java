package jp.co.soramitsu.iroha.java;

import iroha.protocol.Queries;
import iroha.protocol.Queries.Query.Payload;
import iroha.protocol.Queries.QueryPayloadMeta;
import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.CryptoException;
import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha3SignatureBuilder;
import jp.co.soramitsu.iroha.java.crypto.SignatureBuilder;
import jp.co.soramitsu.iroha.java.detail.Hashable;

public class BlocksQuery
    extends Hashable<Payload.Builder> {

  private QueryPayloadMeta.Builder meta;
  private SignatureBuilder signatureBuilder;
  private Queries.BlocksQuery.Builder q = Queries.BlocksQuery.newBuilder();

  public BlocksQuery(QueryPayloadMeta.Builder meta) {
    super(Payload.newBuilder());

    this.meta = meta;
    signatureBuilder = new Ed25519Sha3SignatureBuilder();
  }

  public BlocksQuery(QueryPayloadMeta.Builder meta, SignatureBuilder signatureBuilder) {
    super(Payload.newBuilder());

    this.meta = meta;
    this.signatureBuilder = signatureBuilder;
  }

  public byte[] payload() {
    return meta.buildPartial().toByteArray();
  }

  private void updatePayload() {
    getProto().setMeta(meta);
    q.setMeta(meta);
  }

  public Queries.BlocksQuery buildSigned(KeyPair keyPair) throws CryptoException {
    updatePayload();
    q.setSignature(signatureBuilder.sign(this, keyPair));
    return q.build();
  }

  public Queries.BlocksQuery buildUnsigned() {
    updatePayload();
    return q.build();
  }

  public static BlocksQueryBuilder builder(String accountId, Instant time, long counter) {
    return new BlocksQueryBuilder(accountId, time, counter);
  }

  public static BlocksQueryBuilder builder(String accountId, Date time, long counter) {
    return new BlocksQueryBuilder(accountId, time, counter);
  }

  public static BlocksQueryBuilder builder(String accountId, Long time, long counter) {
    return new BlocksQueryBuilder(accountId, time, counter);
  }

  public static BlocksQueryBuilder builder(String accountId, long counter) {
    return builder(accountId, System.currentTimeMillis(), counter);
  }
}
