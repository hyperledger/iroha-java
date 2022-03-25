//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.filters.role

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * RoleEventFilter
 *
 * Generated from 'iroha_data_model::events::data::filters::role::RoleEventFilter' enum
 */
public sealed class RoleEventFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'ByCreated' variant
     */
    public class ByCreated : RoleEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByCreated>, ScaleWriter<ByCreated> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ByCreated = try {
                ByCreated()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByCreated) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ByDeleted' variant
     */
    public class ByDeleted : RoleEventFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ByDeleted>, ScaleWriter<ByDeleted> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ByDeleted = try {
                ByDeleted()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ByDeleted) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RoleEventFilter>, ScaleWriter<RoleEventFilter> {
        public override fun read(reader: ScaleCodecReader): RoleEventFilter = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> ByCreated.read(reader)
            1 -> ByDeleted.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: RoleEventFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ByCreated.write(writer, instance as ByCreated)
                1 -> ByDeleted.write(writer, instance as ByDeleted)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
