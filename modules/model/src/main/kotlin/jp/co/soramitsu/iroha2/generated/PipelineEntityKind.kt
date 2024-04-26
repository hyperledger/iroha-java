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
import kotlin.Unit

/**
 * PipelineEntityKind
 *
 * Generated from 'PipelineEntityKind' enum
 */
public sealed class PipelineEntityKind : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is Block -> Block.equals(this, other)
        is Transaction -> Transaction.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is Block -> Block.hashCode()
        is Transaction -> Transaction.hashCode()
        else -> super.hashCode() }

    /**
     * 'Block' variant
     */
    public class Block : PipelineEntityKind() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Block>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Block> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Block = try {
                Block()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Block,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Block, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PipelineEntityKind.Block".hashCode()
        }
    }

    /**
     * 'Transaction' variant
     */
    public class Transaction : PipelineEntityKind() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Transaction>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Transaction> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Transaction = try {
                Transaction()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Transaction,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(
                o1: jp.co.soramitsu.iroha2.generated.PipelineEntityKind.Transaction,
                o2: Any?,
            ): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".PipelineEntityKind.Transaction".hashCode()
        }
    }

    public companion object : ScaleReader<PipelineEntityKind>, ScaleWriter<PipelineEntityKind> {
        override fun read(reader: ScaleCodecReader): PipelineEntityKind = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Block.read(reader)
            1 -> Transaction.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: PipelineEntityKind) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Block.write(writer, instance as Block)
                1 -> Transaction.write(writer, instance as Transaction)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
