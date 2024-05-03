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
 * NewParameter
 *
 * Generated from 'NewParameter' regular structure
 */
public data class NewParameter(
    public val parameter: Parameter,
) {
    public companion object : ScaleReader<NewParameter>, ScaleWriter<NewParameter> {
        override fun read(reader: ScaleCodecReader): NewParameter = try {
            NewParameter(
                Parameter.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: NewParameter): Unit = try {
            Parameter.write(writer, instance.parameter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
