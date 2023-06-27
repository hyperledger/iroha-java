//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * IpfsPath
 *
 * Generated from 'IpfsPath' regular structure
 */
public data class IpfsPath(
    public val string: String
) {
    public companion object : ScaleReader<IpfsPath>, ScaleWriter<IpfsPath> {
        public override fun read(reader: ScaleCodecReader): IpfsPath = try {
            IpfsPath(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: IpfsPath) = try {
            writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
