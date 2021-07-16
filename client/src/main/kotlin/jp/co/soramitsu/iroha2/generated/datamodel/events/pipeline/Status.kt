//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * Status
 *
 * Generated from 'iroha_data_model::events::pipeline::Status' enum
 */
public sealed class Status {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Validating' variant
   */
  public class Validating : Status() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  /**
   * 'Rejected' variant
   */
  public class Rejected(
    private val rejected: RejectionReason
  ) : Status() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Rejected>, ScaleWriter<Rejected> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Rejected {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Rejected): Unit {
      }
    }
  }

  /**
   * 'Committed' variant
   */
  public class Committed : Status() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  public companion object : ScaleReader<Status>, ScaleWriter<Status> {
    public override fun read(reader: ScaleCodecReader): Status = when(val discriminant =
        reader.readUByte()) {
    	0 -> Validating.read(reader)
    	1 -> Rejected.read(reader)
    	2 -> Committed.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: Status): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Validating.write(writer, instance as Validating)
      	1 -> Rejected.write(writer, instance as Rejected)
      	2 -> Committed.write(writer, instance as Committed)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
