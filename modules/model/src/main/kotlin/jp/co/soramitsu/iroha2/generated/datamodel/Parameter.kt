//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Parameter
 *
 * Generated from 'iroha_data_model::Parameter' regular structure
 */
public data class Parameter(
    public val id: Id,
    public val `val`: Value
) {
    public companion object : ScaleReader<Parameter>, ScaleWriter<Parameter> {
        public override fun read(reader: ScaleCodecReader): Parameter = try {
            Parameter(
                Id.read(reader),
                Value.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Parameter) = try {
            Id.write(writer, instance.id)
            Value.write(writer, instance.`val`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
