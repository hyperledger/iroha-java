package jp.co.soramitsu.iroha.java;

import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import static jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.privateKeyFromBytes;
import static jp.co.soramitsu.crypto.ed25519.Ed25519Sha3.publicKeyFromBytes;

import iroha.protocol.BlockOuterClass.Block;
import iroha.protocol.BlockOuterClass.Block_v1;
import iroha.protocol.Endpoint.TxList;
import iroha.protocol.Endpoint.TxStatusRequest;
import iroha.protocol.Queries;
import iroha.protocol.TransactionOuterClass;
import iroha.protocol.TransactionOuterClass.Transaction.Payload.BatchMeta.BatchType;
import java.security.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.xml.bind.DatatypeConverter;
import lombok.val;
import org.bouncycastle.jcajce.provider.digest.SHA3;

public class Utils {

  public static final String IROHA_FRIENDLY_QUOTE = "\\\"";
  public static final String IROHA_FRIENDLY_NEW_LINE = "\\n";

  /**
   * Parse a keypair from hex strings
   *
   * @param hexPublicKey 64-byte (128-symbol) hexstring, public key
   * @param hexPrivateKey 64-byte (128-symbol) hexstring, private key
   * @return Ed25519-Sha3 KeyPair instance
   */
  public static KeyPair parseHexKeypair(String hexPublicKey, String hexPrivateKey) {
    return new KeyPair(
        parseHexPublicKey(hexPublicKey),
        parseHexPrivateKey(hexPrivateKey)
    );
  }

  /**
   * Parse a public key from hexstring
   *
   * @param hexPublicKey 64-byte (128-symbol) hexstring, public key
   * @return Ed25519-Sha3 PublicKey instance
   */
  public static PublicKey parseHexPublicKey(String hexPublicKey) {
    return publicKeyFromBytes(parseHexBinary(hexPublicKey));
  }

  /**
   * Parse a private key from hexstring
   *
   * @param hexPrivateKey 64-byte (128-symbol) hexstring, private key
   * @return Ed25519-Sha3 PrivateKey instance
   */
  public static PrivateKey parseHexPrivateKey(String hexPrivateKey) {
    return privateKeyFromBytes(parseHexBinary(hexPrivateKey));
  }

  /**
   * Calculate SHA3-256 reduced hash of {@link Transaction}
   *
   * @param tx IPJ transaction
   * @return 32 bytes hash
   */
  public static byte[] reducedHash(Transaction tx) {
    return reducedHash(tx.reducedPayload.build());
  }

  /**
   * Calculate SHA3-256 reduced hash of {@link iroha.protocol.TransactionOuterClass.Transaction}
   *
   * @param tx Protobuf transaction
   * @return 32 bytes hash
   */
  public static byte[] reducedHash(TransactionOuterClass.Transaction tx) {
    return reducedHash(tx.getPayload().getReducedPayload());
  }

  /**
   * Calculate SHA3-256 hash of {@link iroha.protocol.TransactionOuterClass.Transaction.Payload.ReducedPayload}
   *
   * @param reducedPayload Protobuf of ReducedPayload
   * @return 32 bytes hash
   */
  public static byte[] reducedHash(
      TransactionOuterClass.Transaction.Payload.ReducedPayload reducedPayload) {
    val sha3 = new SHA3.Digest256();
    val data = reducedPayload.toByteArray();
    return sha3.digest(data);
  }

  /**
   * Calculate SHA3-256 hash of {@link iroha.protocol.TransactionOuterClass.Transaction}
   *
   * @param tx Protobuf Transaction
   * @return 32 bytes hash
   */
  public static byte[] hash(TransactionOuterClass.Transaction tx) {
    val sha3 = new SHA3.Digest256();
    val data = tx.getPayload().toByteArray();
    return sha3.digest(data);
  }

  /**
   * Calculate SHA3-256 hash of {@link Block_v1}
   *
   * @param b BlockV1
   * @return 32 bytes hash
   */
  public static byte[] hash(Block_v1 b) {
    val sha3 = new SHA3.Digest256();
    val data = b.getPayload().toByteArray();
    return sha3.digest(data);
  }

  /**
   * Calculate SHA3-256 hash of {@link Block}
   *
   * @param b Protobuf Block
   * @return 32 bytes hash
   */
  public static byte[] hash(Block b) {
    switch (b.getBlockVersionCase()) {
      case BLOCK_V1:
        return hash(b.getBlockV1());
      default:
        throw new IllegalArgumentException(
            String.format("Block has undefined version: %s", b.getBlockVersionCase()));
    }
  }

  /**
   * Calculate SHA3-256 hash of {@link Queries.Query}
   *
   * @param q Protobuf Query
   * @return 32 bytes hash
   */
  public static byte[] hash(Queries.Query q) {
    val sha3 = new SHA3.Digest256();
    val data = q.getPayload().toByteArray();
    return sha3.digest(data);
  }

  /**
   * This method is here only because some old versions of Android do not have Objects.nonNull
   *
   * @param obj any object
   * @return true if object is not null
   */
  public static boolean nonNull(Object obj) {
    return obj != null;
  }

  /**
   * Helper method to create {@link TxStatusRequest} from byte array
   *
   * @param hash tx hash
   * @return {@link TxStatusRequest}
   */
  public static TxStatusRequest createTxStatusRequest(byte[] hash) {
    return TxStatusRequest.newBuilder()
        .setTxHash(Utils.toHex(hash))
        .build();
  }

  /**
   * Helper method tto create {@link TxList} from iterable
   *
   * @param list list of protobuf transactions
   * @return {@link TxList}
   */
  public static TxList createTxList(Iterable<TransactionOuterClass.Transaction> list) {
    return TxList.newBuilder()
        .addAllTransactions(list)
        .build();
  }

  /**
   * Create Ordered Batch of transactions created by single user from iterable
   */
  public static Iterable<TransactionOuterClass.Transaction> createTxOrderedBatch(
      Iterable<TransactionOuterClass.Transaction> list, KeyPair keyPair) {
    return createBatch(list, BatchType.ORDERED, keyPair);
  }

  /**
   * Create unsigned Ordered Batch of any transactions from iterable
   */
  public static Iterable<Transaction> createTxUnsignedOrderedBatch(Iterable<Transaction> list) {
    return createBatch(list, BatchType.ORDERED);
  }

  /**
   * Create Atomic Batch of transactions created by single user from iterable
   */
  public static Iterable<TransactionOuterClass.Transaction> createTxAtomicBatch(
      Iterable<TransactionOuterClass.Transaction> list, KeyPair keyPair) {
    return createBatch(list, BatchType.ATOMIC, keyPair);
  }

  /**
   * Create unsigned Atomic Batch of any signed transactions from iterable
   */
  public static Iterable<Transaction> createTxUnsignedAtomicBatch(Iterable<Transaction> list) {
    return createBatch(list, BatchType.ATOMIC);
  }

  /**
   * Convert bytes to hexstring
   */
  public static String toHex(byte[] b) {
    return DatatypeConverter.printHexBinary(b);
  }

  /**
   * Get transaction hash hexstring
   * @param transaction
   * @return lowercase hexstring
   */
  public static String toHexHash(TransactionOuterClass.Transaction transaction) {
    return DatatypeConverter.printHexBinary(hash(transaction)).toLowerCase();
  }

  /**
   * Get query hash hexstring
   * @param query
   * @return lowercase hexstring
   */
  public static String toHexHash(Queries.Query query) {
    return DatatypeConverter.printHexBinary(hash(query)).toLowerCase();
  }

  /**
   * Get block_v1 hash hexstring
   * @param block_v1
   * @return lowercase hexstring
   */
  public static String toHexHash(Block_v1 block_v1) {
    return DatatypeConverter.printHexBinary(hash(block_v1)).toLowerCase();
  }

  /**
   * Get block hash hexstring
   * @param block
   * @return lowercase hexstring
   */
  public static String toHexHash(Block block) {
    return DatatypeConverter.printHexBinary(hash(block)).toLowerCase();
  }

  public static Iterable<String> getProtoBatchHashesHex(
      Iterable<TransactionOuterClass.Transaction> list) {
    return StreamSupport.stream(list.spliterator(), false)
        .map(tx -> toHex(reducedHash(tx)))
        .collect(Collectors.toList());
  }

  private static Iterable<TransactionOuterClass.Transaction> createBatch(
      Iterable<TransactionOuterClass.Transaction> list, BatchType batchType, KeyPair keyPair) {
    final Iterable<String> batchHashes = getProtoBatchHashesHex(list);
    return StreamSupport.stream(list.spliterator(), false)
        .map(tx -> Transaction
            .parseFrom(tx)
            .makeMutable()
            .setBatchMeta(batchType, batchHashes)
            .sign(keyPair)
            .build()
        )
        .collect(Collectors.toList());
  }

  public static Iterable<String> getBatchHashesHex(Iterable<Transaction> list) {
    return StreamSupport.stream(list.spliterator(), false)
        .map(tx -> toHex(reducedHash(tx)))
        .collect(Collectors.toList());
  }

  private static Iterable<Transaction> createBatch(Iterable<Transaction> list,
      BatchType batchType) {
    final Iterable<String> batchHashes = getBatchHashesHex(list);
    return StreamSupport.stream(list.spliterator(), false)
        .map(tx -> tx
            .makeMutable()
            .setBatchMeta(batchType, batchHashes)
            .build()
        )
        .collect(Collectors.toList());
  }

  /**
   * Escapes symbols reserved in JSON so it can be used in Iroha
   *
   * @param str input string to escape
   * @return escaped string
   */
  public static String irohaEscape(String str) {
    return str.replace("\"", IROHA_FRIENDLY_QUOTE)
        .replace("\n", IROHA_FRIENDLY_NEW_LINE);
  }

  /**
   * Reverse to irohaEscape(), unescape symbols reserved in JSON so it can be used in Iroha
   *
   * @param str input escaped string
   * @return unescaped string
   */
  public static String irohaUnEscape(String str) {
    return str.replace(IROHA_FRIENDLY_QUOTE, "\"")
        .replace(IROHA_FRIENDLY_NEW_LINE, "\n");
  }
}
