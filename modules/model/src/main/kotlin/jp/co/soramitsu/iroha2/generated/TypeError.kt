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
        public val mismatch: Mismatch<jp.co.soramitsu.iroha2.generated.AssetValueType>,
    ) : TypeError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TypeError.AssetValueType>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TypeError.AssetValueType> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TypeError.AssetValueType = try {
                AssetValueType(
                    Mismatch.read(reader) as Mismatch<jp.co.soramitsu.iroha2.generated.AssetValueType>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TypeError.AssetValueType,
            ): Unit = try {
                Mismatch.write(writer, instance.mismatch)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NumericAssetValueTypeExpected' variant
     */
    public data class NumericAssetValueTypeExpected(
        public val assetValueType: jp.co.soramitsu.iroha2.generated.AssetValueType,
    ) : TypeError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TypeError.NumericAssetValueTypeExpected>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TypeError.NumericAssetValueTypeExpected> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TypeError.NumericAssetValueTypeExpected = try {
                NumericAssetValueTypeExpected(
                    jp.co.soramitsu.iroha2.generated.AssetValueType.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TypeError.NumericAssetValueTypeExpected,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.AssetValueType.write(writer, instance.assetValueType)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'StoreAssetValueTypeExpected' variant
     */
    public data class StoreAssetValueTypeExpected(
        public val assetValueType: jp.co.soramitsu.iroha2.generated.AssetValueType,
    ) : TypeError() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.TypeError.StoreAssetValueTypeExpected>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.TypeError.StoreAssetValueTypeExpected> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.TypeError.StoreAssetValueTypeExpected = try {
                StoreAssetValueTypeExpected(
                    jp.co.soramitsu.iroha2.generated.AssetValueType.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.TypeError.StoreAssetValueTypeExpected,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.AssetValueType.write(writer, instance.assetValueType)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    public companion object : ScaleReader<TypeError>, ScaleWriter<TypeError> {
        override fun read(reader: ScaleCodecReader): TypeError = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AssetValueType.read(reader)
            1 -> NumericAssetValueTypeExpected.read(reader)
            2 -> StoreAssetValueTypeExpected.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: TypeError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AssetValueType.write(writer, instance as AssetValueType)
                1 -> NumericAssetValueTypeExpected.write(writer, instance as NumericAssetValueTypeExpected)
                2 -> StoreAssetValueTypeExpected.write(writer, instance as StoreAssetValueTypeExpected)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
