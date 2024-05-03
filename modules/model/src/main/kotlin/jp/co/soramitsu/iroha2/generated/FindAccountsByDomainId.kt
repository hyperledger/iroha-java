//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * FindAccountsByDomainId
 *
 * Generated from 'FindAccountsByDomainId' regular structure
 */
public data class FindAccountsByDomainId(
    public val domainId: DomainId,
) {
    public companion object : ScaleReader<FindAccountsByDomainId>, ScaleWriter<FindAccountsByDomainId> {
        override fun read(reader: ScaleCodecReader): FindAccountsByDomainId = try {
            FindAccountsByDomainId(
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainId): Unit = try {
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
