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
 * HashValue
 *
 * Generated from 'HashValue' enum
 */
public sealed class HashValue : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Transaction' variant
     */
    public data class Transaction(
        public val hashOf: HashOf<VersionedSignedTransaction>,
    ) : HashValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.HashValue.Transaction>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.HashValue.Transaction> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.HashValue.Transaction = try {
                Transaction(
                    HashOf.read(reader) as HashOf<VersionedSignedTransaction>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.HashValue.Transaction,
            ): Unit = try {
                HashOf.write(writer, instance.hashOf)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val hashOf: HashOf<VersionedCommittedBlock>,
    ) : HashValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.HashValue.Block>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.HashValue.Block> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.HashValue.Block = try {
                Block(
                    HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.HashValue.Block,
            ): Unit = try {
                HashOf.write(writer, instance.hashOf)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<HashValue>, ScaleWriter<HashValue> {
        override fun read(reader: ScaleCodecReader): HashValue = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Transaction.read(reader)
            1 -> Block.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: HashValue) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Transaction.write(writer, instance as Transaction)
                1 -> Block.write(writer, instance as Block)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
