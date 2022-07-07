//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.error

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.permissions.ValidatorType
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * Mismatch
 *
 * Generated from
 * 'iroha_core::smartcontracts::isi::error::Mismatch<iroha_core::smartcontracts::isi::permissions::ValidatorType>'
 * regular structure
 */
public data class Mismatch<T0>(
    public val expected: ValidatorType,
    public val `actual`: ValidatorType
) {
    public companion object : ScaleReader<Mismatch<out Any>>, ScaleWriter<Mismatch<out Any>> {
        public override fun read(reader: ScaleCodecReader): Mismatch<out Any> = try {
            Mismatch(
                ValidatorType.read(reader),
                ValidatorType.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Mismatch<out Any>) = try {
            ValidatorType.write(writer, instance.expected)
            ValidatorType.write(writer, instance.`actual`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
