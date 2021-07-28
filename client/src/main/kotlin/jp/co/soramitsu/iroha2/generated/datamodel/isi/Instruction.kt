//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * Instruction
 *
 * Generated from 'iroha_data_model::isi::Instruction' enum
 */
public sealed class Instruction {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Register' variant
   */
  public class Register(
    private val registerBox: RegisterBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Register>, ScaleWriter<Register> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Register = Register(
        RegisterBox.read(reader) as RegisterBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Register): Unit {
          RegisterBox.write(writer, instance.registerBox)
      }
    }
  }

  /**
   * 'Unregister' variant
   */
  public class Unregister(
    private val unregisterBox: UnregisterBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Unregister>, ScaleWriter<Unregister> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Unregister = Unregister(
        UnregisterBox.read(reader) as UnregisterBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Unregister): Unit {
          UnregisterBox.write(writer, instance.unregisterBox)
      }
    }
  }

  /**
   * 'Mint' variant
   */
  public class Mint(
    private val mintBox: MintBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Mint>, ScaleWriter<Mint> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): Mint = Mint(
        MintBox.read(reader) as MintBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Mint): Unit {
          MintBox.write(writer, instance.mintBox)
      }
    }
  }

  /**
   * 'Burn' variant
   */
  public class Burn(
    private val burnBox: BurnBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Burn>, ScaleWriter<Burn> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): Burn = Burn(
        BurnBox.read(reader) as BurnBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Burn): Unit {
          BurnBox.write(writer, instance.burnBox)
      }
    }
  }

  /**
   * 'Transfer' variant
   */
  public class Transfer(
    private val transferBox: TransferBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Transfer>, ScaleWriter<Transfer> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): Transfer = Transfer(
        TransferBox.read(reader) as TransferBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Transfer): Unit {
          TransferBox.write(writer, instance.transferBox)
      }
    }
  }

  /**
   * 'If' variant
   */
  public class If(
    private val `if`: jp.co.soramitsu.iroha2.generated.datamodel.isi.If
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<If>, ScaleWriter<If> {
      public const val DISCRIMINANT: Int = 5

      public override fun read(reader: ScaleCodecReader): If = If(
        jp.co.soramitsu.iroha2.generated.datamodel.isi.If.read(reader) as
            jp.co.soramitsu.iroha2.generated.datamodel.isi.If,
      )

      public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
          jp.co.soramitsu.iroha2.generated.datamodel.isi.If.write(writer, instance.if)
      }
    }
  }

  /**
   * 'Pair' variant
   */
  public class Pair(
    private val pair: jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Pair>, ScaleWriter<Pair> {
      public const val DISCRIMINANT: Int = 6

      public override fun read(reader: ScaleCodecReader): Pair = Pair(
        jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair.read(reader) as
            jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Pair): Unit {
          jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair.write(writer, instance.pair)
      }
    }
  }

  /**
   * 'Sequence' variant
   */
  public class Sequence(
    private val sequenceBox: SequenceBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Sequence>, ScaleWriter<Sequence> {
      public const val DISCRIMINANT: Int = 7

      public override fun read(reader: ScaleCodecReader): Sequence = Sequence(
        SequenceBox.read(reader) as SequenceBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Sequence): Unit {
          SequenceBox.write(writer, instance.sequenceBox)
      }
    }
  }

  /**
   * 'Fail' variant
   */
  public class Fail(
    private val failBox: FailBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
      public const val DISCRIMINANT: Int = 8

      public override fun read(reader: ScaleCodecReader): Fail = Fail(
        FailBox.read(reader) as FailBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Fail): Unit {
          FailBox.write(writer, instance.failBox)
      }
    }
  }

  /**
   * 'SetKeyValue' variant
   */
  public class SetKeyValue(
    private val setKeyValueBox: SetKeyValueBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<SetKeyValue>, ScaleWriter<SetKeyValue> {
      public const val DISCRIMINANT: Int = 9

      public override fun read(reader: ScaleCodecReader): SetKeyValue = SetKeyValue(
        SetKeyValueBox.read(reader) as SetKeyValueBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: SetKeyValue): Unit {
          SetKeyValueBox.write(writer, instance.setKeyValueBox)
      }
    }
  }

  /**
   * 'RemoveKeyValue' variant
   */
  public class RemoveKeyValue(
    private val removeKeyValueBox: RemoveKeyValueBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<RemoveKeyValue>, ScaleWriter<RemoveKeyValue> {
      public const val DISCRIMINANT: Int = 10

      public override fun read(reader: ScaleCodecReader): RemoveKeyValue = RemoveKeyValue(
        RemoveKeyValueBox.read(reader) as RemoveKeyValueBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: RemoveKeyValue): Unit {
          RemoveKeyValueBox.write(writer, instance.removeKeyValueBox)
      }
    }
  }

  /**
   * 'Grant' variant
   */
  public class Grant(
    private val grantBox: GrantBox
  ) : Instruction() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Grant>, ScaleWriter<Grant> {
      public const val DISCRIMINANT: Int = 11

      public override fun read(reader: ScaleCodecReader): Grant = Grant(
        GrantBox.read(reader) as GrantBox,
      )

      public override fun write(writer: ScaleCodecWriter, instance: Grant): Unit {
          GrantBox.write(writer, instance.grantBox)
      }
    }
  }

  public companion object : ScaleReader<Instruction>, ScaleWriter<Instruction> {
    public override fun read(reader: ScaleCodecReader): Instruction = when(val discriminant =
        reader.readUByte()) {
    	0 -> Register.read(reader)
    	1 -> Unregister.read(reader)
    	2 -> Mint.read(reader)
    	3 -> Burn.read(reader)
    	4 -> Transfer.read(reader)
    	5 -> If.read(reader)
    	6 -> Pair.read(reader)
    	7 -> Sequence.read(reader)
    	8 -> Fail.read(reader)
    	9 -> SetKeyValue.read(reader)
    	10 -> RemoveKeyValue.read(reader)
    	11 -> Grant.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: Instruction): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Register.write(writer, instance as Register)
      	1 -> Unregister.write(writer, instance as Unregister)
      	2 -> Mint.write(writer, instance as Mint)
      	3 -> Burn.write(writer, instance as Burn)
      	4 -> Transfer.write(writer, instance as Transfer)
      	5 -> If.write(writer, instance as If)
      	6 -> Pair.write(writer, instance as Pair)
      	7 -> Sequence.write(writer, instance as Sequence)
      	8 -> Fail.write(writer, instance as Fail)
      	9 -> SetKeyValue.write(writer, instance as SetKeyValue)
      	10 -> RemoveKeyValue.write(writer, instance as RemoveKeyValue)
      	11 -> Grant.write(writer, instance as Grant)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
