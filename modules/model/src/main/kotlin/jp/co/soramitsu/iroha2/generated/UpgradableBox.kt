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
 * UpgradableBox
 *
 * Generated from 'UpgradableBox' enum
 */
public sealed class UpgradableBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Executor' variant
     */
    public data class Executor(
        public val executor: jp.co.soramitsu.iroha2.generated.Executor,
    ) : UpgradableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.UpgradableBox.Executor>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.UpgradableBox.Executor> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.UpgradableBox.Executor = try {
                Executor(
                    jp.co.soramitsu.iroha2.generated.Executor.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.UpgradableBox.Executor,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Executor.write(writer, instance.executor)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<UpgradableBox>, ScaleWriter<UpgradableBox> {
        override fun read(reader: ScaleCodecReader): UpgradableBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Executor.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: UpgradableBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Executor.write(writer, instance as Executor)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
