//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * StatusKind
 *
 * Generated from 'iroha_data_model::events::pipeline::StatusKind' enum
 */
public sealed class StatusKind : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Validating' variant
     */
    public class Validating : StatusKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Validating>, ScaleWriter<Validating> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Validating = try {
                Validating()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Validating) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Rejected' variant
     */
    public class Rejected : StatusKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Rejected>, ScaleWriter<Rejected> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Rejected = try {
                Rejected()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Rejected) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Committed' variant
     */
    public class Committed : StatusKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Committed>, ScaleWriter<Committed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Committed = try {
                Committed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Committed) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<StatusKind>, ScaleWriter<StatusKind> {
        public override fun read(reader: ScaleCodecReader): StatusKind = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Validating.read(reader)
            1 -> Rejected.read(reader)
            2 -> Committed.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: StatusKind) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Validating.write(writer, instance as Validating)
                1 -> Rejected.write(writer, instance as Rejected)
                2 -> Committed.write(writer, instance as Committed)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
