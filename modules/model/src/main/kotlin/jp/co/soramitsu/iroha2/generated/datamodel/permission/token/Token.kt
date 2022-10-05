//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.token

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.Map

/**
 * Token
 *
 * Generated from 'iroha_data_model::permission::token::Token' regular structure
 */
public data class Token(
    public val definitionId: TokenId,
    public val params: Map<Name, Value>
) {
    public companion object : ScaleReader<Token>, ScaleWriter<Token> {
        public override fun read(reader: ScaleCodecReader): Token = try {
            Token(
                TokenId.read(reader),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Token) = try {
            TokenId.write(writer, instance.definitionId)
            writer.writeCompact(instance.params.size)
            instance.params.toSortedMap(
                Name.comparator()
            ).forEach { (key, value) ->
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
