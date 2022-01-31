//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignaturesOf
import jp.co.soramitsu.iroha2.wrapException

/**
 * ValidTransaction
 *
 * Generated from 'iroha_data_model::transaction::ValidTransaction' regular structure
 */
public data class ValidTransaction(
    public val payload: Payload,
    public val signatures: SignaturesOf<Payload>
) {
    public companion object : ScaleReader<ValidTransaction>, ScaleWriter<ValidTransaction> {
        public override fun read(reader: ScaleCodecReader): ValidTransaction = try {
            ValidTransaction(
                Payload.read(reader),
                SignaturesOf.read(reader) as SignaturesOf<Payload>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidTransaction) = try {
            Payload.write(writer, instance.payload)
            SignaturesOf.write(writer, instance.signatures)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
