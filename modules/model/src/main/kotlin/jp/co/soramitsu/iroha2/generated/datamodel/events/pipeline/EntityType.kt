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
 * EntityType
 *
 * Generated from 'iroha_data_model::events::pipeline::EntityType' enum
 */
public sealed class EntityType : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Block' variant
     */
    public class Block : EntityType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Transaction' variant
     */
    public class Transaction : EntityType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Transaction = try {
                Transaction()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EntityType>, ScaleWriter<EntityType> {
        public override fun read(reader: ScaleCodecReader): EntityType = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Block.read(reader)
            1 -> Transaction.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EntityType) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Block.write(writer, instance as Block)
                1 -> Transaction.write(writer, instance as Transaction)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
