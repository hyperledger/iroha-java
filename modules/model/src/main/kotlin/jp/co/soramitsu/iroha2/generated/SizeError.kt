//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger

/**
 * SizeError
 *
 * Generated from 'SizeError' regular structure
 */
public data class SizeError(
    public val limits: Limits,
    public val `actual`: BigInteger
) {
    public companion object : ScaleReader<SizeError>, ScaleWriter<SizeError> {
        public override fun read(reader: ScaleCodecReader): SizeError = try {
            SizeError(
                Limits.read(reader),
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SizeError) = try {
            Limits.write(writer, instance.limits)
            writer.writeUint64(instance.`actual`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
