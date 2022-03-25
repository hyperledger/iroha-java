//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.merkle

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Int

/**
 * Node
 *
 * Generated from
 * 'iroha_data_model::merkle::Node<iroha_data_model::transaction::VersionedTransaction>' enum
 */
public sealed class Node<T0> : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Subtree' variant
     */
    public data class Subtree(
        public val subtree:  
            jp.co.soramitsu.iroha2.generated.datamodel.merkle.Subtree<VersionedTransaction>
    ) : Node<VersionedTransaction>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Subtree>, ScaleWriter<Subtree> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Subtree = try {
                Subtree(
                    jp.co.soramitsu.iroha2.generated.datamodel.merkle.Subtree.read(reader) as
                        jp.co.soramitsu.iroha2.generated.datamodel.merkle.Subtree<VersionedTransaction>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Subtree) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.merkle.Subtree.write(writer, instance.subtree)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Leaf' variant
     */
    public data class Leaf(
        public val leaf: jp.co.soramitsu.iroha2.generated.datamodel.merkle.Leaf<VersionedTransaction>
    ) : Node<VersionedTransaction>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Leaf>, ScaleWriter<Leaf> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Leaf = try {
                Leaf(
                    jp.co.soramitsu.iroha2.generated.datamodel.merkle.Leaf.read(reader) as
                        jp.co.soramitsu.iroha2.generated.datamodel.merkle.Leaf<VersionedTransaction>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Leaf) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.merkle.Leaf.write(writer, instance.leaf)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Empty' variant
     */
    public class Empty : Node<VersionedTransaction>() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Empty>, ScaleWriter<Empty> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Empty = try {
                Empty()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Empty) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Node<out Any>>, ScaleWriter<Node<out Any>> {
        public override fun read(reader: ScaleCodecReader): Node<out Any> = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Subtree.read(reader)
            1 -> Leaf.read(reader)
            2 -> Empty.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Node<out Any>) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Subtree.write(writer, instance as Subtree)
                1 -> Leaf.write(writer, instance as Leaf)
                2 -> Empty.write(writer, instance as Empty)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
