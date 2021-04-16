package jp.co.soramitsu.iroha2;

import java.security.KeyPair;
import java.util.concurrent.Future;
import jp.co.soramitsu.iroha2.TransactionTerminalStatusWebSocketListener.TerminalStatus;
import jp.co.soramitsu.iroha2.model.AccountId;
import jp.co.soramitsu.iroha2.model.AssetId;
import jp.co.soramitsu.iroha2.model.Bool;
import jp.co.soramitsu.iroha2.model.DefinitionId;
import jp.co.soramitsu.iroha2.model.Domain;
import jp.co.soramitsu.iroha2.model.DomainName;
import jp.co.soramitsu.iroha2.model.Id;
import jp.co.soramitsu.iroha2.model.Identifiable;
import jp.co.soramitsu.iroha2.model.Transaction;
import jp.co.soramitsu.iroha2.model.U32;
import jp.co.soramitsu.iroha2.model.V1Transaction;
import jp.co.soramitsu.iroha2.model.Value;
import jp.co.soramitsu.iroha2.model.expression.Expression;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.instruction.Burn;
import jp.co.soramitsu.iroha2.model.instruction.Fail;
import jp.co.soramitsu.iroha2.model.instruction.If;
import jp.co.soramitsu.iroha2.model.instruction.Instruction;
import jp.co.soramitsu.iroha2.model.instruction.Mint;
import jp.co.soramitsu.iroha2.model.instruction.Register;
import jp.co.soramitsu.iroha2.model.instruction.Sequence;
import jp.co.soramitsu.iroha2.model.instruction.Transfer;
import jp.co.soramitsu.iroha2.model.instruction.Unregister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(5)
public class InstructionTest {

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
      Assertions.assertTrue(result.get().isCommitted());
    });
  }

  /**
   * Asserts that transaction with instruction was rejected
   */
  private <T> void assertInstructionRejected(Instruction instruction) {
    Assertions.assertDoesNotThrow(() -> {
      Transaction transaction = new TransactionBuilder()
          .setCreator("alice", "wonderland")
          .addInstruction(instruction)
          .build()
          .sign(keyPair)
          .build();

      Future<TerminalStatus> result = api.instructionAsync(new V1Transaction(transaction));
      Assertions.assertFalse(result.get().isCommitted());
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
        new AssetId(new DefinitionId("rose", "wonderland"), new AccountId("alice", "wonderland"))
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
  public void testFail() {
    String reason = "test fail reason";
    Fail fail = new Fail(reason);
    assertInstructionRejected(fail);
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
