// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
import kotlin.collections.List

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
    private val u32: Int
  ) : Value() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<U32>, ScaleWriter<U32> {
      public override fun read(reader: ScaleCodecReader): U32 =
          jp.co.soramitsu.schema.generated.datamodel.Value.U32(reader.readLong().toInt())

      public override fun write(writer: ScaleCodecWriter, instance: U32): Unit {
        writer.writeLong(instance.`u32`.toLong())
      }
    }
  }

  /**
   * 'Bool' variant
   */
  public class Bool(
    private val bool: Boolean
  ) : Value() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<Bool>, ScaleWriter<Bool> {
      public override fun read(reader: ScaleCodecReader): Bool =
          jp.co.soramitsu.schema.generated.datamodel.Value.Bool(reader.readBoolean())

      public override fun write(writer: ScaleCodecWriter, instance: Bool): Unit {
        writer.writeBoolean(instance.`bool`)
      }
    }
  }

  /**
   * 'String' variant
   */
  public class String(
    private val string: kotlin.String
  ) : Value() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<String>, ScaleWriter<String> {
      public override fun read(reader: ScaleCodecReader): String =
          jp.co.soramitsu.schema.generated.datamodel.Value.String(reader.readString())

      public override fun write(writer: ScaleCodecWriter, instance: String): Unit {
        writer.writeString(instance.`string`)
      }
    }
  }

  /**
   * 'Vec' variant
   */
  public class Vec(
    private val vec: List<Value>
  ) : Value() {
    public override fun discriminant(): Int = 3

    public companion object CODEC : ScaleReader<Vec>, ScaleWriter<Vec> {
      public override fun read(reader: ScaleCodecReader): Vec =
          jp.co.soramitsu.schema.generated.datamodel.Value.Vec(reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(Value)))

      public override fun write(writer: ScaleCodecWriter, instance: Vec): Unit {
        writer.write(io.emeraldpay.polkaj.scale.writer.ListWriter(Value), instance.`vec`)
      }
    }
  }

  /**
   * 'Id' variant
   */
  public class Id(
    private val id: IdBox
  ) : Value() {
    public override fun discriminant(): Int = 4

    public companion object CODEC : ScaleReader<Id>, ScaleWriter<Id> {
      public override fun read(reader: ScaleCodecReader): Id =
          jp.co.soramitsu.schema.generated.datamodel.Value.Id(jp.co.soramitsu.schema.generated.datamodel.IdBox.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Id): Unit {
        jp.co.soramitsu.schema.generated.datamodel.IdBox.write(writer, instance.`id`)
      }
    }
  }

  /**
   * 'Identifiable' variant
   */
  public class Identifiable(
    private val identifiable: IdentifiableBox
  ) : Value() {
    public override fun discriminant(): Int = 5

    public companion object CODEC : ScaleReader<Identifiable>, ScaleWriter<Identifiable> {
      public override fun read(reader: ScaleCodecReader): Identifiable =
          jp.co.soramitsu.schema.generated.datamodel.Value.Identifiable(jp.co.soramitsu.schema.generated.datamodel.IdentifiableBox.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Identifiable): Unit {
        jp.co.soramitsu.schema.generated.datamodel.IdentifiableBox.write(writer,
            instance.`identifiable`)
      }
    }
  }

  /**
   * 'PublicKey' variant
   */
  public class PublicKey(
    private val publicKey: jp.co.soramitsu.schema.generated.crypto.PublicKey
  ) : Value() {
    public override fun discriminant(): Int = 6

    public companion object CODEC : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
      public override fun read(reader: ScaleCodecReader): PublicKey =
          jp.co.soramitsu.schema.generated.datamodel.Value.PublicKey(jp.co.soramitsu.schema.generated.crypto.PublicKey.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: PublicKey): Unit {
        jp.co.soramitsu.schema.generated.crypto.PublicKey.write(writer, instance.`publicKey`)
      }
    }
  }

  /**
   * 'Parameter' variant
   */
  public class Parameter(
    private val parameter: jp.co.soramitsu.schema.generated.datamodel.Parameter
  ) : Value() {
    public override fun discriminant(): Int = 7

    public companion object CODEC : ScaleReader<Parameter>, ScaleWriter<Parameter> {
      public override fun read(reader: ScaleCodecReader): Parameter =
          jp.co.soramitsu.schema.generated.datamodel.Value.Parameter(jp.co.soramitsu.schema.generated.datamodel.Parameter.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Parameter): Unit {
        jp.co.soramitsu.schema.generated.datamodel.Parameter.write(writer, instance.`parameter`)
      }
    }
  }

  /**
   * 'SignatureCheckCondition' variant
   */
  public class SignatureCheckCondition(
    private val signatureCheckCondition:
        jp.co.soramitsu.schema.generated.datamodel.account.SignatureCheckCondition
  ) : Value() {
    public override fun discriminant(): Int = 8

    public companion object CODEC : ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
      public override fun read(reader: ScaleCodecReader): SignatureCheckCondition =
          jp.co.soramitsu.schema.generated.datamodel.Value.SignatureCheckCondition(jp.co.soramitsu.schema.generated.datamodel.account.SignatureCheckCondition.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition): Unit {
        jp.co.soramitsu.schema.generated.datamodel.account.SignatureCheckCondition.write(writer,
            instance.`signatureCheckCondition`)
      }
    }
  }

  /**
   * 'TransactionValue' variant
   */
  public class TransactionValue(
    private val transactionValue:
        jp.co.soramitsu.schema.generated.datamodel.transaction.TransactionValue
  ) : Value() {
    public override fun discriminant(): Int = 9

    public companion object CODEC : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
      public override fun read(reader: ScaleCodecReader): TransactionValue =
          jp.co.soramitsu.schema.generated.datamodel.Value.TransactionValue(jp.co.soramitsu.schema.generated.datamodel.transaction.TransactionValue.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: TransactionValue): Unit {
        jp.co.soramitsu.schema.generated.datamodel.transaction.TransactionValue.write(writer,
            instance.`transactionValue`)
      }
    }
  }

  /**
   * 'PermissionToken' variant
   */
  public class PermissionToken(
    private val permissionToken:
        jp.co.soramitsu.schema.generated.datamodel.permissions.PermissionToken
  ) : Value() {
    public override fun discriminant(): Int = 10

    public companion object CODEC : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
      public override fun read(reader: ScaleCodecReader): PermissionToken =
          jp.co.soramitsu.schema.generated.datamodel.Value.PermissionToken(jp.co.soramitsu.schema.generated.datamodel.permissions.PermissionToken.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: PermissionToken): Unit {
        jp.co.soramitsu.schema.generated.datamodel.permissions.PermissionToken.write(writer,
            instance.`permissionToken`)
      }
    }
  }

  public companion object CODEC : ScaleReader<Value>, ScaleWriter<Value> {
    public override fun read(reader: ScaleCodecReader): Value = when(reader.readUByte()) {
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
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: Value): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
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
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
