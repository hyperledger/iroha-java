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
 * PipelineEvent
 *
 * Generated from 'PipelineEvent' regular structure
 */
public data class PipelineEvent(
    public val entityKind: PipelineEntityKind,
    public val status: PipelineStatus,
    public val hash: Hash,
) {
    public companion object : ScaleReader<PipelineEvent>, ScaleWriter<PipelineEvent> {
        override fun read(reader: ScaleCodecReader): PipelineEvent = try {
            PipelineEvent(
                PipelineEntityKind.read(reader),
                PipelineStatus.read(reader),
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: PipelineEvent): Unit = try {
            PipelineEntityKind.write(writer, instance.entityKind)
            PipelineStatus.write(writer, instance.status)
            Hash.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
