//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.sumeragi.viewchange

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * NoTransactionReceiptReceived
 *
 * Generated from 'iroha_core::sumeragi::view_change::NoTransactionReceiptReceived' tuple structure
 */
public class NoTransactionReceiptReceived {
    public companion object :
        ScaleReader<NoTransactionReceiptReceived>,
        ScaleWriter<NoTransactionReceiptReceived> {
        public override fun read(reader: ScaleCodecReader): NoTransactionReceiptReceived = try {
            NoTransactionReceiptReceived()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NoTransactionReceiptReceived) =
            try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
