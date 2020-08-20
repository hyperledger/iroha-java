package jp.co.soramitsu.iroha.java;

import static jp.co.soramitsu.iroha.java.Utils.nonNull;
import static jp.co.soramitsu.iroha.java.detail.Const.accountIdDelimiter;

import iroha.protocol.Commands;
import iroha.protocol.Commands.AddAssetQuantity;
import iroha.protocol.Commands.AddPeer;
import iroha.protocol.Commands.AddSignatory;
import iroha.protocol.Commands.AppendRole;
import iroha.protocol.Commands.Command;
import iroha.protocol.Commands.CompareAndSetAccountDetail;
import iroha.protocol.Commands.CreateAccount;
import iroha.protocol.Commands.CreateAsset;
import iroha.protocol.Commands.CreateDomain;
import iroha.protocol.Commands.CreateRole;
import iroha.protocol.Commands.DetachRole;
import iroha.protocol.Commands.GrantPermission;
import iroha.protocol.Commands.RemovePeer;
import iroha.protocol.Commands.RemoveSignatory;
import iroha.protocol.Commands.RevokePermission;
import iroha.protocol.Commands.SetAccountDetail;
import iroha.protocol.Commands.SetAccountQuorum;
import iroha.protocol.Commands.SubtractAssetQuantity;
import iroha.protocol.Commands.TransferAsset;
import iroha.protocol.Primitive.GrantablePermission;
import iroha.protocol.Primitive.Peer;
import iroha.protocol.Primitive.RolePermission;
import iroha.protocol.TransactionOuterClass;
import iroha.protocol.TransactionOuterClass.Transaction.Payload.BatchMeta.BatchType;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.CryptoException;
import jp.co.soramitsu.iroha.java.detail.BuildableAndSignable;
import lombok.val;
import static jp.co.soramitsu.iroha.java.ValidationException.Type.NOT_ALLOWED;

public class TransactionBuilder {

  private FieldValidator validator;
  private Transaction tx;

  private void init(String accountId, Long time) {
    tx = new Transaction();
    if (nonNull(accountId)) {
      setCreatorAccountId(accountId);
    }

    if (nonNull(time)) {
      setCreatedTime(time);
    }

    setQuorum(1 /* default value */);

    this.validator = new FieldValidator();
  }

  /**
   * Both fields are required, therefore we can not create builder without them. However, in genesis
   * block they can be null.
   */
  public TransactionBuilder(String accountId, Instant time) {
    init(accountId, time.toEpochMilli());
  }

  public TransactionBuilder(String accountId, Date time) {
    init(accountId, time.getTime());
  }

  public TransactionBuilder(String accountId, Long time) {
    init(accountId, time);
  }

  /* default */ TransactionBuilder(Transaction transaction) {
    tx = transaction;
  }

  public TransactionBuilder disableValidation() {
    this.validator = null;
    return this;
  }

  public TransactionBuilder setCreatorAccountId(String accountId) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    tx.reducedPayload.setCreatorAccountId(accountId);
    return this;
  }

  public TransactionBuilder setCreatedTime(Long time) {
    if (nonNull(this.validator)) {
      this.validator.checkTimestamp(time);
    }

    tx.reducedPayload.setCreatedTime(time);
    return this;
  }

  public TransactionBuilder setCreatedTime(Date time) {
    return setCreatedTime(time.getTime());
  }

  public TransactionBuilder setCreatedTime(Instant time) {
    return setCreatedTime(time.toEpochMilli());
  }

  public TransactionBuilder setQuorum(int quorum) {
    if (nonNull(this.validator)) {
      this.validator.checkQuorum(quorum);
    }

    tx.reducedPayload.setQuorum(quorum);
    return this;
  }

  public TransactionBuilder setSettingValue(String key, String value) {
    throw new ValidationException(NOT_ALLOWED, "Command can be used in genesis block only");
  }

  public TransactionBuilder callEngine(String caller, String optCallee, String input) {
    if (nonNull(validator)) {
      validator.checkAccountId(caller);
      validator.checkHexString(input);
    }

    val b = Commands.CallEngine
            .newBuilder()
            .setCaller(caller)
            .setInput(input);

    if (nonNull(optCallee)) {
      if (nonNull(validator)) {
        validator.checkEvmAddress(optCallee);
      }
      b.setCallee(optCallee);
    }


    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setCallEngine(
                b.build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder createAccount(
      String accountName,
      String domainid,
      byte[] publicKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccount(accountName);
      this.validator.checkDomain(domainid);
      this.validator.checkPublicKey(publicKey);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setCreateAccount(
                CreateAccount.newBuilder()
                    .setAccountName(accountName)
                    .setDomainId(domainid)
                    .setPublicKey(Utils.toHex(publicKey)).build()
            ).build()
    );

    return this;
  }

  public TransactionBuilder createAccount(
      String accountName,
      String domainid,
      PublicKey publicKey
  ) {
    return createAccount(
        accountName,
        domainid,
        publicKey.getEncoded()
    );
  }

  public TransactionBuilder createAccount(
      String accountId,
      PublicKey publicKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    val t = accountId.split(accountIdDelimiter);

    return createAccount(
        t[0],
        t[1],
        publicKey.getEncoded()
    );
  }

  public TransactionBuilder transferAsset(
      String sourceAccount,
      String destinationAccount,
      String assetId,
      String description,
      String amount
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(sourceAccount);
      this.validator.checkAccountId(destinationAccount);
      this.validator.checkAssetId(assetId);
      this.validator.checkDescription(description);
      this.validator.checkAmount(amount);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setTransferAsset(
                TransferAsset.newBuilder()
                    .setSrcAccountId(sourceAccount)
                    .setDestAccountId(destinationAccount)
                    .setAssetId(assetId)
                    .setDescription(description)
                    .setAmount(amount)
                    .build()
            ).build()
    );

    return this;
  }

  public TransactionBuilder transferAsset(
      String sourceAccount,
      String destinationAccount,
      String assetId,
      String description,
      BigDecimal amount
  ) {
    return transferAsset(
        sourceAccount,
        destinationAccount,
        assetId,
        description,
        amount.toPlainString()
    );
  }

  public TransactionBuilder setAccountDetail(
      String accountId,
      String key,
      String value
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkAccountDetailsKey(key);
      this.validator.checkAccountDetailsValue(value);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setSetAccountDetail(
                SetAccountDetail.newBuilder()
                    .setAccountId(accountId)
                    .setKey(key)
                    .setValue(value)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder addPeer(
      String address,
      byte[] peerKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkPeerAddress(address);
      this.validator.checkPublicKey(peerKey);
    }

    val hex = DatatypeConverter.printHexBinary(peerKey);

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setAddPeer(
                AddPeer.newBuilder()
                    .setPeer(
                        Peer.newBuilder()
                            .setAddress(address)
                            .setPeerKey(Utils.toHex(peerKey))
                    ).build()
            ).build()
    );

    return this;
  }

  public TransactionBuilder addPeer(
      String address,
      PublicKey peerKey
  ) {
    return addPeer(address, peerKey.getEncoded());
  }

  public TransactionBuilder removePeer(
      byte[] peerKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkPublicKey(peerKey);
    }

    val hex = DatatypeConverter.printHexBinary(peerKey);

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setRemovePeer(
                RemovePeer.newBuilder()
                    .setPublicKey(Utils.toHex(peerKey))
                    .build()
            )
    );

    return this;
  }

  public TransactionBuilder removePeer(
      PublicKey peerKey
  ) {
    return removePeer(peerKey.getEncoded());
  }

  public TransactionBuilder grantPermission(
      String accountId,
      GrantablePermission permission
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setGrantPermission(
                GrantPermission.newBuilder()
                    .setAccountId(accountId)
                    .setPermission(permission)
                    .build()
            ).build()
    );

    return this;
  }

  public TransactionBuilder grantPermissions(
      String accountId,
      Iterable<GrantablePermission> permissions
  ) {
    for (GrantablePermission p : permissions) {
      this.grantPermission(accountId, p);
    }
    return this;
  }

  public TransactionBuilder createRole(
      String roleName,
      Iterable<? extends RolePermission> permissions
  ) {

    tx.reducedPayload.addCommands(
        Command.newBuilder().setCreateRole(
            CreateRole.newBuilder()
                .setRoleName(roleName)
                .addAllPermissions(permissions)
                .build()
        ).build()
    );

    return this;
  }

  public TransactionBuilder createDomain(
      String domainId,
      String defaultRole
  ) {

    if (nonNull(this.validator)) {
      this.validator.checkDomain(domainId);
      this.validator.checkRoleName(defaultRole);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setCreateDomain(
                CreateDomain.newBuilder()
                    .setDomainId(domainId)
                    .setDefaultRole(defaultRole)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder appendRole(
      String accountId,
      String roleName
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkRoleName(roleName);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setAppendRole(
                AppendRole.newBuilder()
                    .setAccountId(accountId)
                    .setRoleName(roleName)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder createAsset(
      String assetName,
      String domain,
      Integer precision
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAssetName(assetName);
      this.validator.checkDomain(domain);
      this.validator.checkPrecision(precision);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setCreateAsset(
                CreateAsset.newBuilder()
                    .setAssetName(assetName)
                    .setDomainId(domain)
                    .setPrecision(precision)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder addAssetQuantity(
      String assetId,
      String amount
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAssetId(assetId);
      this.validator.checkAmount(amount);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setAddAssetQuantity(
                AddAssetQuantity.newBuilder()
                    .setAssetId(assetId)
                    .setAmount(amount)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder addAssetQuantity(String assetId, BigDecimal amount) {
    return this.addAssetQuantity(assetId, amount.toPlainString());
  }

  public TransactionBuilder addSignatory(String accountId, PublicKey publicKey) {
    return this.addSignatory(accountId, publicKey.getEncoded());
  }

  public TransactionBuilder addSignatory(
      String accountId,
      byte[] publicKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkPublicKey(publicKey);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setAddSignatory(
                AddSignatory.newBuilder()
                    .setAccountId(accountId)
                    .setPublicKey(Utils.toHex(publicKey))
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder detachRole(
      String accountId,
      String roleName
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkRoleName(roleName);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setDetachRole(
                DetachRole.newBuilder()
                    .setAccountId(accountId)
                    .setRoleName(roleName)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder removeSignatory(
      String accountId,
      byte[] publicKey
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkPublicKey(publicKey);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setRemoveSignatory(
                RemoveSignatory.newBuilder()
                    .setAccountId(accountId)
                    .setPublicKey(Utils.toHex(publicKey))
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder removeSignatory(String accountId, PublicKey publicKey) {
    return this.removeSignatory(accountId, publicKey.getEncoded());
  }

  public TransactionBuilder revokePermission(
      String accountId,
      GrantablePermission permission
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setRevokePermission(
                RevokePermission.newBuilder()
                    .setAccountId(accountId)
                    .setPermission(permission)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder subtractAssetQuantity(
      String assetId,
      String amount
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAssetId(assetId);
      this.validator.checkAmount(amount);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setSubtractAssetQuantity(
                SubtractAssetQuantity.newBuilder()
                    .setAssetId(assetId)
                    .setAmount(amount)
                    .build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder setAccountQuorum(
      String accountId,
      int quorum
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkQuorum(quorum);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setSetAccountQuorum(
                SetAccountQuorum.newBuilder()
                    .setAccountId(accountId)
                    .setQuorum(quorum)
                    .build()
            )
            .build()
    );

    return this;

  }

  public TransactionBuilder subtractAssetQuantity(String assetId, BigDecimal amount) {
    return this.subtractAssetQuantity(assetId, amount.toPlainString());
  }

  public TransactionBuilder setBatchMeta(BatchType batchType, Iterable<String> hashes) {
    tx.batchMeta.setType(batchType);
    tx.batchMeta.addAllReducedHashes(hashes);
    tx.updateBatch();

    return this;
  }

  /**
   * Compare the old value before set the new one
   * @param accountId - to set value in details
   * @param key - key
   * @param value - value to set
   * @param optOldValue - old value to check (optional)
   * @param optCheckValue - if true, empty old_value in command must match absent value in WSV;
   *                        if false, any old_value in command matches absent in WSV (legacy)
   */
  public TransactionBuilder compareAndSetAccountDetail(
      String accountId,
      String key,
      String value,
      String optOldValue,
      Boolean optCheckEmpty
  ) {
    if (nonNull(this.validator)) {
      this.validator.checkAccountId(accountId);
      this.validator.checkAccountDetailsKey(key);
      this.validator.checkAccountDetailsValue(value);
    }
    val b = CompareAndSetAccountDetail
        .newBuilder()
        .setAccountId(accountId)
        .setKey(key)
        .setValue(value);

    if (nonNull(optOldValue)) {
      if (nonNull(this.validator)) {
        this.validator.checkAccountDetailsValue(optOldValue);
      }
      b.setOldValue(optOldValue);
    }

    if (nonNull(optCheckEmpty)) {
      b.setCheckEmpty(optCheckEmpty);
    }

    tx.reducedPayload.addCommands(
        Command.newBuilder()
            .setCompareAndSetAccountDetail(
                b.build()
            )
            .build()
    );

    return this;
  }

  public TransactionBuilder setBatchMeta(Iterable<Transaction> transactions, BatchType batchType) {
    return setBatchMeta(batchType, Utils.getBatchHashesHex(transactions));
  }

  public BuildableAndSignable<TransactionOuterClass.Transaction> sign(KeyPair keyPair)
      throws CryptoException {
    return tx.sign(keyPair);
  }

  public Transaction build() {
    tx.updatePayload();
    return tx;
  }
}
