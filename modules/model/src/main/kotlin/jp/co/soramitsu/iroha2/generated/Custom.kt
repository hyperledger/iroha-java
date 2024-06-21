//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit

/**
 * Custom
 *
 * Generated from 'Custom' regular structure
 */
public data class Custom(
    public val payload: String,
) {
    public companion object : ScaleReader<Custom>, ScaleWriter<Custom> {
        override fun read(reader: ScaleCodecReader): Custom = try {
            Custom(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Custom): Unit = try {
            writer.writeAsList(instance.payload.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
