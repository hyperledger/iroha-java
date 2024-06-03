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
 * PipelineEventFilter
 *
 * Generated from 'PipelineEventFilter' regular structure
 */
public data class PipelineEventFilter(
    public val entityKind: PipelineEntityKind? = null,
    public val statusKind: PipelineStatusKind? = null,
    public val hash: Hash? = null,
) {
    public companion object : ScaleReader<PipelineEventFilter>, ScaleWriter<PipelineEventFilter> {
        override fun read(reader: ScaleCodecReader): PipelineEventFilter = try {
            PipelineEventFilter(
                reader.readNullable(PipelineEntityKind) as PipelineEntityKind?,
                reader.readNullable(PipelineStatusKind) as PipelineStatusKind?,
                reader.readNullable(Hash) as Hash?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PipelineEventFilter): Unit = try {
            writer.writeNullable(PipelineEntityKind, instance.entityKind)
            writer.writeNullable(PipelineStatusKind, instance.statusKind)
            writer.writeNullable(Hash, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
