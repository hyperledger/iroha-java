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
import kotlin.collections.List

/**
 * UniqueVec
 *
 * Generated from 'UniqueVec<PeerId>' regular structure
 */
public data class UniqueVec<T0>(
    public val vecOfPeerId: List<PeerId>,
) {
    public companion object : ScaleReader<UniqueVec<out Any>>, ScaleWriter<UniqueVec<out Any>> {
        override fun read(reader: ScaleCodecReader): UniqueVec<out Any> = try {
            UniqueVec(
                reader.readVec(reader.readCompactInt()) { PeerId.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: UniqueVec<out Any>): Unit = try {
            writer.writeCompact(instance.vecOfPeerId.size)
            instance.vecOfPeerId.forEach { value ->
                PeerId.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
