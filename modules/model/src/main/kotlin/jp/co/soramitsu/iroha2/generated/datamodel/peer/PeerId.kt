//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.peer

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * PeerId
 *
 * Generated from 'iroha_data_model::peer::PeerId' regular structure
 */
public data class PeerId(
    public val address: String,
    public val publicKey: PublicKey
) {
    public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
        public override fun read(reader: ScaleCodecReader): PeerId = try {
            PeerId(
                reader.readString(),
                PublicKey.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerId) = try {
            writer.writeAsList(instance.address.toByteArray(Charsets.UTF_8))
            PublicKey.write(writer, instance.publicKey)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
