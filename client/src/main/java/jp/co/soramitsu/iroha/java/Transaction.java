package jp.co.soramitsu.iroha.java;

import com.google.protobuf.InvalidProtocolBufferException;
import iroha.protocol.TransactionOuterClass;
import iroha.protocol.TransactionOuterClass.Transaction.Payload;
import iroha.protocol.TransactionOuterClass.Transaction.Payload.BatchMeta;
import iroha.protocol.TransactionOuterClass.Transaction.Payload.ReducedPayload;
import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;
import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha3SignatureBuilder;
import jp.co.soramitsu.iroha.java.crypto.SignatureBuilder;
import jp.co.soramitsu.iroha.java.detail.BuildableAndSignable;
import jp.co.soramitsu.iroha.java.detail.Hashable;
import jp.co.soramitsu.iroha.java.detail.ReducedHashable;
import lombok.val;

public class Transaction
    extends
    Hashable<TransactionOuterClass.Transaction.Payload.Builder>  // should be Payload.Builder
    implements BuildableAndSignable<TransactionOuterClass.Transaction>,
    ReducedHashable {

  private TransactionOuterClass.Transaction.Builder tx = TransactionOuterClass.Transaction
      .newBuilder();

  /* default */ ReducedPayload.Builder reducedPayload = ReducedPayload.newBuilder();

  /* default */ BatchMeta.Builder batchMeta = BatchMeta.newBuilder();

  /* default */ void updatePayload() {
    tx.setPayload(
        getProto()
            .setReducedPayload(reducedPayload)
    );
  }

  /* default */ void updateBatch() {
    tx.setPayload(
        getProto()
            .setBatch(batchMeta)
    );
  }

  /* default */ Transaction() {
    super(Payload.newBuilder());
  }

  /* default */ Transaction(TransactionOuterClass.Transaction tx) {
    super(Payload.newBuilder(tx.getPayload()));
    this.tx = TransactionOuterClass.Transaction.newBuilder(tx);
    this.reducedPayload = ReducedPayload.newBuilder(tx.getPayload().getReducedPayload());
    this.batchMeta = BatchMeta.newBuilder(tx.getPayload().getBatch());
  }

  /**
   * An old version of transaction sign. Uses Iroha builtin Ed25519/Sha3 signature implicitly. Prefer explicit version
   * sign(KeyPair keyPair, SignatureBuilder signatureBuilder)
   */
  @Deprecated
  @Override
  public BuildableAndSignable<TransactionOuterClass.Transaction> sign(KeyPair keyPair) {
    updatePayload();
    tx.addSignatures(Ed25519Sha3SignatureBuilder.getInstance().sign(this, keyPair));
    return this;
  }

  @Override
  public BuildableAndSignable<TransactionOuterClass.Transaction> sign(KeyPair keyPair, SignatureBuilder signatureBuilder) {
    updatePayload();
    tx.addSignatures(signatureBuilder.sign(this, keyPair));
    return this;
  }

  @Override
  public TransactionOuterClass.Transaction build() {
    updatePayload();
    return tx.build();
  }

  @Override
  public byte[] getReducedHash() {
    return Utils.reducedHash(tx.getPayload().getReducedPayload());
  }

  @Override
  public String getReducedHashHex() {
    return Utils.toHex(getReducedHash());
  }

  public TransactionBuilder makeMutable() {
    tx.clearSignatures();
    return new TransactionBuilder(this);
  }

  public static Transaction parseFrom(TransactionOuterClass.Transaction input) {
    return new Transaction(input);
  }

  public static Transaction parseFrom(byte[] input) throws InvalidProtocolBufferException {
    val proto = TransactionOuterClass.Transaction.parseFrom(input);
    return new Transaction(proto);
  }

  public static TransactionBuilder builder(String accountId, Long date) {
    return new TransactionBuilder(accountId, date);
  }

  public static TransactionBuilder builder(String accountId, Date date) {
    return new TransactionBuilder(accountId, date);
  }

  public static TransactionBuilder builder(String accountId, Instant time) {
    return new TransactionBuilder(accountId, time);
  }

  public static TransactionBuilder builder(String accountId) {
    return builder(accountId, System.currentTimeMillis());
  }

  public static TransactionBuilder builder(String accountId, Long date, SignatureBuilder signatureBuilder) {
    return new TransactionBuilder(accountId, date, signatureBuilder);
  }

  public static TransactionBuilder builder(String accountId, Date date, SignatureBuilder signatureBuilder) {
    return new TransactionBuilder(accountId, date, signatureBuilder);
  }

  public static TransactionBuilder builder(String accountId, Instant time, SignatureBuilder signatureBuilder) {
    return new TransactionBuilder(accountId, time, signatureBuilder);
  }

  public static TransactionBuilder builder(String accountId, SignatureBuilder signatureBuilder) {
    return builder(accountId, System.currentTimeMillis(), signatureBuilder);
  }
}
