package jp.co.soramitsu.iroha.java;

import iroha.protocol.QryResponses.AccountAssetResponse;
import iroha.protocol.QryResponses.AccountResponse;
import iroha.protocol.QryResponses.AssetResponse;
import iroha.protocol.QryResponses.BlockResponse;
import iroha.protocol.QryResponses.ErrorResponse;
import iroha.protocol.QryResponses.PeersResponse;
import iroha.protocol.QryResponses.QueryResponse;
import iroha.protocol.QryResponses.SignatoriesResponse;
import iroha.protocol.QryResponses.TransactionsPageResponse;
import iroha.protocol.QryResponses.TransactionsResponse;
import java.security.KeyPair;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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

  public QueryAPI(IrohaAPI api, String accountId, KeyPair keyPair) {
    this.api = api;
    this.accountId = accountId;
    this.keyPair = keyPair;
  }

  public QueryAPI(IrohaAPI api, Account account) {
    this.api = api;
    this.accountId = account.getId();
    this.keyPair = account.getKeyPair();
  }

  private static AtomicInteger counter = new AtomicInteger(1);

  private void checkErrorResponse(QueryResponse response) {
    if (response.hasErrorResponse()) {
      ErrorResponse errorResponse = response.getErrorResponse();
      throw new ErrorResponseException(errorResponse);
    }
  }

  public PeersResponse getPeers() {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountDetail(accountId, writer, key)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    val adr = res.getAccountDetailResponse();

    return adr.getDetail();
  }

  public String getAccountDetails(
      String accountId,
      String writer,
      String key,
      Integer pageSize,
      String accountDetailRecordIdWriter,
      String accountDetailRecordIdKey
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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

    val adr = res.getAccountDetailResponse();

    return adr.getDetail();
  }

  public String getAccountDetails(
      String accountId,
      String writer,
      String key,
      Integer pageSize
  ) {
    return getAccountDetails(accountId, writer, key, pageSize, null, null);
  }

  public AccountResponse getAccount(String accountId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccount(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountResponse();
  }

  public BlockResponse getBlock(Long height) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getBlock(height)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getBlockResponse();
  }

  public TransactionsPageResponse getAccountTransactions(String accountId, Integer pageSize,
      String firstHashHex) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountTransactions(accountId, pageSize, firstHashHex)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }

  public TransactionsPageResponse getAccountTransactions(String accountId, Integer pageSize) {
    return getAccountTransactions(accountId, pageSize, null);
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId, String assetId,
      Integer pageSize, String firstHashHex) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountAssetTransactions(accountId, assetId, pageSize, firstHashHex)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId, String assetId,
      Integer pageSize) {
    return getAccountAssetTransactions(accountId, assetId, pageSize, null);
  }

  public TransactionsResponse getTransactions(List<byte[]> hashes) {
    return getTransactions(
        hashes.stream()
            .map(Utils::toHex)
            .collect(Collectors.toList())
    );
  }

  public TransactionsResponse getTransactions(Iterable<String> hashes) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getTransactions(hashes)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public AssetResponse getAssetInfo(String assetId) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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
    val q = Query.builder(this.accountId, counter.getAndIncrement())
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
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getPendingTransactions()
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public TransactionsResponse getPendingTransactions(
      Integer pageSize,
      String firstHashHex
  ) {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getPendingTransactions(pageSize, firstHashHex)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public TransactionsResponse getPendingTransactions(
      Integer pageSize
  ) {
    return getPendingTransactions(pageSize, null);
  }
}
