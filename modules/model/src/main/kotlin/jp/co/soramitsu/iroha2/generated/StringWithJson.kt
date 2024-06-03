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
 * StringWithJson
 *
 * Generated from 'StringWithJson' regular structure
 */
public data class StringWithJson(
    public val string: String,
) {
    public companion object : ScaleReader<StringWithJson>, ScaleWriter<StringWithJson> {
        override fun read(reader: ScaleCodecReader): StringWithJson = try {
            StringWithJson(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: StringWithJson): Unit = try {
            writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
