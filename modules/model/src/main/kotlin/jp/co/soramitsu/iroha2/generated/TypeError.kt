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
 * TypeError
 *
 * Generated from 'TypeError' enum
 */
public sealed class TypeError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'AssetValueType' variant
     */
    public data class AssetValueType(
        public val mismatchOfAssetValueType: MismatchOfAssetValueType
    ) : TypeError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetValueType>, ScaleWriter<AssetValueType> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): AssetValueType = try {
                AssetValueType(
                    MismatchOfAssetValueType.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetValueType) = try {
                MismatchOfAssetValueType.write(writer, instance.mismatchOfAssetValueType)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ParameterValueType' variant
     */
    public data class ParameterValueType(
        public val mismatchOfValue: MismatchOfValue
    ) : TypeError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ParameterValueType>, ScaleWriter<ParameterValueType> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): ParameterValueType = try {
                ParameterValueType(
                    MismatchOfValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ParameterValueType) = try {
                MismatchOfValue.write(writer, instance.mismatchOfValue)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinitionId' variant
     */
    public data class AssetDefinitionId(
        public val mismatchOfAssetDefinitionId: MismatchOfAssetDefinitionId
    ) : TypeError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): AssetDefinitionId = try {
                AssetDefinitionId(
                    MismatchOfAssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId) = try {
                MismatchOfAssetDefinitionId.write(writer, instance.mismatchOfAssetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<TypeError>, ScaleWriter<TypeError> {
        public override fun read(reader: ScaleCodecReader): TypeError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AssetValueType.read(reader)
            1 -> ParameterValueType.read(reader)
            2 -> AssetDefinitionId.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: TypeError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AssetValueType.write(writer, instance as AssetValueType)
                1 -> ParameterValueType.write(writer, instance as ParameterValueType)
                2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
