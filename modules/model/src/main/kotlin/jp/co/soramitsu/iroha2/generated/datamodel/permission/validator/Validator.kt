//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.validator

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.WasmSmartContract
import jp.co.soramitsu.iroha2.wrapException

/**
 * Validator
 *
 * Generated from 'iroha_data_model::permission::validator::Validator' regular structure
 */
public data class Validator(
    public val id: ValidatorId,
    public val validatorType: Type,
    public val wasm: WasmSmartContract
) {
    public companion object : ScaleReader<Validator>, ScaleWriter<Validator> {
        public override fun read(reader: ScaleCodecReader): Validator = try {
            Validator(
                ValidatorId.read(reader),
                Type.read(reader),
                WasmSmartContract.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Validator) = try {
            ValidatorId.write(writer, instance.id)
            Type.write(writer, instance.validatorType)
            WasmSmartContract.write(writer, instance.wasm)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
