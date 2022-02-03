//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray
import kotlin.Int
import kotlin.collections.List

/**
 * Executable
 *
 * Generated from 'iroha_data_model::transaction::Executable' enum
 */
public sealed class Executable : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Instructions' variant
     */
    public data class Instructions(
        public val vec: List<Instruction>
    ) : Executable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Instructions>, ScaleWriter<Instructions> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Instructions = try {
                Instructions(
                    reader.readVec(reader.readCompactInt()) { Instruction.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Instructions) = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value -> Instruction.write(writer, value) }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Wasm' variant
     */
    public data class Wasm(
        public val vec: ByteArray
    ) : Executable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Wasm>, ScaleWriter<Wasm> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Wasm = try {
                Wasm(
                    reader.readByteArray(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Wasm) = try {
                writer.writeAsList(instance.vec)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Executable>, ScaleWriter<Executable> {
        public override fun read(reader: ScaleCodecReader): Executable = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Instructions.read(reader)
            1 -> Wasm.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Executable) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Instructions.write(writer, instance as Instructions)
                1 -> Wasm.write(writer, instance as Wasm)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
