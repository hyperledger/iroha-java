//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block.stream

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * VersionedBlockPublisherMessage
 *
 * Generated from 'iroha_core::block::stream::VersionedBlockPublisherMessage' enum
 */
public sealed class VersionedBlockPublisherMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'V1' variant
     */
    public data class V1(
        public val blockPublisherMessage: BlockPublisherMessage
    ) : VersionedBlockPublisherMessage() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<V1>, ScaleWriter<V1> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): V1 = try {
                V1(
                    BlockPublisherMessage.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: V1) = try {
                BlockPublisherMessage.write(writer, instance.blockPublisherMessage)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<VersionedBlockPublisherMessage>,
        ScaleWriter<VersionedBlockPublisherMessage> {
        public override fun read(reader: ScaleCodecReader): VersionedBlockPublisherMessage = when (
            val
            discriminant = reader.readUByte().toInt()
        ) {
            1 -> V1.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: VersionedBlockPublisherMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                1 -> V1.write(writer, instance as V1)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
