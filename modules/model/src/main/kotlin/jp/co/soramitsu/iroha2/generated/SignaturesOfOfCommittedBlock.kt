//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit
import kotlin.collections.List

/**
 * SignaturesOfOfCommittedBlock
 *
 * Generated from 'SignaturesOfOfCommittedBlock' regular structure
 */
public data class SignaturesOfOfCommittedBlock(
    public val signatures: List<SignatureOf<CommittedBlock>>,
) {
    public companion object :
        ScaleReader<SignaturesOfOfCommittedBlock>,
        ScaleWriter<SignaturesOfOfCommittedBlock> {
        override fun read(reader: ScaleCodecReader): SignaturesOfOfCommittedBlock = try {
            SignaturesOfOfCommittedBlock(
                reader.readVec(reader.readCompactInt()) {
                    SignatureOf.read(reader) as
                        SignatureOf<CommittedBlock>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignaturesOfOfCommittedBlock): Unit = try {
            writer.writeCompact(instance.signatures.size)
            instance.signatures.sortedWith(
                SignatureOf.comparator(),
            ).forEach { value ->
                SignatureOf.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
