//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

/**
 * Instruction
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction' enum
 */
public sealed class Instruction {
  /**
   * 'Register' variant
   */
  public class Register(
    private val register: RegisterBox
  ) : Instruction()

  /**
   * 'Unregister' variant
   */
  public class Unregister(
    private val unregister: UnregisterBox
  ) : Instruction()

  /**
   * 'Mint' variant
   */
  public class Mint(
    private val mint: MintBox
  ) : Instruction()

  /**
   * 'Burn' variant
   */
  public class Burn(
    private val burn: BurnBox
  ) : Instruction()

  /**
   * 'Transfer' variant
   */
  public class Transfer(
    private val transfer: TransferBox
  ) : Instruction()

  /**
   * 'If' variant
   */
  public class If(
    private val `if`: jp.co.soramitsu.iroha2.generated.datamodel.isi.If
  ) : Instruction()

  /**
   * 'Pair' variant
   */
  public class Pair(
    private val pair: jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair
  ) : Instruction()

  /**
   * 'Sequence' variant
   */
  public class Sequence(
    private val sequence: SequenceBox
  ) : Instruction()

  /**
   * 'Fail' variant
   */
  public class Fail(
    private val fail: FailBox
  ) : Instruction()

  /**
   * 'SetKeyValue' variant
   */
  public class SetKeyValue(
    private val setKeyValue: SetKeyValueBox
  ) : Instruction()

  /**
   * 'RemoveKeyValue' variant
   */
  public class RemoveKeyValue(
    private val removeKeyValue: RemoveKeyValueBox
  ) : Instruction()

  /**
   * 'Grant' variant
   */
  public class Grant(
    private val grant: GrantBox
  ) : Instruction()
}
