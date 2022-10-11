//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.permission.token

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.generated.datamodel.ValueKind
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.Map

/**
 * Definition
 *
 * Generated from 'iroha_data_model::permission::token::Definition' regular structure
 */
public data class Definition(
    public val id: TokenId,
    public val params: Map<Name, ValueKind>
) {
    public companion object : ScaleReader<Definition>, ScaleWriter<Definition> {
        public override fun read(reader: ScaleCodecReader): Definition = try {
            Definition(
                TokenId.read(reader),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { ValueKind.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Definition) = try {
            TokenId.write(writer, instance.id)
            writer.writeCompact(instance.params.size)
            instance.params.toSortedMap(
                Name.comparator()
            ).forEach { (key, value) ->
                Name.write(writer, key)
                ValueKind.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
