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
 * Fail
 *
 * Generated from 'Fail' regular structure
 */
public data class Fail(
    public val message: String,
) {
    public companion object : ScaleReader<Fail>, ScaleWriter<Fail> {
        override fun read(reader: ScaleCodecReader): Fail = try {
            Fail(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Fail): Unit = try {
            writer.writeAsList(instance.message.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
