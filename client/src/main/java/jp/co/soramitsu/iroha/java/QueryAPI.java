package jp.co.soramitsu.iroha.java;

import com.google.protobuf.Timestamp;
import iroha.protocol.QryResponses.AccountAssetResponse;
import iroha.protocol.QryResponses.AccountDetailResponse;
import iroha.protocol.QryResponses.AccountResponse;
import iroha.protocol.QryResponses.EngineReceiptsResponse;
import iroha.protocol.QryResponses.AssetResponse;
import iroha.protocol.QryResponses.BlockResponse;
import iroha.protocol.QryResponses.ErrorResponse;
import iroha.protocol.QryResponses.PeersResponse;
import iroha.protocol.QryResponses.QueryResponse;
import iroha.protocol.QryResponses.RolesResponse;
import iroha.protocol.QryResponses.RolePermissionsResponse;
import iroha.protocol.QryResponses.SignatoriesResponse;
import iroha.protocol.QryResponses.TransactionsPageResponse;
import iroha.protocol.QryResponses.TransactionsResponse;
import java.security.KeyPair;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha3SignatureBuilder;
import jp.co.soramitsu.iroha.java.crypto.SignatureBuilder;
import jp.co.soramitsu.iroha.java.debug.Account;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

@Getter
public class QueryAPI {

  @NonNull
  private IrohaAPI api;
  @NonNull
  private String accountId;
  @NonNull
  private KeyPair keyPair;
  @NonNull
  private final FieldValidator.Config config;
  // default signature builder
  private SignatureBuilder signatureBuilder;

  public QueryAPI(IrohaAPI api, String accountId, KeyPair keyPair, FieldValidator.Config config) {
    this(api, accountId, keyPair, Ed25519Sha3SignatureBuilder.getInstance(), config);
  }

  public QueryAPI(IrohaAPI api, String accountId, KeyPair keyPair, SignatureBuilder signatureBuilder, FieldValidator.Config config) {
    this.api = api;
    this.accountId = accountId;
    this.keyPair = keyPair;
    this.signatureBuilder = signatureBuilder;
    this.config = config;
  }

  public QueryAPI(IrohaAPI api, Account account, FieldValidator.Config config) {
    this(api, account.getId(), account.getKeyPair(), Ed25519Sha3SignatureBuilder.getInstance(), config);
  }

  public QueryAPI(IrohaAPI api, Account account, SignatureBuilder signatureBuilder, FieldValidator.Config config) {
    this(api, account.getId(), account.getKeyPair(), signatureBuilder, config);
  }

  private static AtomicInteger counter = new AtomicInteger(1);

  private void checkErrorResponse(QueryResponse response) {
    if (response.hasErrorResponse()) {
      ErrorResponse errorResponse = response.getErrorResponse();
      throw new ErrorResponseException(errorResponse);
    }
  }

  public EngineReceiptsResponse getEngineReceipts(String txHash) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
            .getEngineReceipts(txHash)
            .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getEngineReceiptsResponse();
  }

  public PeersResponse getPeers() {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getPeers()
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getPeersResponse();
  }

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
  public String getAccountDetails(
      String accountId,
      String writer,
      String key
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAccountDetail(accountId, writer, key)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    val adr = res.getAccountDetailResponse();

    return adr.getDetail();
  }

  public AccountDetailResponse getAccountDetails(
      String accountId,
      String writer,
      String key,
      Integer pageSize,
      String accountDetailRecordIdWriter,
      String accountDetailRecordIdKey
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAccountDetail(
            accountId,
            writer,
            key,
            pageSize,
            accountDetailRecordIdWriter,
            accountDetailRecordIdKey
        )
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountDetailResponse();
  }

  public AccountDetailResponse getAccountDetails(
      String accountId,
      String writer,
      String key,
      Integer pageSize
  ) {
    return getAccountDetails(accountId, writer, key, pageSize, null, null);
  }

  public AccountResponse getAccount(String accountId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAccount(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountResponse();
  }

  public BlockResponse getBlock(Long height) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getBlock(height)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getBlockResponse();
  }
  public TransactionsPageResponse getAccountTransactions(String accountId,
                                                         Integer pageSize,
                                                         String firstHashHex,
                                                         QueryBuilder.Ordering ordering,
                                                         Timestamp firstTxTime,
                                                         Timestamp lastTxTime,
                                                         Integer firstTxHeight,
                                                         Integer lastTxHeight) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
            .getAccountTransactions(accountId, pageSize, firstHashHex, ordering, firstTxTime, lastTxTime, firstTxHeight, lastTxHeight)
            .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }
  public TransactionsPageResponse getAccountTransactions(String accountId,
                                                         Integer pageSize,
                                                         String firstHashHex,
                                                         QueryBuilder.Ordering ordering) {
    return getAccountTransactions(accountId, pageSize, firstHashHex, ordering, null, null, null, null);
  }

  public TransactionsPageResponse getAccountTransactions(String accountId,
                                                         Integer pageSize,
                                                         String firstHashHex) {
    return getAccountTransactions(accountId, pageSize, firstHashHex, null);
  }

  public TransactionsPageResponse getAccountTransactions(String accountId, Integer pageSize) {
    return getAccountTransactions(accountId, pageSize, null, null);
  }

  public TransactionsPageResponse getAccountTransactions(String accountId,
                                                         Integer pageSize,
                                                         QueryBuilder.Ordering ordering) {
    return getAccountTransactions(accountId, pageSize, null, ordering);
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId,
                                                              String assetId,
                                                              Integer pageSize,
                                                              String firstHashHex,
                                                              QueryBuilder.Ordering ordering) {

    return getAccountAssetTransactions(accountId, assetId, pageSize, firstHashHex, ordering, null, null, null, null);

  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId,
                                                              String assetId,
                                                              Integer pageSize,
                                                              String firstHashHex,
                                                              QueryBuilder.Ordering ordering,
                                                              Timestamp firstTxTime,
                                                              Timestamp lastTxTime,
                                                              Integer firstTxHeight,
                                                              Integer lastTxHeight) {

    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
            .getAccountAssetTransactions(accountId, assetId, pageSize, firstHashHex, ordering, firstTxTime, lastTxTime, firstTxHeight, lastTxHeight)
            .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId,
                                                              String assetId,
                                                              Integer pageSize,
                                                              String firstHashHex) {
    return getAccountAssetTransactions(accountId, assetId, pageSize, firstHashHex, null);
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId,
                                                              String assetId,
                                                              Integer pageSize) {
    return getAccountAssetTransactions(accountId, assetId, pageSize, null, null);
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId,
                                                              String assetId,
                                                              Integer pageSize,
                                                              QueryBuilder.Ordering ordering) {
    return getAccountAssetTransactions(accountId, assetId, pageSize, null, ordering);
  }

  public TransactionsResponse getTransactions(List<byte[]> hashes) {
    return getTransactions(
        hashes.stream()
            .map(Utils::toHex)
            .collect(Collectors.toList())
    );
  }

  public TransactionsResponse getTransactions(Iterable<String> hashes) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getTransactions(hashes)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public AssetResponse getAssetInfo(String assetId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAssetInfo(assetId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAssetResponse();
  }

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
  public AccountAssetResponse getAccountAssets(String accountId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAccountAssets(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountAssetsResponse();
  }

  public AccountAssetResponse getAccountAssets(
      String accountId,
      Integer pageSize,
      String firstAssetId
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getAccountAssets(accountId, pageSize, firstAssetId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountAssetsResponse();
  }

  public AccountAssetResponse getAccountAssets(
      String accountId,
      Integer pageSize
  ) {
    return getAccountAssets(accountId, pageSize, null);
  }

  public SignatoriesResponse getSignatories(String accountId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getSignatories(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getSignatoriesResponse();
  }

  /**
   * Pagination metadata can be missing in the request for compatibility reasons, but this behaviour
   * is deprecated and should be avoided. This function is deprecated in Iroha 1.1.0 and will be
   * deleted in Iroha 2.0.0
   */
  @Deprecated
  public TransactionsResponse getPendingTransactions() {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getPendingTransactions()
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public TransactionsResponse getPendingTransactions(
          Integer pageSize,
          String firstHashHex,
          QueryBuilder.Ordering ordering,
          Timestamp firstTxTime,
          Timestamp lastTxTime,
          Integer firstTxHeight,
          Integer lastTxHeight
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
            .getPendingTransactions(pageSize, firstHashHex, ordering, firstTxTime, lastTxTime, firstTxHeight, lastTxHeight)
            .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public TransactionsResponse getPendingTransactions(
          Integer pageSize,
          String firstHashHex,
          QueryBuilder.Ordering ordering
  ) {
    return getPendingTransactions(pageSize, firstHashHex, ordering, null, null, null, null);
  }

  public TransactionsResponse getPendingTransactions(
          Integer pageSize,
          String firstHashHex
  ) {
    return getPendingTransactions(pageSize, firstHashHex, null);
  }

  public TransactionsResponse getPendingTransactions(
      Integer pageSize
  ) {
    return getPendingTransactions(pageSize, null, null);
  }

  public TransactionsResponse getPendingTransactions(
          Integer pageSize,
          QueryBuilder.Ordering ordering
  ) {
    return getPendingTransactions(pageSize, null, ordering);
  }

  public RolesResponse getRoles() {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getRoles()
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getRolesResponse();
  }

  public RolePermissionsResponse getRolePermissions(String roleId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement(), signatureBuilder, this.config)
        .getRolePermissions(roleId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getRolePermissionsResponse();
  }
}
