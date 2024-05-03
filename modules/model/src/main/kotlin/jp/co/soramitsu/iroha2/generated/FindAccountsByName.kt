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
 * FindAccountsByName
 *
 * Generated from 'FindAccountsByName' regular structure
 */
public data class FindAccountsByName(
    public val name: Name,
) {
    public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
        override fun read(reader: ScaleCodecReader): FindAccountsByName = try {
            FindAccountsByName(
                Name.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName): Unit = try {
            Name.write(writer, instance.name)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
