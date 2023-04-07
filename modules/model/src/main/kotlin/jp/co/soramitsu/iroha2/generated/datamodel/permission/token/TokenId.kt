//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.token

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException

/**
 * TokenId
 *
 * Generated from 'iroha_data_model::permission::token::TokenId' regular structure
 */
public data class TokenId(
    public val name: Name
) {
    public companion object : ScaleReader<TokenId>, ScaleWriter<TokenId> {
        public override fun read(reader: ScaleCodecReader): TokenId = try {
            TokenId(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: TokenId) = try {
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
