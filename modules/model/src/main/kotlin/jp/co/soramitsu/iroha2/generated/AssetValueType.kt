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
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Unit

/**
 * AssetValueType
 *
 * Generated from 'AssetValueType' enum
 */
public sealed class AssetValueType : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    override fun equals(other: Any?): Boolean = when (this) {
        is Quantity -> Quantity.equals(this, other)
        is BigQuantity -> BigQuantity.equals(this, other)
        is Fixed -> Fixed.equals(this, other)
        is Store -> Store.equals(this, other)
        else -> super.equals(other) }

    override fun hashCode(): Int = when (this) {
        is Quantity -> Quantity.hashCode()
        is BigQuantity -> BigQuantity.hashCode()
        is Fixed -> Fixed.hashCode()
        is Store -> Store.hashCode()
        else -> super.hashCode() }

    /**
     * 'Quantity' variant
     */
    public class Quantity : AssetValueType() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValueType.Quantity>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValueType.Quantity> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValueType.Quantity = try {
                Quantity()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValueType.Quantity,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetValueType.Quantity, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetValueType.Quantity".hashCode()
        }
    }

    /**
     * 'BigQuantity' variant
     */
    public class BigQuantity : AssetValueType() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValueType.BigQuantity>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValueType.BigQuantity> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValueType.BigQuantity = try {
                BigQuantity()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValueType.BigQuantity,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetValueType.BigQuantity, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetValueType.BigQuantity".hashCode()
        }
    }

    /**
     * 'Fixed' variant
     */
    public class Fixed : AssetValueType() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValueType.Fixed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValueType.Fixed> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValueType.Fixed = try {
                Fixed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValueType.Fixed,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetValueType.Fixed, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetValueType.Fixed".hashCode()
        }
    }

    /**
     * 'Store' variant
     */
    public class Store : AssetValueType() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValueType.Store>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValueType.Store> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValueType.Store = try {
                Store()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValueType.Store,
            ): Unit = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: jp.co.soramitsu.iroha2.generated.AssetValueType.Store, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            override fun hashCode(): Int = ".AssetValueType.Store".hashCode()
        }
    }

    public companion object : ScaleReader<AssetValueType>, ScaleWriter<AssetValueType> {
        override fun read(reader: ScaleCodecReader): AssetValueType = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Quantity.read(reader)
            1 -> BigQuantity.read(reader)
            2 -> Fixed.read(reader)
            3 -> Store.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AssetValueType) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Quantity.write(writer, instance as Quantity)
                1 -> BigQuantity.write(writer, instance as BigQuantity)
                2 -> Fixed.write(writer, instance as Fixed)
                3 -> Store.write(writer, instance as Store)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
