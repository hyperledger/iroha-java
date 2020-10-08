package jp.co.soramitsu.iroha.java;

import static jp.co.soramitsu.iroha.java.Utils.nonNull;

import iroha.protocol.Primitive.AccountDetailRecordId;
import iroha.protocol.Queries;
import iroha.protocol.Queries.Field;
import iroha.protocol.Queries.Direction;
import iroha.protocol.Queries.AccountDetailPaginationMeta;
import iroha.protocol.Queries.AssetPaginationMeta;
import iroha.protocol.Queries.GetAccount;
import iroha.protocol.Queries.GetAccountAssetTransactions;
import iroha.protocol.Queries.GetAccountAssets;
import iroha.protocol.Queries.GetAccountDetail;
import iroha.protocol.Queries.GetEngineReceipts;
import iroha.protocol.Queries.GetAccountTransactions;
import iroha.protocol.Queries.GetAssetInfo;
import iroha.protocol.Queries.GetBlock;
import iroha.protocol.Queries.GetPeers;
import iroha.protocol.Queries.GetPendingTransactions;
import iroha.protocol.Queries.GetRolePermissions;
import iroha.protocol.Queries.GetRoles;
import iroha.protocol.Queries.GetSignatories;
import iroha.protocol.Queries.GetTransactions;
import iroha.protocol.Queries.QueryPayloadMeta;
import iroha.protocol.Queries.TxPaginationMeta;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha3SignatureBuilder;
import jp.co.soramitsu.iroha.java.crypto.SignatureBuilder;
import lombok.Value;
import lombok.val;

public class QueryBuilder {

  public class Ordering {

    @Value
    class Sequence {
      public Sequence(Field field, Direction direction) {
        this.field = field;
        this.direction = direction;
      }

      Field field;
      Direction direction;
    }

    void addFieldOrdering(Field field, Direction direction) {
      addFieldOrdering(new Sequence(field, direction));
    }

    void addFieldOrdering(Sequence sequence) {
      fieldOrdering.add(sequence);
    }

    Queries.Ordering.Builder toBuilder() {
      Queries.Ordering.Builder builder = Queries.Ordering.newBuilder();
      fieldOrdering.forEach(i -> {
           builder.addSequence(Queries.Ordering.FieldOrdering.newBuilder()
               .setField(i.getField())
               .setDirection(i.getDirection()));
           });
      return builder;
    }

    List<Sequence> fieldOrdering = new ArrayList<>();
  }

  private SignatureBuilder signatureBuilder;

  private FieldValidator validator;

  private QueryPayloadMeta.Builder meta = QueryPayloadMeta.newBuilder();

  private Query newQuery() {
    return new Query(meta, signatureBuilder);
  }

  private void init(String accountId, Long time, long counter, SignatureBuilder signatureBuilder) {
    this.signatureBuilder = signatureBuilder;
    setCreatorAccountId(accountId);
    setCreatedTime(time);
    setCounter(counter);

    enableValidation();
  }

  public QueryBuilder(String accountId, Instant time, long counter) {
    init(accountId, time.toEpochMilli(), counter, Ed25519Sha3SignatureBuilder.getInstance());
  }

  public QueryBuilder(String accountId, Date time, long counter) {
    init(accountId, time.getTime(), counter, Ed25519Sha3SignatureBuilder.getInstance());
  }

  public QueryBuilder(String accountId, Long time, long counter) {
    init(accountId, time, counter, Ed25519Sha3SignatureBuilder.getInstance());
  }

  public QueryBuilder(String accountId, Instant time, long counter, SignatureBuilder signatureBuilder) {
    init(accountId, time.toEpochMilli(), counter, signatureBuilder);
  }

  public QueryBuilder(String accountId, Date time, long counter, SignatureBuilder signatureBuilder) {
    init(accountId, time.getTime(), counter, signatureBuilder);
  }

  public QueryBuilder(String accountId, Long time, long counter, SignatureBuilder signatureBuilder) {
    init(accountId, time, counter, signatureBuilder);
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

  public Query getEngineReceipts(String txHash) {
    Query query = newQuery();

    query.getProto().setGetEngineReceipts(
        GetEngineReceipts.newBuilder()
            .setTxHash(txHash)
            .build()
    );

    return query;
  }

  public Query getPeers() {
    Query query = newQuery();

    query.getProto().setGetPeers(
        GetPeers.newBuilder()
            .build()
    );

    return query;
  }

  public Query getAccountAssetTransactions(
      String accountId,
      String assetId,
      Integer pageSize,
      String firstHashHex,
      Ordering ordering
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
                getTxPaginationMeta(pageSize, firstHashHex, ordering)
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

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
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

  public Query getAccountAssets(
      String accountId,
      Integer pageSize,
      String firstAssetId
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetAccountAssets(
        GetAccountAssets.newBuilder()
            .setAccountId(accountId)
            .setPaginationMeta(
                getAssetPaginationMeta(pageSize, firstAssetId)
            )
            .build()
    );

    return query;
  }

  private AssetPaginationMeta getAssetPaginationMeta(
      Integer pageSize,
      String firstAssetId
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkPageSize(pageSize);
      if (firstAssetId != null) {
        this.validator.checkAssetId(firstAssetId);
      }
    }

    val paginationMetaBuilder = AssetPaginationMeta.newBuilder().setPageSize(pageSize);
    if (firstAssetId != null) {
      paginationMetaBuilder.setFirstAssetId(firstAssetId);
    }

    return paginationMetaBuilder.build();
  }

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
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
      String key,
      Integer pageSize,
      String accountDetailRecordIdWriter,
      String accountDetailRecordIdKey
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
    query.getProto().setGetAccountDetail(
        b.setPaginationMeta(
            getAccountDetailPaginationMeta(
                pageSize,
                accountDetailRecordIdWriter,
                accountDetailRecordIdKey
            )
        )
    );

    return query;
  }

  private AccountDetailPaginationMeta getAccountDetailPaginationMeta(
      Integer pageSize,
      String accountDetailRecordIdWriter,
      String accountDetailRecordIdKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkPageSize(pageSize);
      if (accountDetailRecordIdWriter != null) {
        this.validator.checkAccountId(accountDetailRecordIdWriter);
      }
      if (accountDetailRecordIdKey != null) {
        this.validator.checkAccountDetailsKey(accountDetailRecordIdKey);
      }
    }

    val paginationMetaBuilder = AccountDetailPaginationMeta
        .newBuilder()
        .setPageSize(pageSize);

    if (accountDetailRecordIdWriter != null || accountDetailRecordIdKey != null) {
      AccountDetailRecordId.Builder accountRecordIdBuilder = AccountDetailRecordId.newBuilder();
      if (accountDetailRecordIdWriter != null) {
        accountRecordIdBuilder.setWriter(accountDetailRecordIdWriter);
      }
      if (accountDetailRecordIdKey != null) {
        accountRecordIdBuilder.setKey(accountDetailRecordIdKey);
      }
      paginationMetaBuilder.setFirstRecordId(accountRecordIdBuilder.build());
    }

    return paginationMetaBuilder.build();
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

  public Query getBlock(Long height) {
    Query query = newQuery();

    query.getProto().setGetBlock(
        GetBlock.newBuilder()
            .setHeight(height)
            .build()
    );

    return query;
  }

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
  public Query getPendingTransactions() {
    Query query = newQuery();

    query.getProto().setGetPendingTransactions(
        GetPendingTransactions.newBuilder()
            .build()
    );

    return query;
  }

  public Query getPendingTransactions(
      Integer pageSize,
      String firstHashHex,
      Ordering ordering
  ) {
    Query query = newQuery();

    query.getProto().setGetPendingTransactions(
        GetPendingTransactions.newBuilder()
            .setPaginationMeta(
                getTxPaginationMeta(pageSize, firstHashHex, ordering)
            )
            .build()
    );

    return query;
  }

  public Query getAccountTransactions(String accountId, Integer pageSize, String firstHashHex, Ordering ordering) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    Query query = newQuery();

    query.getProto().setGetAccountTransactions(
        GetAccountTransactions.newBuilder()
            .setAccountId(accountId)
            .setPaginationMeta(
                getTxPaginationMeta(pageSize, firstHashHex, ordering)
            )
            .build()
    );

    return query;
  }

  private TxPaginationMeta getTxPaginationMeta(Integer pageSize, String firstHashHex, Ordering ordering) {
    if (nonNull(this.validator)) {
      this.validator.checkPageSize(pageSize);
      if (firstHashHex != null) {
        this.validator.checkHashLength(firstHashHex);
      }
    }

    val paginationMetaBuilder = TxPaginationMeta.newBuilder()
        .setPageSize(pageSize);
    if (firstHashHex != null) {
      paginationMetaBuilder.setFirstTxHash(firstHashHex);
    }

    if (ordering != null) {
      paginationMetaBuilder.setOrdering(ordering.toBuilder());
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
