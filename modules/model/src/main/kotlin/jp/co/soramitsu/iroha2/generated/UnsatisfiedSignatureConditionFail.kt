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

/**
 * UnsatisfiedSignatureConditionFail
 *
 * Generated from 'UnsatisfiedSignatureConditionFail' regular structure
 */
public data class UnsatisfiedSignatureConditionFail(
    public val reason: String
) {
    public companion object :
        ScaleReader<UnsatisfiedSignatureConditionFail>,
        ScaleWriter<UnsatisfiedSignatureConditionFail> {
        public override fun read(reader: ScaleCodecReader): UnsatisfiedSignatureConditionFail = try {
            UnsatisfiedSignatureConditionFail(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: UnsatisfiedSignatureConditionFail) =
            try {
                writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
