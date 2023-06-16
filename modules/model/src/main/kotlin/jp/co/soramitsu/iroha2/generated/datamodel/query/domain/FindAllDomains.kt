//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.domain

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * FindAllDomains
 *
 * Generated from 'iroha_data_model::query::domain::FindAllDomains' tuple structure
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

        public fun equals(o1: FindAllDomains, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = "datamodel.query.domain.FindAllDomains".hashCode()
    }
}
