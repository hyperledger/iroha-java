//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Long
import kotlin.collections.Map

/**
 * Payload
 *
 * Generated from 'iroha_data_model::transaction::Payload' regular structure
 */
public data class Payload(
    public val accountId: Id,
    public val instructions: Executable,
    public val creationTime: BigInteger,
    public val timeToLiveMs: BigInteger,
    public val nonce: Long?,
    public val metadata: Map<Name, Value>
) {
    public companion object : ScaleReader<Payload>, ScaleWriter<Payload> {
        public override fun read(reader: ScaleCodecReader): Payload = try {
            Payload(
                Id.read(reader),
                Executable.read(reader),
                reader.readUint64(),
                reader.readUint64(),
                reader.readNullable(),
                reader.readMap(reader.readCompactInt(), { Name.read(reader) }, { Value.read(reader) }),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Payload) = try {
            Id.write(writer, instance.accountId)
            Executable.write(writer, instance.instructions)
            writer.writeUint64(instance.creationTime)
            writer.writeUint64(instance.timeToLiveMs)
            writer.writeNullable(instance.nonce)
            writer.writeCompact(instance.metadata.size)
            instance.metadata.toSortedMap(Comparator.comparing(Name::string)).forEach { (key, value) ->  
                Name.write(writer, key)
                Value.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
