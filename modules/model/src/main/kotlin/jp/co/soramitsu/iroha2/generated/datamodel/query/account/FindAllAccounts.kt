//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter

/**
 * FindAllAccounts
 *
 * Generated from 'iroha_data_model::query::account::FindAllAccounts' regular structure
 */
public class FindAllAccounts {
    public companion object : ScaleReader<FindAllAccounts>, ScaleWriter<FindAllAccounts> {
        public override fun read(reader: ScaleCodecReader): FindAllAccounts = FindAllAccounts()

        public override fun write(writer: ScaleCodecWriter, instance: FindAllAccounts) {
        }
    }
}
