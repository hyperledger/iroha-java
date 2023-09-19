//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit

/**
 * VersionedBlockMessage
 *
 * Generated from 'VersionedBlockMessage' enum
 */
public sealed class VersionedBlockMessage : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'V1' variant
     */
    public data class V1(
        public val blockMessage: BlockMessage,
    ) : VersionedBlockMessage() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.VersionedBlockMessage.V1>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.VersionedBlockMessage.V1> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.VersionedBlockMessage.V1 = try {
                V1(
                    BlockMessage.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.VersionedBlockMessage.V1,
            ): Unit = try {
                BlockMessage.write(writer, instance.blockMessage)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<VersionedBlockMessage>, ScaleWriter<VersionedBlockMessage> {
        override fun read(reader: ScaleCodecReader): VersionedBlockMessage = when (
            val discriminant =
                reader.readUByte()
        ) {
            1 -> V1.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: VersionedBlockMessage) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                1 -> V1.write(writer, instance as V1)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
