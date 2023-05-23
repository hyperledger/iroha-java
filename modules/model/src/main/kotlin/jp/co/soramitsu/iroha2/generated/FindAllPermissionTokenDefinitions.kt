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
 * FindAllPermissionTokenDefinitions
 *
 * Generated from 'FindAllPermissionTokenDefinitions' regular structure
 */
public class FindAllPermissionTokenDefinitions {
    public companion object :
        ScaleReader<FindAllPermissionTokenDefinitions>,
        ScaleWriter<FindAllPermissionTokenDefinitions> {
        public override fun read(reader: ScaleCodecReader): FindAllPermissionTokenDefinitions = try {
            FindAllPermissionTokenDefinitions()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindAllPermissionTokenDefinitions) =
            try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

        public fun equals(o1: FindAllPermissionTokenDefinitions, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        public override fun hashCode(): Int = ".FindAllPermissionTokenDefinitions".hashCode()
    }
}
