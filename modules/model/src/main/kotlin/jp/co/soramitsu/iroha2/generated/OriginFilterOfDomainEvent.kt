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
 * OriginFilterOfDomainEvent
 *
 * Generated from 'OriginFilterOfDomainEvent' regular structure
 */
public data class OriginFilterOfDomainEvent(
    public val domainId: DomainId
) {
    public companion object :
        ScaleReader<OriginFilterOfDomainEvent>,
        ScaleWriter<OriginFilterOfDomainEvent> {
        public override fun read(reader: ScaleCodecReader): OriginFilterOfDomainEvent = try {
            OriginFilterOfDomainEvent(
                DomainId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: OriginFilterOfDomainEvent) = try {
            DomainId.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
