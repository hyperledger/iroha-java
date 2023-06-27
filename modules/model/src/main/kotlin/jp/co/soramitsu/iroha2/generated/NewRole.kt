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
 * NewRole
 *
 * Generated from 'NewRole' regular structure
 */
public data class NewRole(
    public val `inner`: Role
) {
    public companion object : ScaleReader<NewRole>, ScaleWriter<NewRole> {
        public override fun read(reader: ScaleCodecReader): NewRole = try {
            NewRole(
                Role.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: NewRole) = try {
            Role.write(writer, instance.`inner`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
