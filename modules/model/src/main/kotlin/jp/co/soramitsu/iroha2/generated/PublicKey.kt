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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PublicKey

        if (algorithm != other.algorithm) return false
        return payload.contentEquals(other.payload)
    }

    override fun hashCode(): Int {
        var result = algorithm.hashCode()
        result = 31 * result + payload.contentHashCode()
        return result
    } // TODO: добавить в генератор
}
