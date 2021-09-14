//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Int
import kotlin.UInt

/**
 * Parameter
 *
 * Generated from 'iroha_data_model::Parameter' enum
 */
public sealed class Parameter {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'MaximumFaultyPeersAmount' variant
     */
    public data class MaximumFaultyPeersAmount(
        public val u32: UInt
    ) : Parameter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<MaximumFaultyPeersAmount>,
            ScaleWriter<MaximumFaultyPeersAmount> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): MaximumFaultyPeersAmount = try {
                MaximumFaultyPeersAmount(
                    reader.readUint32().toUInt(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MaximumFaultyPeersAmount) = try {
                writer.writeUint32(instance.u32.toInt())
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'BlockTime' variant
     */
    public data class BlockTime(
        public val u128: BigInteger
    ) : Parameter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlockTime>, ScaleWriter<BlockTime> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BlockTime = try {
                BlockTime(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlockTime) = try {
                writer.writeUint128(instance.u128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'CommitTime' variant
     */
    public data class CommitTime(
        public val u128: BigInteger
    ) : Parameter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<CommitTime>, ScaleWriter<CommitTime> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): CommitTime = try {
                CommitTime(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: CommitTime) = try {
                writer.writeUint128(instance.u128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TransactionReceiptTime' variant
     */
    public data class TransactionReceiptTime(
        public val u128: BigInteger
    ) : Parameter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<TransactionReceiptTime>,
            ScaleWriter<TransactionReceiptTime> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): TransactionReceiptTime = try {
                TransactionReceiptTime(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionReceiptTime) = try {
                writer.writeUint128(instance.u128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Parameter>, ScaleWriter<Parameter> {
        public override fun read(reader: ScaleCodecReader): Parameter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> MaximumFaultyPeersAmount.read(reader)
            1 -> BlockTime.read(reader)
            2 -> CommitTime.read(reader)
            3 -> TransactionReceiptTime.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Parameter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> MaximumFaultyPeersAmount.write(writer, instance as MaximumFaultyPeersAmount)
                1 -> BlockTime.write(writer, instance as BlockTime)
                2 -> CommitTime.write(writer, instance as CommitTime)
                3 -> TransactionReceiptTime.write(writer, instance as TransactionReceiptTime)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
