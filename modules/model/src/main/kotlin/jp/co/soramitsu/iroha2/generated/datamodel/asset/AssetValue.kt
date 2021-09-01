//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import java.math.BigInteger
import kotlin.Int
import kotlin.UInt

/**
 * AssetValue
 *
 * Generated from 'iroha_data_model::asset::AssetValue' enum
 */
public sealed class AssetValue {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Quantity' variant
     */
    public data class Quantity(
        public val u32: UInt
    ) : AssetValue() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Quantity>, ScaleWriter<Quantity> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Quantity = Quantity(
                reader.readUint32().toUInt(),
            )

            public override fun write(writer: ScaleCodecWriter, instance: Quantity) {
                writer.writeUint32(instance.u32.toInt())
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

            public override fun read(reader: ScaleCodecReader): BigQuantity = BigQuantity(
                reader.readUint128(),
            )

            public override fun write(writer: ScaleCodecWriter, instance: BigQuantity) {
                writer.writeUint128(instance.u128)
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
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Store = Store(
                Metadata.read(reader),
            )

            public override fun write(writer: ScaleCodecWriter, instance: Store) {
                Metadata.write(writer, instance.metadata)
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
            2 -> Store.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetValue) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Quantity.write(writer, instance as Quantity)
                1 -> BigQuantity.write(writer, instance as BigQuantity)
                2 -> Store.write(writer, instance as Store)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
