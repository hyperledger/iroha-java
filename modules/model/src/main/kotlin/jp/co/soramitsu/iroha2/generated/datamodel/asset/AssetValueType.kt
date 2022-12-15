//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * AssetValueType
 *
 * Generated from 'iroha_data_model::asset::AssetValueType' enum
 */
public sealed class AssetValueType : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is Quantity -> Quantity.equals(this, other)
        is BigQuantity -> BigQuantity.equals(this, other)
        is Fixed -> Fixed.equals(this, other)
        is Store -> Store.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is Quantity -> Quantity.hashCode()
        is BigQuantity -> BigQuantity.hashCode()
        is Fixed -> Fixed.hashCode()
        is Store -> Store.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'Quantity' variant
     */
    public class Quantity : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Quantity>, ScaleWriter<Quantity> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Quantity = try {
                Quantity()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Quantity) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Quantity, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = 1
        }
    }

    /**
     * 'BigQuantity' variant
     */
    public class BigQuantity : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BigQuantity>, ScaleWriter<BigQuantity> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BigQuantity = try {
                BigQuantity()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BigQuantity) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: BigQuantity, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = 1
        }
    }

    /**
     * 'Fixed' variant
     */
    public class Fixed : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Fixed = try {
                Fixed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Fixed, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = 1
        }
    }

    /**
     * 'Store' variant
     */
    public class Store : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Store>, ScaleWriter<Store> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Store = try {
                Store()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Store) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Store, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = 1
        }
    }

    public companion object : ScaleReader<AssetValueType>, ScaleWriter<AssetValueType> {
        public override fun read(reader: ScaleCodecReader): AssetValueType = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Quantity.read(reader)
            1 -> BigQuantity.read(reader)
            2 -> Fixed.read(reader)
            3 -> Store.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetValueType) {
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
