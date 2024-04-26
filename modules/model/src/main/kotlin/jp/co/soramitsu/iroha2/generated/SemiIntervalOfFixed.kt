//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * SemiIntervalOfFixed
 *
 * Generated from 'SemiIntervalOfFixed' regular structure
 */
public data class SemiIntervalOfFixed(
    public val start: Fixed,
    public val limit: Fixed,
) {
    public companion object : ScaleReader<SemiIntervalOfFixed>, ScaleWriter<SemiIntervalOfFixed> {
        override fun read(reader: ScaleCodecReader): SemiIntervalOfFixed = try {
            SemiIntervalOfFixed(
                Fixed.read(reader),
                Fixed.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SemiIntervalOfFixed): Unit = try {
            Fixed.write(writer, instance.start)
            Fixed.write(writer, instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
