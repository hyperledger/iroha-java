package jp.co.soramitsu.iroha.java;

import static jp.co.soramitsu.iroha.java.Utils.nonNull;

import iroha.protocol.Queries.GetAccount;
import iroha.protocol.Queries.GetAccountAssetTransactions;
import iroha.protocol.Queries.GetAccountAssets;
import iroha.protocol.Queries.GetAccountDetail;
import iroha.protocol.Queries.GetAccountTransactions;
import iroha.protocol.Queries.GetAssetInfo;
import iroha.protocol.Queries.GetPendingTransactions;
import iroha.protocol.Queries.GetRolePermissions;
import iroha.protocol.Queries.GetRoles;
import iroha.protocol.Queries.GetSignatories;
import iroha.protocol.Queries.GetTransactions;
import iroha.protocol.Queries.QueryPayloadMeta;
import iroha.protocol.Queries.TxPaginationMeta;
import iroha.protocol.Queries.TxPaginationMeta.Builder;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;

public class QueryBuilder {

  private FieldValidator validator;

  private QueryPayloadMeta.Builder meta = QueryPayloadMeta.newBuilder();

  private Query newQuery() {
    return new Query(meta);
  }

  private void init(String accountId, Long time, long counter) {
    setCreatorAccountId(accountId);
    setCreatedTime(time);
    setCounter(counter);
  }

  public QueryBuilder(String accountId, Instant time, long counter) {
    init(accountId, time.toEpochMilli(), counter);
  }

  public QueryBuilder(String accountId, Date time, long counter) {
    init(accountId, time.getTime(), counter);
  }

  public QueryBuilder(String accountId, Long time, long counter) {
    init(accountId, time, counter);
  }

  public QueryBuilder enableValidation() {
    this.validator = new FieldValidator();
    return this;
  }

  public QueryBuilder disableValidation() {
    this.validator = null;
    return this;
  }

  public QueryBuilder setCreatorAccountId(String accountId) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    meta.setCreatorAccountId(accountId);
    return this;
  }

  public QueryBuilder setCreatedTime(Long time) {
    if (nonNull(this.validator)) {
      this.validator.checkTimestamp(time);
    }

    meta.setCreatedTime(time);
    return this;
  }

  public QueryBuilder setCreatedTime(Date time) {
    return setCreatedTime(time.getTime());
  }

  public QueryBuilder setCreatedTime(Instant time) {
    return setCreatedTime(time.toEpochMilli());
  }

  public QueryBuilder setCounter(long counter) {
    meta.setQueryCounter(counter);
    return this;
  }

  public Query getAccountAssetTransactions(
      String accountId,
      String assetId,
      Integer pageSize,
      String firstHashHex
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkAssetId(assetId);
      this.validator.checkPageSize(pageSize);
    }

    Query query = newQuery();

    query.getProto().setGetAccountAssetTransactions(
        GetAccountAssetTransactions.newBuilder()
            .setAccountId(accountId)
            .setAssetId(assetId)
            .setPaginationMeta(
                getPaginationMeta(pageSize, firstHashHex)
            )
            .build()
    );

    return query;
  }

  public Query getAccount(
      String accountId
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetAccount(
        GetAccount.newBuilder()
            .setAccountId(accountId)
            .build()
    );

    return query;
  }

  public Query getSignatories(
      String accountId
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetSignatories(
        GetSignatories.newBuilder()
            .setAccountId(accountId)
            .build()
    );

    return query;
  }

  public Query getAccountAssets(
      String accountId
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetAccountAssets(
        GetAccountAssets.newBuilder()
            .setAccountId(accountId)
            .build()
    );

    return query;
  }

  /**
   * Get account detail. Each field is optional.
   *
   * @param accountId where to get "detail"; can be null
   * @param writer filter by writer; can be null
   * @param key filter by key; can be null
   */
  public Query getAccountDetail(
      String accountId,
      String writer,
      String key
  ) {

    val b = GetAccountDetail.newBuilder();

    if (nonNull(accountId)) {
      if (nonNull(this.validator)) {
        this.validator.checkAccountId(accountId);
      }
      b.setAccountId(accountId);
    }

    if (nonNull(writer)) {
      if (nonNull(this.validator)) {
        this.validator.checkAccountId(writer);
      }
      b.setWriter(writer);
    }

    if (nonNull(key)) {
      if (nonNull(this.validator)) {
        this.validator.checkAccountDetailsKey(key);
      }
      b.setKey(key);
    }

    Query query = newQuery();
    query.getProto().setGetAccountDetail(b);

    return query;
  }

  public Query getTransactions(List<byte[]> hashes) {
    return getTransactions(
        hashes.stream()
            .map(Utils::toHex)
            .collect(Collectors.toList())
    );
  }

  public Query getTransactions(Iterable<String> hashes) {
    Query query = newQuery();

    query.getProto().setGetTransactions(
        GetTransactions.newBuilder()
            .addAllTxHashes(hashes)
            .build()
    );

    return query;
  }

  public Query getPendingTransactions() {
    Query query = newQuery();

    query.getProto().setGetPendingTransactions(
        GetPendingTransactions.newBuilder()
            .build()
    );

    return query;
  }

  public Query getAccountTransactions(String accountId, Integer pageSize, String firstHashHex) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetAccountTransactions(
        GetAccountTransactions.newBuilder()
            .setAccountId(accountId)
            .setPaginationMeta(
                getPaginationMeta(pageSize, firstHashHex)
            )
            .build()
    );

    return query;
  }

  private TxPaginationMeta getPaginationMeta(Integer pageSize, String firstHashHex) {
    if (nonNull(this.validator)) {
      this.validator.checkPageSize(pageSize);
      if (firstHashHex != null) {
        this.validator.checkHashLength(firstHashHex);
      }
    }

    Builder paginationMetaBuilder = TxPaginationMeta.newBuilder().setPageSize(pageSize);
    if (firstHashHex != null) {
      paginationMetaBuilder.setFirstTxHash(firstHashHex);
    }

    return paginationMetaBuilder.build();
  }

  public Query getAssetInfo(String assetId) {
    if (nonNull(this.validator)) {
      this.validator.checkAssetId(assetId);
    }

    Query query = newQuery();

    query.getProto().setGetAssetInfo(
        GetAssetInfo.newBuilder()
            .setAssetId(assetId)
            .build()
    );

    return query;
  }

  public Query getRoles() {
    Query query = newQuery();

    query.getProto().setGetRoles(
        GetRoles.newBuilder()
            .build()
    );

    return query;
  }

  public Query getRolePermissions(String roleId) {
    if (nonNull(this.validator)) {
      this.validator.checkRoleName(roleId);
    }

    Query query = newQuery();

    query.getProto().setGetRolePermissions(
        GetRolePermissions.newBuilder()
            .setRoleId(roleId)
            .build()
    );

    return query;
  }
}
