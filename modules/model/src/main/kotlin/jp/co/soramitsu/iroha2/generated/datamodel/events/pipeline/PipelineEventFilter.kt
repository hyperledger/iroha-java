//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.wrapException

/**
 * PipelineEventFilter
 *
 * Generated from 'iroha_data_model::events::pipeline::PipelineEventFilter' regular structure
 */
public data class PipelineEventFilter(
    public val entityKind: EntityKind? = null,
    public val statusKind: StatusKind? = null,
    public val hash: Hash? = null
) {
    public companion object : ScaleReader<PipelineEventFilter>, ScaleWriter<PipelineEventFilter> {
        public override fun read(reader: ScaleCodecReader): PipelineEventFilter = try {
            PipelineEventFilter(
                reader.readNullable(EntityKind) as EntityKind?,
                reader.readNullable(StatusKind) as StatusKind?,
                reader.readNullable(Hash) as Hash?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PipelineEventFilter) = try {
            writer.writeNullable(EntityKind, instance.entityKind)
            writer.writeNullable(StatusKind, instance.statusKind)
            writer.writeNullable(Hash, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
