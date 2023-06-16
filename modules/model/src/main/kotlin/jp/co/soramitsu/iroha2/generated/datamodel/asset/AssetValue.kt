//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Int
import kotlin.Long

/**
 * AssetValue
 *
 * Generated from 'iroha_data_model::asset::AssetValue' enum
 */
public sealed class AssetValue : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Quantity' variant
     */
    public data class Quantity(
        public val u32: Long
    ) : AssetValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Quantity>, ScaleWriter<Quantity> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Quantity = try {
                Quantity(
                    reader.readUint32(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Quantity) = try {
                writer.writeUint32(instance.u32)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'BigQuantity' variant
     */
    public data class BigQuantity(
        public val u128: BigInteger
    ) : AssetValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BigQuantity>, ScaleWriter<BigQuantity> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BigQuantity = try {
                BigQuantity(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BigQuantity) = try {
                writer.writeUint128(instance.u128)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Fixed' variant
     */
    public data class Fixed(
        public val fixed: jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed
    ) : AssetValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Fixed = try {
                Fixed(
                    jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
                jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed.write(writer, instance.fixed)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Store' variant
     */
    public data class Store(
        public val metadata: Metadata
    ) : AssetValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Store>, ScaleWriter<Store> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Store = try {
                Store(
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Store) = try {
                Metadata.write(writer, instance.metadata)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AssetValue>, ScaleWriter<AssetValue> {
        public override fun read(reader: ScaleCodecReader): AssetValue = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Quantity.read(reader)
            1 -> BigQuantity.read(reader)
            2 -> Fixed.read(reader)
            3 -> Store.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetValue) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Quantity.write(writer, instance as Quantity)
                1 -> BigQuantity.write(writer, instance as BigQuantity)
                2 -> Fixed.write(writer, instance as Fixed)
                3 -> Store.write(writer, instance as Store)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
