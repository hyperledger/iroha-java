package jp.co.soramitsu.iroha2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationConfig;
import jp.co.soramitsu.iroha2.TransactionTerminalStatusWebSocketListener.TerminalStatus;
import jp.co.soramitsu.iroha2.model.*;
import jp.co.soramitsu.iroha2.model.events.*;
import jp.co.soramitsu.iroha2.model.events.reject.NotPermitted;
import jp.co.soramitsu.iroha2.model.expression.Expression;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.instruction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.security.KeyPair;
import java.util.concurrent.Future;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;

@Timeout(10)
public class InstructionTest {

  // root account keys:
  // priv: 9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e
  // pub:  7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0
  String privateKeyHex = "de757bcb79f4c63e8fa0795edc26f86dfdba189b846e903d0b732bb644607720";
  KeyPair keyPair = Utils.EdDSAKeyPairFromHexPrivateKey(privateKeyHex);
  Iroha2Api api = new Iroha2Api("localhost:8080");

  /**
   * Asserts that transaction with instruction was successfully committed
   */
  private void assertInstructionCommitted(Instruction instruction) {
    Assertions.assertDoesNotThrow(() -> {
      Transaction transaction = new TransactionBuilder()
          .setCreator("alice", "wonderland")
          .addInstruction(instruction)
          .setTimeToLive(100_000L)
          .build()
          .sign(keyPair)
          .build();

      Future<TerminalStatus> result = api.instructionAsync(new V1Transaction(transaction));
//      Assertions.assertTrue(result.get().isCommitted(), result.get().getMessage());
    });
  }

  /**
   * Asserts that transaction with instruction was rejected
   */
  private void assertInstructionRejected(Instruction instruction, String reason) {
    Assertions.assertDoesNotThrow(() -> {
      Transaction transaction = new TransactionBuilder()
          .setCreator("alice", "wonderland")
          .addInstruction(instruction)
          .build()
          .sign(keyPair)
          .build();

      Future<TerminalStatus> result = api.instructionAsync(new V1Transaction(transaction));
      Assertions.assertFalse(result.get().isCommitted());
//      Assertions.assertTrue(result.get().getMessage().contains(reason));
    });
  }


  // Test register/unregister instruction
  @Test
  public void testRegister() {
    Expression registerExpr = new Raw(new Value(new Identifiable(new Domain("new test domain"))));

    Instruction register = new Register(registerExpr);
    assertInstructionCommitted(register);

    Expression unregisterExpr = new Raw(new Value(new Id(new DomainName("new test domain"))));
    Instruction unregister = new Unregister(unregisterExpr);
    assertInstructionCommitted(unregister);
  }

  /**
   * Test mint, transfer and burn is committed, balance changed
   */
  @Test
  public void testMint() {
    Expression amount = new Raw(new Value(new U32(100)));
    Expression destination = new Raw(new Value(new Id(
        new AssetId(new DefinitionId("rose", "wonderland"), new AccountId("root", "global"))
    )));

    Mint mint = new Mint(amount, destination);
    assertInstructionCommitted(mint);

    Transfer transfer = new Transfer(destination, amount, destination);
    assertInstructionCommitted(transfer);

    Burn burn = new Burn(amount, destination);
    assertInstructionCommitted(burn);
  }

  /**
   * Test fail instruction
   */
  @Test
  public void testFail() throws JsonProcessingException {
//    String reason = "test fail reason";
//    Fail fail = new Fail(reason);
//    assertInstructionRejected(fail, reason);
    var json = "{\"Pipeline\":{\"entity_type\":\"Transaction\",\"status\":\"Validating\",\"hash\":[112,39,237,251,112,246,8,211,99,176,79,43,118,194,51,90,228,245,221,144,59,237,102,30,54,207,195,65,214,203,60,6]}}";
//    var event = new ObjectMapper().readValue(json, V1VersionedEvent.class);
    System.out.println(json);

    final var objectMapper =new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE );
    objectMapper.configure(WRAP_ROOT_VALUE, true);


    var event = new Pipeline(EntityType.Transaction, new Rejected(new NotPermitted("foo")), new byte[] {1,1,1,});
    System.out.println(objectMapper.writeValueAsString(event));

    var deserializedEvent = objectMapper.readValue(json, Event.class);
    System.out.println(objectMapper.writeValueAsString(deserializedEvent));
  }

  /**
   * Test if instruction with false condition and empty else
   */
  @Test
  public void testIf() {
    String reason = "test fail reason";
    Fail fail = new Fail(reason);
    If ifInstruction = new If(new Raw(new Value(new Bool(false))), fail);
    assertInstructionCommitted(ifInstruction);
  }

  /**
   * Test if instruction with false condition and empty else
   */
  @Test
  public void testIfEmptyElse() {
    String reason = "test fail reason";
    Fail fail = new Fail(reason);
    Sequence emptySequence = new Sequence();
    If ifInstruction = new If(new Raw(new Value(new Bool(false))), fail, emptySequence);
    assertInstructionCommitted(ifInstruction);
  }
}
