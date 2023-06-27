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
 * RepetitionError
 *
 * Generated from 'RepetitionError' regular structure
 */
public data class RepetitionError(
    public val instructionType: InstructionType,
    public val id: IdBox
) {
    public companion object : ScaleReader<RepetitionError>, ScaleWriter<RepetitionError> {
        public override fun read(reader: ScaleCodecReader): RepetitionError = try {
            RepetitionError(
                InstructionType.read(reader),
                IdBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RepetitionError) = try {
            InstructionType.write(writer, instance.instructionType)
            IdBox.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
