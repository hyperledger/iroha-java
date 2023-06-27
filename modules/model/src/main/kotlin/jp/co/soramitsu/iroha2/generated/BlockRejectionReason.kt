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
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * BlockRejectionReason
 *
 * Generated from 'BlockRejectionReason' enum
 */
public sealed class BlockRejectionReason : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is ConsensusBlockRejection -> ConsensusBlockRejection.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is ConsensusBlockRejection -> ConsensusBlockRejection.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'ConsensusBlockRejection' variant
     */
    public class ConsensusBlockRejection : BlockRejectionReason() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<ConsensusBlockRejection>,
            ScaleWriter<ConsensusBlockRejection> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): ConsensusBlockRejection = try {
                ConsensusBlockRejection()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ConsensusBlockRejection) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: ConsensusBlockRejection, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int =
                ".BlockRejectionReason.ConsensusBlockRejection".hashCode()
        }
    }

    public companion object : ScaleReader<BlockRejectionReason>, ScaleWriter<BlockRejectionReason> {
        public override fun read(reader: ScaleCodecReader): BlockRejectionReason = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> ConsensusBlockRejection.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockRejectionReason) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> ConsensusBlockRejection.write(writer, instance as ConsensusBlockRejection)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
