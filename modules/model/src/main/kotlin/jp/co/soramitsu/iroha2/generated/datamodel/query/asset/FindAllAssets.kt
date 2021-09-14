//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllAssets
 *
 * Generated from 'iroha_data_model::query::asset::FindAllAssets' regular structure
 */
public class FindAllAssets {
    public companion object : ScaleReader<FindAllAssets>, ScaleWriter<FindAllAssets> {
        public override fun read(reader: ScaleCodecReader): FindAllAssets = try {
            FindAllAssets()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllAssets) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
