//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray
import kotlin.Unit

/**
 * PublicKey
 *
 * Generated from 'PublicKey' regular structure
 */
public data class PublicKey(
    public val algorithm: Algorithm,
    public val payload: ByteArray,
) {
    public companion object : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
        override fun read(reader: ScaleCodecReader): PublicKey = try {
            PublicKey(
                Algorithm.read(reader),
                reader.readByteArray(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PublicKey): Unit = try {
            Algorithm.write(writer, instance.algorithm)
            writer.writeAsList(instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
