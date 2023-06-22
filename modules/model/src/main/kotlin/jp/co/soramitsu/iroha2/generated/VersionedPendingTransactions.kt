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

/**
 * VersionedPendingTransactions
 *
 * Generated from 'VersionedPendingTransactions' enum
 */
public sealed class VersionedPendingTransactions : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'V1' variant
     */
    public data class V1(
        public val pendingTransactions: PendingTransactions
    ) : VersionedPendingTransactions() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<V1>, ScaleWriter<V1> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): V1 = try {
                V1(
                    PendingTransactions.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: V1) = try {
                PendingTransactions.write(writer, instance.pendingTransactions)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<VersionedPendingTransactions>,
        ScaleWriter<VersionedPendingTransactions> {
        public override fun read(reader: ScaleCodecReader): VersionedPendingTransactions = when (
            val
            discriminant = reader.readUByte()
        ) {
            1 -> V1.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: VersionedPendingTransactions) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                1 -> V1.write(writer, instance as V1)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
