//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.metadata

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long

/**
 * Limits
 *
 * Generated from 'iroha_data_model::metadata::Limits' regular structure
 */
public data class Limits(
    public val maxLen: Long,
    public val maxEntryByteSize: Long
) {
    public companion object : ScaleReader<Limits>, ScaleWriter<Limits> {
        public override fun read(reader: ScaleCodecReader): Limits = try {
            Limits(
                reader.readUint32(),
                reader.readUint32(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Limits) = try {
            writer.writeUint32(instance.maxLen)
            writer.writeUint32(instance.maxEntryByteSize)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
