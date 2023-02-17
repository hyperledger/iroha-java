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
 * VersionedBlockSubscriptionRequest
 *
 * Generated from 'iroha_core::block::stream::VersionedBlockSubscriptionRequest' enum
 */
public sealed class VersionedBlockSubscriptionRequest : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'V1' variant
     */
    public data class V1(
        public val blockSubscriptionRequest: BlockSubscriptionRequest
    ) : VersionedBlockSubscriptionRequest() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<V1>, ScaleWriter<V1> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): V1 = try {
                V1(
                    BlockSubscriptionRequest.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: V1) = try {
                BlockSubscriptionRequest.write(writer, instance.blockSubscriptionRequest)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<VersionedBlockSubscriptionRequest>,
        ScaleWriter<VersionedBlockSubscriptionRequest> {
        public override fun read(reader: ScaleCodecReader): VersionedBlockSubscriptionRequest = when (
            val
            discriminant = reader.readUByte()
        ) {
            1 -> V1.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: VersionedBlockSubscriptionRequest
        ) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                1 -> V1.write(writer, instance as V1)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
