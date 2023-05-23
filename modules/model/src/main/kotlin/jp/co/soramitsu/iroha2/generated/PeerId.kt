//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * PeerId
 *
 * Generated from 'PeerId' regular structure
 */
public data class PeerId(
    public val address: SocketAddr,
    public val publicKey: PublicKey
) {
    public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
        public override fun read(reader: ScaleCodecReader): PeerId = try {
            PeerId(
                SocketAddr.read(reader),
                PublicKey.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PeerId) = try {
            SocketAddr.write(writer, instance.address)
            PublicKey.write(writer, instance.publicKey)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
