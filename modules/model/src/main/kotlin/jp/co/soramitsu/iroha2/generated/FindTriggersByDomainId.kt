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
 * FindTriggersByDomainId
 *
 * Generated from 'FindTriggersByDomainId' regular structure
 */
public data class FindTriggersByDomainId(
    public val domainId: EvaluatesTo<DomainId>
) {
    public companion object : ScaleReader<FindTriggersByDomainId>, ScaleWriter<FindTriggersByDomainId> {
        public override fun read(reader: ScaleCodecReader): FindTriggersByDomainId = try {
            FindTriggersByDomainId(
                EvaluatesTo.read(reader) as EvaluatesTo<DomainId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindTriggersByDomainId) = try {
            EvaluatesTo.write(writer, instance.domainId)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}