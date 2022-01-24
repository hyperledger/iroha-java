//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * AssetUpdated
 *
 * Generated from 'iroha_data_model::events::data::AssetUpdated' enum
 */
public sealed class AssetUpdated : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Received' variant
     */
    public class Received : AssetUpdated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Received>, ScaleWriter<Received> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Received = try {
                Received()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Received) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Sent' variant
     */
    public class Sent : AssetUpdated() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Sent>, ScaleWriter<Sent> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Sent = try {
                Sent()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Sent) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AssetUpdated>, ScaleWriter<AssetUpdated> {
        public override fun read(reader: ScaleCodecReader): AssetUpdated = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Received.read(reader)
            1 -> Sent.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AssetUpdated) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Received.write(writer, instance as Received)
                1 -> Sent.write(writer, instance as Sent)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
