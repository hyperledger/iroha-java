//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * AccountFilter
 *
 * Generated from 'AccountFilter' regular structure
 */
public data class AccountFilter(
    public val originFilter: FilterOpt<OriginFilter<AccountEvent>>,
    public val eventFilter: FilterOpt<AccountEventFilter>
) {
    public companion object : ScaleReader<AccountFilter>, ScaleWriter<AccountFilter> {
        public override fun read(reader: ScaleCodecReader): AccountFilter = try {
            AccountFilter(
                FilterOpt.read(reader) as FilterOpt<OriginFilter<AccountEvent>>,
                FilterOpt.read(reader) as FilterOpt<AccountEventFilter>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountFilter) = try {
            FilterOpt.write(writer, instance.originFilter)
            FilterOpt.write(writer, instance.eventFilter)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
