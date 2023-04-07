//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.role

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * FindAllRoles
 *
 * Generated from 'iroha_data_model::query::role::FindAllRoles' tuple structure
 */
public class FindAllRoles {
    public companion object : ScaleReader<FindAllRoles>, ScaleWriter<FindAllRoles> {
        public override fun read(reader: ScaleCodecReader): FindAllRoles = try {
            FindAllRoles()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllRoles) = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllRoles, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = "datamodel.query.role.FindAllRoles".hashCode()
    }
}
