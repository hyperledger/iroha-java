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
 * JsonString
 *
 * Generated from 'JsonString' regular structure
 */
public data class JsonString(
    public val string: String,
) {
    public companion object : ScaleReader<JsonString>, ScaleWriter<JsonString> {
        override fun read(reader: ScaleCodecReader): JsonString = try {
            JsonString(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: JsonString): Unit = try {
            writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
