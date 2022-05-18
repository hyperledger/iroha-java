//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * FindAllAccounts
 *
 * Generated from 'iroha_data_model::query::account::FindAllAccounts' tuple structure
 */
public class FindAllAccounts {
    public companion object : ScaleReader<FindAllAccounts>, ScaleWriter<FindAllAccounts> {
        public override fun read(reader: ScaleCodecReader): FindAllAccounts = try {
            FindAllAccounts()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllAccounts) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
