package jp.co.soramitsu.iroha.java;

import static java.util.Objects.nonNull;

import iroha.protocol.Queries;
import iroha.protocol.Queries.QueryPayloadMeta;
import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.CryptoException;

public class BlocksQueryBuilder {
  private final FieldValidator.Config config;
  private FieldValidator validator;

  private QueryPayloadMeta.Builder meta = QueryPayloadMeta.newBuilder();

  private BlocksQuery newQuery() {
    return new BlocksQuery(meta);
  }

  private void init(String accountId, Long time, long counter) {
    setCreatorAccountId(accountId);
    setCreatedTime(time);
    setCounter(counter);
  }

  public BlocksQueryBuilder(String accountId, Instant time, long counter, FieldValidator.Config config) {
    this(accountId, time.toEpochMilli(), counter, config);
  }

  public BlocksQueryBuilder(String accountId, Date time, long counter, FieldValidator.Config config) {
    this(accountId, time.getTime(), counter, config);
  }

  public BlocksQueryBuilder(String accountId, Long time, long counter, FieldValidator.Config config) {
    this.config = config;
    init(accountId, time, counter);
  }

  public BlocksQueryBuilder enableValidation() {
    this.validator = new FieldValidator(this.config);
    return this;
  }

  public BlocksQueryBuilder disableValidation() {
    this.validator = null;
    return this;
  }

  public BlocksQueryBuilder setCreatorAccountId(String accountId) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    meta.setCreatorAccountId(accountId);
    return this;
  }

  public BlocksQueryBuilder setCreatedTime(Long time) {
    if (nonNull(this.validator)) {
      this.validator.checkTimestamp(time);
    }

    meta.setCreatedTime(time);
    return this;
  }

  public BlocksQueryBuilder setCreatedTime(Instant time) {
    return setCreatedTime(time.toEpochMilli());
  }

  public BlocksQueryBuilder setCreatedTime(Date time) {
    return setCreatedTime(time.getTime());
  }

  public BlocksQueryBuilder setCounter(long counter) {
    meta.setQueryCounter(counter);
    return this;
  }

  public BlocksQuery getQuery() {
    return newQuery();
  }

  public Queries.BlocksQuery buildSigned(KeyPair keyPair) throws CryptoException {
    return newQuery().buildSigned(keyPair);
  }

  public Queries.BlocksQuery buildUnsigned() {
    return newQuery().buildUnsigned();
  }
}
