//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * ContextValue
 *
 * Generated from 'ContextValue' regular structure
 */
public data class ContextValue(
    public val valueName: Name,
) {
    public companion object : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
        override fun read(reader: ScaleCodecReader): ContextValue = try {
            ContextValue(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ContextValue): Unit = try {
            Name.write(writer, instance.valueName)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
