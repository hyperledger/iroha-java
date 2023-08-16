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
 * FindAllAssets
 *
 * Generated from 'FindAllAssets' regular structure
 */
public class FindAllAssets {
    public companion object : ScaleReader<FindAllAssets>, ScaleWriter<FindAllAssets> {
        override fun read(reader: ScaleCodecReader): FindAllAssets = try {
            FindAllAssets()
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: FindAllAssets): Unit = try {
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public fun equals(o1: FindAllAssets, o2: Any?): Boolean = when (o2) {
            null -> false
            else -> o2::class == o1::class
        }

        override fun hashCode(): Int = ".FindAllAssets".hashCode()
    }
}
