//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long
import kotlin.Unit

/**
 * SemiIntervalOfu32
 *
 * Generated from 'SemiIntervalOfu32' regular structure
 */
public data class SemiIntervalOfu32(
    public val start: Long,
    public val limit: Long,
) {
    public companion object : ScaleReader<SemiIntervalOfu32>, ScaleWriter<SemiIntervalOfu32> {
        override fun read(reader: ScaleCodecReader): SemiIntervalOfu32 = try {
            SemiIntervalOfu32(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SemiIntervalOfu32): Unit = try {
            writer.writeUint32(instance.start)
            writer.writeUint32(instance.limit)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
