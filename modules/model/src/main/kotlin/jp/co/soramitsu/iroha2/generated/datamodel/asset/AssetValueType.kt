//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int

/**
 * AssetValueType
 *
 * Generated from 'iroha_data_model::asset::AssetValueType' enum
 */
public sealed class AssetValueType {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Quantity' variant
     */
    public class Quantity : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Quantity>, ScaleWriter<Quantity> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Quantity = Quantity()

            public override fun write(writer: ScaleCodecWriter, instance: Quantity) {
            }
        }
    }

    /**
     * 'BigQuantity' variant
     */
    public class BigQuantity : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BigQuantity>, ScaleWriter<BigQuantity> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): BigQuantity = BigQuantity()

            public override fun write(writer: ScaleCodecWriter, instance: BigQuantity) {
            }
        }
    }

    /**
     * 'Fixed' variant
     */
    public class Fixed : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Fixed = Fixed()

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) {
            }
        }
    }

    /**
     * 'Store' variant
     */
    public class Store : AssetValueType() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Store>, ScaleWriter<Store> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Store = Store()

            public override fun write(writer: ScaleCodecWriter, instance: Store) {
            }
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
