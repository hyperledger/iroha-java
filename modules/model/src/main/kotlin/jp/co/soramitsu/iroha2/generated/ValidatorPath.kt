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
 * ValidatorPath
 *
 * Generated from 'ValidatorPath' regular structure
 */
public data class ValidatorPath(
    public val validatorRelativePath: String
) {
    public companion object : ScaleReader<ValidatorPath>, ScaleWriter<ValidatorPath> {
        public override fun read(reader: ScaleCodecReader): ValidatorPath = try {
            ValidatorPath(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidatorPath) = try {
            writer.writeAsList(instance.validatorRelativePath.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
