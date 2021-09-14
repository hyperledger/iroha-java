//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.domain

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllDomains
 *
 * Generated from 'iroha_data_model::query::domain::FindAllDomains' regular structure
 */
public class FindAllDomains {
    public companion object : ScaleReader<FindAllDomains>, ScaleWriter<FindAllDomains> {
        public override fun read(reader: ScaleCodecReader): FindAllDomains = try {
            FindAllDomains()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllDomains) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
