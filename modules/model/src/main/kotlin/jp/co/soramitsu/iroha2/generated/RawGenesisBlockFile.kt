//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * RawGenesisBlockFile
 *
 * Generated from 'RawGenesisBlockFile' regular structure
 */
public data class RawGenesisBlockFile(
    public val transactions: List<GenesisTransactionBuilder>,
    public val executorFile: String,
) {
    public companion object : ScaleReader<RawGenesisBlockFile>, ScaleWriter<RawGenesisBlockFile> {
        override fun read(reader: ScaleCodecReader): RawGenesisBlockFile = try {
            RawGenesisBlockFile(
                reader.readVec(reader.readCompactInt()) { GenesisTransactionBuilder.read(reader) },
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlockFile): Unit = try {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                GenesisTransactionBuilder.write(writer, value)
            }
            writer.writeAsList(instance.executorFile.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
