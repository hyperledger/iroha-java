//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.predicate.`value`

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Long

/**
 * AtIndex
 *
 * Generated from 'iroha_data_model::predicate::value::AtIndex' regular structure
 */
public data class AtIndex(
    public val index: Long,
    public val predicate: Predicate
) {
    public companion object : ScaleReader<AtIndex>, ScaleWriter<AtIndex> {
        public override fun read(reader: ScaleCodecReader): AtIndex = try {
            AtIndex(
                reader.readUint32(),
                Predicate.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AtIndex) = try {
            writer.writeUint32(instance.index)
            Predicate.write(writer, instance.predicate)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
