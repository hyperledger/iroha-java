package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import jp.co.soramitsu.iroha2.model.*;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.instruction.*;
import jp.co.soramitsu.iroha2.scale.writer.ScaleWriterFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

/**
 * Tests SCALE serialization of Payload with all possible instructions.
 */
public class PayloadWriterTest extends ScaleWriterFixture {

  private byte[] scale(Payload payload) {
    return Assertions.assertDoesNotThrow(() -> {
      ByteArrayOutputStream encoded = new ByteArrayOutputStream();
      ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
      codec.write(new PayloadWriter(), payload);
      return encoded.toByteArray();
    });
  }

  private byte[] scale(Register payload) {
    return Assertions.assertDoesNotThrow(() -> {
      ByteArrayOutputStream encoded = new ByteArrayOutputStream();
      ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
      codec.write(new RegisterWriter(), payload);
      return encoded.toByteArray();
    });
  }


  /**
   * Compares scale serialization of register command with generated in rust one:
   * <pre>
   * {@code
   * let domain_name = "Soramitsu";
   * let create_domain = RegisterBox::new(
   *  IdentifiableBox::from(Domain::new(domain_name)),
   * );
   * }
   * </pre>
   */
  @Test
  public void testRegisterInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611662666185");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Register register = new Register(new Raw(new Value(new Identifiable(new Domain("Soramitsu")))));

    System.err.println(bytesToJsonString(scale(register)));

    Payload payload = new Payload(accountId, List.of(register), creationTime, timeToLiveMs, new Metadata(new HashMap<>()));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 0, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 201, 169, 148, 62, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }



  /**
   * Compares scale serialization of unregister command with generated in rust one:
   * <pre>
   * {@code
   * let domain_name = "Soramitsu";
   * let create_domain = UnregisterBox::new(
   *  IdentifiableBox::from(Domain::new(domain_name)),
   * );
   * }
   * </pre>
   */
  @Test
  public void testUnregisterInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611669634230");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Domain domain = new Domain("Soramitsu");
    Unregister unregister = new Unregister(new Raw(new Value(new Identifiable(domain))));

    payload.setInstructions(List.of(unregister));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 1, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 182, 252, 254, 62, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of mint command with generated in rust one:
   * <pre>
   * {@code
   *     let account_id: AccountId = AccountId::new("root", "global");
   *     let quantity: u32 = 100;
   *     let mint_asset = MintBox::new(
   *         Value::U32(quantity),
   *         IdBox::AssetId(AssetId::new(
   *             AssetDefinitionId::new("XOR", "Soramitsu"),
   *             account_id.clone(),
   *         )),
   *     );
   * }
   * </pre>
   */
  @Test
  public void testMintInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = BigInteger.ONE;
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Raw expression = new Raw(new Value(new U32(100)));
    DefinitionId definitionId = new DefinitionId("XOR", "Soramitsu");
    AssetId assetId = new AssetId(definitionId, accountId);
    Raw expression_id = new Raw(new Value(new Id(assetId)));
    Mint mint = new Mint(expression, expression_id);

    payload.setInstructions(List.of(mint));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 2, 13, 0, 100, 0, 0, 0, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of burn command with generated in rust one:
   * <pre>
   * {@code
   *     let account_id: AccountId = AccountId::new("root", "global");
   *     let quantity: u32 = 100;
   *     let burn_asset = BurnBox::new(
   *         Value::U32(quantity),
   *         IdBox::AssetId(AssetId::new(
   *             AssetDefinitionId::new("XOR", "Soramitsu"),
   *             account_id,
   *         )),
   *     );
   * }
   * </pre>
   */
  @Test
  public void testBurnInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611672005134");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Raw expression = new Raw(new Value(new U32(100)));
    DefinitionId definitionId = new DefinitionId("XOR", "Soramitsu");
    AssetId assetId = new AssetId(definitionId, accountId);
    Raw expression_id = new Raw(new Value(new Id(assetId)));
    Burn burn = new Burn(expression, expression_id);

    payload.setInstructions(List.of(burn));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 3, 13, 0, 100, 0, 0, 0, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 14, 42, 35, 63, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of transfer command with generated in rust one:
   * <pre>
   * {@code
   *     let account_id = AccountId::new("root", "global");
   *     let asset_definition_id = AssetDefinitionId { name: "XOR".to_string(), domain_name: "Soramitsu".to_string() };
   *
   *     let instruction = TransferBox::new(
   *         IdBox::AssetId(AssetId::new(
   *             asset_definition_id.clone(),
   *             account_id.clone(),
   *         )),
   *         Value::U32(100),
   *         IdBox::AssetId(AssetId::new(
   *             asset_definition_id,
   *             account_id.clone(),
   *         )),
   *     );
   * }
   * </pre>
   */
  @Test
  public void testTransferInstruction() {
    BigInteger creationTime = new BigInteger("1611673151703");
    BigInteger timeToLiveMs = BigInteger.ZERO;
    AccountId accountId = new AccountId("root", "global");

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    DefinitionId assetDefinitionId = new DefinitionId("XOR", "Soramitsu");
    Raw amount = new Raw(new Value(new U32(100)));
    AssetId assetId = new AssetId(assetDefinitionId, accountId);
    Transfer transfer = new Transfer(new Raw(new Value(new Id(assetId))), amount,
        new Raw(new Value(new Id(assetId))));

    payload.setInstructions(List.of(transfer));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 4, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 13, 0, 100, 0, 0, 0, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 215, 168, 52, 63, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of if instruction with generated in rust one:
   * <pre>
   * {@code
   *     let domain_name = "Soramitsu";
   *     let create_domain = RegisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let instruction = If::new(true, create_domain);
   * </pre>
   */
  @Test
  public void testIfInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611730695426");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Domain domain = new Domain("Soramitsu");
    Register register = new Register(new Raw(new Value(new Identifiable(domain))));
    Raw condition = new Raw(new Value(new Bool(true)));
    If ifInstruction = new If(condition, register);

    payload.setInstructions(List.of(ifInstruction));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 5, 13, 1, 1, 0, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 0, 2, 181, 162, 66, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of if instruction with generated in rust one:
   * <pre>
   * {@code
   *     let domain_name = "Soramitsu";
   *     let create_domain = RegisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let remove_domain = UnregisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let instruction = If::with_otherwise(true, create_domain, remove_domain);
   * </pre>
   */
  @Test
  public void testIfElseInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611731064806");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Domain domain = new Domain("Soramitsu");
    Register register = new Register(new Raw(new Value(new Identifiable(domain))));
    Unregister removeDomain = new Unregister(new Raw(new Value(new Identifiable(domain))));
    Raw condition = new Raw(new Value(new Bool(true)));
    If ifInstruction = new If(condition, register, removeDomain);

    payload.setInstructions(List.of(ifInstruction));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 5, 13, 1, 1, 0, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 1, 1, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 230, 87, 168, 66, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of Pair instruction with generated in rust one:
   * <pre>
   * {@code
   *     let domain_name = "Soramitsu";
   *     let create_domain = RegisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let remove_domain = UnregisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let instruction = Pair::new(create_domain, remove_domain);
   * </pre>
   */
  @Test
  public void testPairInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611732257095");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Domain domain = new Domain("Soramitsu");
    Register register = new Register(new Raw(new Value(new Identifiable(domain))));
    Unregister removeDomain = new Unregister(new Raw(new Value(new Identifiable(domain))));
    Instruction instruction = new Pair(register, removeDomain);

    payload.setInstructions(List.of(instruction));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 6, 0, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 1, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 71, 137, 186, 66, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of Sequence instruction with generated in rust one:
   * <pre>
   * {@code
   *     let domain_name = "Soramitsu";
   *     let create_domain = RegisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let remove_domain = UnregisterBox::new(
   *         IdentifiableBox::from(Domain::new(domain_name)),
   *     );
   *     let instructions: Vec<Instruction> = vec![create_domain.into(), remove_domain.into()];
   *     let instruction = Instruction::Sequence(SequenceBox::new(instructions));
   * </pre>
   */
  @Test
  public void testSequenceInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611732666904");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Domain domain = new Domain("Soramitsu");
    Register register = new Register(new Raw(new Value(new Identifiable(domain))));
    Unregister removeDomain = new Unregister(new Raw(new Value(new Identifiable(domain))));
    Instruction instruction = new Sequence(List.of(register, removeDomain));

    payload.setInstructions(List.of(instruction));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 7, 8, 0, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 1, 13, 5, 4, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 0, 0, 24, 202, 192, 66, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of Fail instruction with generated in rust one:
   * <pre>
   * {@code
   *     let instruction = Fail(FailBox::new("Fail"));
   * }
   * </pre>
   */
  @Test
  public void testFailInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = new BigInteger("1611733302477");
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    Instruction instruction = new Fail("Fail");

    payload.setInstructions(List.of(instruction));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 8, 16, 70, 97, 105, 108, 205, 124, 202, 66, 119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   *  Compares scale serialization of SetKeyValue command with generated in rust one:
   *
   *  <pre>
   *  {@code
   *   let instruction = SetKeyValueBox::new(
   *     IdBox::AssetId(AssetId::new(
   *       AssetDefinitionId::new("XOR", "Soramitsu"),
   *        account_id.clone(),
   *     )),
   *     "Key".to_string(),
   *     "Value".to_string(),
   *   );
   *  }
   *   </pre>
   */
  @Test
  public void testSetKeyValueInstruction() {
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = BigInteger.ONE;
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    DefinitionId definitionId = new DefinitionId("XOR", "Soramitsu");
    SetKeyValue setKeyValue = new SetKeyValue(
            new Raw(new Value(new Id(new AssetId(definitionId, accountId)))),
            new Raw(new Value(new StringValue("Key"))),
            new Raw(new Value(new StringValue("Value")))
    );

    payload.setInstructions(List.of(setKeyValue));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 9, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 13, 2, 12, 75, 101, 121, 13, 2, 20, 86, 97, 108, 117, 101, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

  /**
   * Compares scale serialization of RemoveKeyValue command with generated in rust one:
   *
   * <pre>
   * {@code
   *     let instruction = RemoveKeyValueBox::new(
   *         IdBox::AssetId(AssetId::new(
   *             AssetDefinitionId::new("XOR", "Soramitsu"),
   *             account_id.clone(),
   *         )),
   *         "Key".to_string(),
   *     );
   * }
   * </pre>
   */
  @Test
  public void testRemoveKeyValueInstruction() {       d
    AccountId accountId = new AccountId("root", "global");
    BigInteger creationTime = BigInteger.ONE;
    BigInteger timeToLiveMs = BigInteger.ZERO;

    Payload payload = new Payload(accountId, creationTime, timeToLiveMs);

    DefinitionId definitionId = new DefinitionId("XOR", "Soramitsu");
    RemoveKeyValue removeKeyValue = new RemoveKeyValue(
            new Raw(new Value(new Id(new AssetId(definitionId, accountId)))),
            new Raw(new Value(new StringValue("Key")))
    );

    payload.setInstructions(List.of(removeKeyValue));

    String expected = "[16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 4, 10, 13, 4, 1, 12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 13, 2, 12, 75, 101, 121, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(payload)));
  }

}
