//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Unit

/**
 * Mismatch
 *
 * Generated from 'Mismatch<AssetValueType>' regular structure
 */
public data class Mismatch<T0>(
    public val expected: AssetValueType,
    public val `actual`: AssetValueType,
) {
    public companion object : ScaleReader<Mismatch<out Any>>, ScaleWriter<Mismatch<out Any>> {
        override fun read(reader: ScaleCodecReader): Mismatch<out Any> = try {
            Mismatch(
                AssetValueType.read(reader),
                AssetValueType.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Mismatch<out Any>): Unit = try {
            AssetValueType.write(writer, instance.expected)
            AssetValueType.write(writer, instance.`actual`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
