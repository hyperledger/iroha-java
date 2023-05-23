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
 * Bool
 *
 * Generated from 'Bool' regular structure
 */
public data class Bool(
    public val bool: Bool
) {
    public companion object : ScaleReader<Bool>, ScaleWriter<Bool> {
        public override fun read(reader: ScaleCodecReader): Bool = try {
            Bool(
                Bool.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Bool) = try {
            Bool.write(writer, instance.bool)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
