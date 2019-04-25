package jp.co.soramitsu.iroha.java;

import iroha.protocol.QryResponses.AccountAssetResponse;
import iroha.protocol.QryResponses.AccountResponse;
import iroha.protocol.QryResponses.AssetResponse;
import iroha.protocol.QryResponses.BlockResponse;
import iroha.protocol.QryResponses.ErrorResponse;
import iroha.protocol.QryResponses.QueryResponse;
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

  private AtomicInteger counter = new AtomicInteger(1);

  private void checkErrorResponse(QueryResponse response) throws ErrorResponseException {
    if (response.hasErrorResponse()) {
      ErrorResponse errorResponse = response.getErrorResponse();
      throw new ErrorResponseException(errorResponse.getErrorCode(),
          errorResponse.getReason().toString(), errorResponse.getMessage());
    }
  }

  public String getAccountDetails(
      String accountId,
      String writer,
      String key
  ) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountDetail(accountId, writer, key)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    val adr = res.getAccountDetailResponse();

    return adr.getDetail();
  }

  public AccountResponse getAccount(String accountId) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccount(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountResponse();
  }

  public BlockResponse getBlock(Long height) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getBlock(height)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getBlockResponse();
  }

  public TransactionsPageResponse getAccountTransactions(String accountId, Integer pageSize,
      String firstHashHex) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountTransactions(accountId, pageSize, firstHashHex)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }

  public TransactionsPageResponse getAccountTransactions(String accountId, Integer pageSize)
      throws ErrorResponseException {
    return getAccountTransactions(accountId, pageSize, null);
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId, String assetId,
      Integer pageSize, String firstHashHex) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountAssetTransactions(accountId, assetId, pageSize, firstHashHex)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsPageResponse();
  }

  public TransactionsPageResponse getAccountAssetTransactions(String accountId, String assetId,
      Integer pageSize) throws ErrorResponseException {
    return getAccountAssetTransactions(accountId, assetId, pageSize, null);
  }

  public TransactionsResponse getTransactions(List<byte[]> hashes) throws ErrorResponseException {
    return getTransactions(
        hashes.stream()
            .map(Utils::toHex)
            .collect(Collectors.toList())
    );
  }

  public TransactionsResponse getTransactions(Iterable<String> hashes)
      throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getTransactions(hashes)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getTransactionsResponse();
  }

  public AssetResponse getAssetInfo(String assetId) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAssetInfo(assetId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAssetResponse();
  }

  public AccountAssetResponse getAccountAssets(String accountId) throws ErrorResponseException {
    val q = Query.builder(this.accountId, counter.getAndIncrement())
        .getAccountAssets(accountId)
        .buildSigned(keyPair);

    val res = api.query(q);

    checkErrorResponse(res);

    return res.getAccountAssetsResponse();
  }
}
