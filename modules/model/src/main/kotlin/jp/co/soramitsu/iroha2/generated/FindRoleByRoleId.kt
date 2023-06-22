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
 * FindRoleByRoleId
 *
 * Generated from 'FindRoleByRoleId' regular structure
 */
public data class FindRoleByRoleId(
    public val id: EvaluatesTo<RoleId>
) {
    public companion object : ScaleReader<FindRoleByRoleId>, ScaleWriter<FindRoleByRoleId> {
        public override fun read(reader: ScaleCodecReader): FindRoleByRoleId = try {
            FindRoleByRoleId(
                EvaluatesTo.read(reader) as EvaluatesTo<RoleId>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindRoleByRoleId) = try {
            EvaluatesTo.write(writer, instance.id)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
