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
 * Parameter
 *
 * Generated from 'Parameter' regular structure
 */
public data class Parameter(
    public val id: ParameterId,
    public val `val`: Value,
) {
    public companion object : ScaleReader<Parameter>, ScaleWriter<Parameter> {
        override fun read(reader: ScaleCodecReader): Parameter = try {
            Parameter(
                ParameterId.read(reader),
                Value.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Parameter): Unit = try {
            ParameterId.write(writer, instance.id)
            Value.write(writer, instance.`val`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
