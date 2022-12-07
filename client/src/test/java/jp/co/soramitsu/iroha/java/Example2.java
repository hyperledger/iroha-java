package jp.co.soramitsu.iroha.java;

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultAccountId;
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultDomainName;
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair;

import iroha.protocol.Commands.Command.CommandCase;
import iroha.protocol.Commands.CreateAccount;
import iroha.protocol.Commands.CreateAsset;
import iroha.protocol.Endpoint.ToriiResponse;
import iroha.protocol.Primitive.RolePermission;
import java.util.Collections;
import jp.co.soramitsu.iroha.java.routers.CmdRouter;
import jp.co.soramitsu.iroha.java.routers.TxStatusRouter;
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer;
import lombok.val;

public class Example2 {

  /**
   * Case Study: I want to send a transaction, then when it is committed I want to log content of
   * transaction commands, depending on their type.
   */
  public static void main(String[] args) {
    // lets start Iroha without logger
    IrohaContainer iroha = new IrohaContainer()
        .withLogger(null /* disable logger */);

    iroha.start();
    IrohaAPI api = iroha.getApi();

    // create a transaction
    val tx = Transaction.builder(defaultAccountId, FieldValidator.defaultConfig)
        .createAccount("0", defaultDomainName, defaultKeyPair.getPublic())
        .createAsset("usd", defaultDomainName, 2)
        .createRole("role", Collections.singletonList(RolePermission.can_add_asset_qty))
        .sign(defaultKeyPair)
        .build();

    // define command router
    val cmdRouter = new CmdRouter();
    val r = new TxStatusRouter();

    // handle CREATE_ACCOUNT command
    cmdRouter.handle(CommandCase.CREATE_ACCOUNT, cmd -> {
      CreateAccount c = cmd.getCreateAccount();
      System.out.printf("[create account] name=%s domain=%s publickey=%s%n",
          c.getAccountName(),
          c.getDomainId(),
          c.getPublicKey());
    });

    // handle CREATE_ASSET
    cmdRouter.handle(CommandCase.CREATE_ASSET, cmd -> {
      CreateAsset c = cmd.getCreateAsset();
      System.out.printf("[create asset] name=%s domain=%s precision=%d%n",
          c.getAssetName(),
          c.getDomainId(),
          c.getPrecision());
    });

    val observer = TransactionStatusObserver.builder()
        .onTransactionCommitted((ToriiResponse tr) -> {
          System.out.println("Received COMMITTED for transaction " + tr.getTxHash());

          // process all commands for each committed transaction
          tx.getPayload()
              .getReducedPayload()
              .getCommandsList()
              .forEach(cmdRouter::process);
        })
        .build();

    api.transaction(tx)
        .blockingSubscribe(observer);

    iroha.stop();
  }
}
