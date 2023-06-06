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
 * MismatchOfValue
 *
 * Generated from 'MismatchOfValue' regular structure
 */
public data class MismatchOfValue(
    public val expected: Value,
    public val `actual`: Value
) {
    public companion object : ScaleReader<MismatchOfValue>, ScaleWriter<MismatchOfValue> {
        public override fun read(reader: ScaleCodecReader): MismatchOfValue = try {
            MismatchOfValue(
                Value.read(reader),
                Value.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: MismatchOfValue) = try {
            Value.write(writer, instance.expected)
            Value.write(writer, instance.`actual`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
