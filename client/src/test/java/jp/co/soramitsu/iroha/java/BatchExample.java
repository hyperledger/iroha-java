package jp.co.soramitsu.iroha.java;

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultAccountId;
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair;

import iroha.protocol.TransactionOuterClass;
import java.util.ArrayList;
import jp.co.soramitsu.iroha.java.subscription.WaitForTerminalStatus;
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer;
import lombok.val;

/**
 * Example of ATOMIC batch.
 */
public class BatchExample {

  private static WaitForTerminalStatus waiter = new WaitForTerminalStatus();

  private static TransactionStatusObserver statusObserver = TransactionStatusObserver.builder()
      .onTransactionFailed(t -> System.out.println(String.format(
          "transaction %s failed with msg: %s",
          t.getTxHash(),
          t.getErrOrCmdName()
      )))
      // executed when got any exception in handlers or grpc
      .onError(e -> System.out.println("Failed with exception: " + e))
      // executed when we receive "committed" status
      .onTransactionCommitted((t) -> System.out.println("Committed :)"))
      // executed when transfer is complete (failed or succeed) and observable is closed
      .onComplete(() -> System.out.println("Complete"))
      .build();


  public static void main(String[] args) {
    IrohaContainer irohaContainer = new IrohaContainer()
        .withLogger(null);

    irohaContainer.start();
    IrohaAPI irohaAPI = irohaContainer.getApi();

    // create 1st transactions
    val tx1 = Transaction.builder(defaultAccountId)
        .setAccountDetail(defaultAccountId, "key1", "value1")
        .sign(defaultKeyPair)
        .build();

    // create 2nd transactions
    val tx2 = Transaction.builder(defaultAccountId)
        .setAccountDetail(defaultAccountId, "key2", "value2")
        .sign(defaultKeyPair)
        .build();

    // add txs to list
    val lst = new ArrayList<TransactionOuterClass.Transaction>();
    lst.add(tx1);
    lst.add(tx2);

    // convert list to batch
    val atomicBatch = Utils.createTxAtomicBatch(lst, defaultKeyPair);

    // send
    irohaAPI.transactionListSync(atomicBatch);

    // wait for terminal status of all txs
    for (TransactionOuterClass.Transaction tx : atomicBatch) {
      val hash = Utils.hash(tx);
      waiter.subscribe(irohaAPI, hash)
          .blockingSubscribe(statusObserver);
    }

    // build protobuf query, sign it
    val q = Query.builder(defaultAccountId, 1)
        .getAccountDetail(defaultAccountId, null, null)
        .buildSigned(defaultKeyPair);

    // execute query, get response
    val res = irohaAPI.query(q);

    System.out.println(res.getAccountDetailResponse().getDetail());

    irohaContainer.stop();
  }

}
