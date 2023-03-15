//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.sorting

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * Sorting
 *
 * Generated from 'iroha_data_model::sorting::Sorting' regular structure
 */
public data class Sorting(
    public val sortByMetadataKey: Name? = null
) {
    public companion object : ScaleReader<Sorting>, ScaleWriter<Sorting> {
        public override fun read(reader: ScaleCodecReader): Sorting = try {
            Sorting(
                reader.readNullable(Name) as Name?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Sorting) = try {
            writer.writeNullable(Name, instance.sortByMetadataKey)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
