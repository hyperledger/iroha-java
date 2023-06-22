//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * FindAllRoleIds
 *
 * Generated from 'FindAllRoleIds' regular structure
 */
public class FindAllRoleIds {
    public companion object : ScaleReader<FindAllRoleIds>, ScaleWriter<FindAllRoleIds> {
        public override fun read(reader: ScaleCodecReader): FindAllRoleIds = try {
            FindAllRoleIds()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllRoleIds) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllRoleIds, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = ".FindAllRoleIds".hashCode()
    }
}
