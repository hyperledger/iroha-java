//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Boolean
import kotlin.Int
import kotlin.UInt
import kotlin.Unit
import kotlin.collections.MutableList

/**
 * Value
 *
 * Generated from 'iroha_data_model::Value' enum
 */
public sealed class Value {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'U32' variant
   */
  public class U32(
    private val u32: UInt
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<U32>, ScaleWriter<U32> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): U32 {
      }

      public override fun write(writer: ScaleCodecWriter, instance: U32): Unit {
      }
    }
  }

  /**
   * 'Bool' variant
   */
  public class Bool(
    private val bool: Boolean
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Bool>, ScaleWriter<Bool> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Bool {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Bool): Unit {
      }
    }
  }

  /**
   * 'String' variant
   */
  public class String(
    private val string: kotlin.String
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<String>, ScaleWriter<String> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): String {
      }

      public override fun write(writer: ScaleCodecWriter, instance: String): Unit {
      }
    }
  }

  /**
   * 'Vec' variant
   */
  public class Vec(
    private val vec: MutableList<Value>
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Vec>, ScaleWriter<Vec> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): Vec {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Vec): Unit {
      }
    }
  }

  /**
   * 'Id' variant
   */
  public class Id(
    private val id: IdBox
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Id>, ScaleWriter<Id> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): Id {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Id): Unit {
      }
    }
  }

  /**
   * 'Identifiable' variant
   */
  public class Identifiable(
    private val identifiable: IdentifiableBox
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Identifiable>, ScaleWriter<Identifiable> {
      public const val DISCRIMINANT: Int = 5

      public override fun read(reader: ScaleCodecReader): Identifiable {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Identifiable): Unit {
      }
    }
  }

  /**
   * 'PublicKey' variant
   */
  public class PublicKey(
    private val publicKey: jp.co.soramitsu.iroha2.generated.crypto.PublicKey
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
      public const val DISCRIMINANT: Int = 6

      public override fun read(reader: ScaleCodecReader): PublicKey {
      }

      public override fun write(writer: ScaleCodecWriter, instance: PublicKey): Unit {
      }
    }
  }

  /**
   * 'Parameter' variant
   */
  public class Parameter(
    private val parameter: jp.co.soramitsu.iroha2.generated.datamodel.Parameter
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Parameter>, ScaleWriter<Parameter> {
      public const val DISCRIMINANT: Int = 7

      public override fun read(reader: ScaleCodecReader): Parameter {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Parameter): Unit {
      }
    }
  }

  /**
   * 'SignatureCheckCondition' variant
   */
  public class SignatureCheckCondition(
    private val signatureCheckCondition:
        jp.co.soramitsu.iroha2.generated.datamodel.account.SignatureCheckCondition
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
      public const val DISCRIMINANT: Int = 8

      public override fun read(reader: ScaleCodecReader): SignatureCheckCondition {
      }

      public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition): Unit {
      }
    }
  }

  /**
   * 'TransactionValue' variant
   */
  public class TransactionValue(
    private val transactionValue:
        jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
      public const val DISCRIMINANT: Int = 9

      public override fun read(reader: ScaleCodecReader): TransactionValue {
      }

      public override fun write(writer: ScaleCodecWriter, instance: TransactionValue): Unit {
      }
    }
  }

  /**
   * 'PermissionToken' variant
   */
  public class PermissionToken(
    private val permissionToken:
        jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
  ) : Value() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
      public const val DISCRIMINANT: Int = 10

      public override fun read(reader: ScaleCodecReader): PermissionToken {
      }

      public override fun write(writer: ScaleCodecWriter, instance: PermissionToken): Unit {
      }
    }
  }

  public companion object : ScaleReader<Value>, ScaleWriter<Value> {
    public override fun read(reader: ScaleCodecReader): Value = when(val discriminant =
        reader.readUByte()) {
    	0 -> U32.read(reader)
    	1 -> Bool.read(reader)
    	2 -> String.read(reader)
    	3 -> Vec.read(reader)
    	4 -> Id.read(reader)
    	5 -> Identifiable.read(reader)
    	6 -> PublicKey.read(reader)
    	7 -> Parameter.read(reader)
    	8 -> SignatureCheckCondition.read(reader)
    	9 -> TransactionValue.read(reader)
    	10 -> PermissionToken.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: Value): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> U32.write(writer, instance as U32)
      	1 -> Bool.write(writer, instance as Bool)
      	2 -> String.write(writer, instance as String)
      	3 -> Vec.write(writer, instance as Vec)
      	4 -> Id.write(writer, instance as Id)
      	5 -> Identifiable.write(writer, instance as Identifiable)
      	6 -> PublicKey.write(writer, instance as PublicKey)
      	7 -> Parameter.write(writer, instance as Parameter)
      	8 -> SignatureCheckCondition.write(writer, instance as SignatureCheckCondition)
      	9 -> TransactionValue.write(writer, instance as TransactionValue)
      	10 -> PermissionToken.write(writer, instance as PermissionToken)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
