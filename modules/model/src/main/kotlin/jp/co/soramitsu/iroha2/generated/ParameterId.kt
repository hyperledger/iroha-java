//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * ParameterId
 *
 * Generated from 'ParameterId' regular structure
 */
public data class ParameterId(
    public val name: Name
) {
    public companion object : ScaleReader<ParameterId>, ScaleWriter<ParameterId> {
        public override fun read(reader: ScaleCodecReader): ParameterId = try {
            ParameterId(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ParameterId) = try {
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
