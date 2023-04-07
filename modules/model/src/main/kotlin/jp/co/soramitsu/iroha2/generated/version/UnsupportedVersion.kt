//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.version

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Short

/**
 * UnsupportedVersion
 *
 * Generated from 'iroha_version::UnsupportedVersion' regular structure
 */
public data class UnsupportedVersion(
    public val version: Short,
    public val raw: RawVersioned
) {
    public companion object : ScaleReader<UnsupportedVersion>, ScaleWriter<UnsupportedVersion> {
        public override fun read(reader: ScaleCodecReader): UnsupportedVersion = try {
            UnsupportedVersion(
                reader.readUByte().toShort(),
                RawVersioned.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: UnsupportedVersion) = try {
            writer.writeUByte(instance.version)
            RawVersioned.write(writer, instance.raw)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
