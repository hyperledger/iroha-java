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
import java.math.BigInteger
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * AssetValue
 *
 * Generated from 'AssetValue' enum
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
        public val u32: Long,
    ) : AssetValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValue.Quantity>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValue.Quantity> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValue.Quantity = try {
                Quantity(
                    reader.readUint32(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValue.Quantity,
            ): Unit = try {
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
        public val u128: BigInteger,
    ) : AssetValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValue.BigQuantity>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValue.BigQuantity> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValue.BigQuantity = try {
                BigQuantity(
                    reader.readUint128(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValue.BigQuantity,
            ): Unit = try {
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
        public val fixed: jp.co.soramitsu.iroha2.generated.Fixed,
    ) : AssetValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValue.Fixed>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValue.Fixed> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValue.Fixed = try {
                Fixed(
                    jp.co.soramitsu.iroha2.generated.Fixed.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValue.Fixed,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Fixed.write(writer, instance.fixed)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Store' variant
     */
    public data class Store(
        public val metadata: Metadata,
    ) : AssetValue() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AssetValue.Store>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AssetValue.Store> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AssetValue.Store = try {
                Store(
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AssetValue.Store,
            ): Unit = try {
                Metadata.write(writer, instance.metadata)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AssetValue>, ScaleWriter<AssetValue> {
        override fun read(reader: ScaleCodecReader): AssetValue = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Quantity.read(reader)
            1 -> BigQuantity.read(reader)
            2 -> Fixed.read(reader)
            3 -> Store.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AssetValue) {
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
