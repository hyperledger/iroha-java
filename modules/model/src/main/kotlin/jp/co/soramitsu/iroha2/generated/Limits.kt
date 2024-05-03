//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long
import kotlin.Unit

/**
 * Limits
 *
 * Generated from 'Limits' regular structure
 */
public data class Limits(
    public val capacity: Long,
    public val maxEntryLen: Long,
) {
    public companion object : ScaleReader<Limits>, ScaleWriter<Limits> {
        override fun read(reader: ScaleCodecReader): Limits = try {
            Limits(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Limits): Unit = try {
            writer.writeUint32(instance.capacity)
            writer.writeUint32(instance.maxEntryLen)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
