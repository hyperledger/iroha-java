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
 * FindAllAssetsDefinitions
 *
 * Generated from 'iroha_data_model::query::asset::FindAllAssetsDefinitions' regular structure
 */
public class FindAllAssetsDefinitions {
    public companion object :
        ScaleReader<FindAllAssetsDefinitions>,
        ScaleWriter<FindAllAssetsDefinitions> {
        public override fun read(reader: ScaleCodecReader): FindAllAssetsDefinitions = try {
            FindAllAssetsDefinitions()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllAssetsDefinitions) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
