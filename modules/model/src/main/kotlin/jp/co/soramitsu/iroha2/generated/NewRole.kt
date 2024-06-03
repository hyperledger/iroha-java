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
 * NewRole
 *
 * Generated from 'NewRole' regular structure
 */
public data class NewRole(
    public val `inner`: Role,
) {
    public companion object : ScaleReader<NewRole>, ScaleWriter<NewRole> {
        override fun read(reader: ScaleCodecReader): NewRole = try {
            NewRole(
                Role.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: NewRole): Unit = try {
            Role.write(writer, instance.`inner`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
