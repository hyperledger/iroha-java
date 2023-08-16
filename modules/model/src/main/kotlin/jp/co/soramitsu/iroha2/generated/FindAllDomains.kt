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
import kotlin.Unit

/**
 * FindAllDomains
 *
 * Generated from 'FindAllDomains' regular structure
 */
public class FindAllDomains {
    public companion object : ScaleReader<FindAllDomains>, ScaleWriter<FindAllDomains> {
        override fun read(reader: ScaleCodecReader): FindAllDomains = try {
            FindAllDomains()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAllDomains): Unit = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllDomains, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        override fun hashCode(): Int = ".FindAllDomains".hashCode()
    }
}
