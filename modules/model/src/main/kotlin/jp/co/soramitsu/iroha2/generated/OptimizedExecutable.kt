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
import kotlin.collections.List

/**
 * OptimizedExecutable
 *
 * Generated from 'OptimizedExecutable' enum
 */
public sealed class OptimizedExecutable : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'WasmInternalRepr' variant
     */
    public data class WasmInternalRepr(
        public val wasmInternalRepr: jp.co.soramitsu.iroha2.generated.WasmInternalRepr
    ) : OptimizedExecutable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<WasmInternalRepr>, ScaleWriter<WasmInternalRepr> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): WasmInternalRepr = try {
                WasmInternalRepr(
                    jp.co.soramitsu.iroha2.generated.WasmInternalRepr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: WasmInternalRepr) = try {
                jp.co.soramitsu.iroha2.generated.WasmInternalRepr.write(writer, instance.wasmInternalRepr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Instructions' variant
     */
    public data class Instructions(
        public val vec: List<InstructionBox>
    ) : OptimizedExecutable() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Instructions>, ScaleWriter<Instructions> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Instructions = try {
                Instructions(
                    reader.readVec(reader.readCompactInt()) { InstructionBox.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Instructions) = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    InstructionBox.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<OptimizedExecutable>, ScaleWriter<OptimizedExecutable> {
        public override fun read(reader: ScaleCodecReader): OptimizedExecutable = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> WasmInternalRepr.read(reader)
            1 -> Instructions.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: OptimizedExecutable) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> WasmInternalRepr.write(writer, instance as WasmInternalRepr)
                1 -> Instructions.write(writer, instance as Instructions)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
