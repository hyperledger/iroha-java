//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.query

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Short

/**
 * UnsupportedVersionError
 *
 * Generated from 'iroha_core::smartcontracts::isi::query::UnsupportedVersionError' regular
 * structure
 */
public data class UnsupportedVersionError(
    public val version: Short
) {
    public companion object :
        ScaleReader<UnsupportedVersionError>,
        ScaleWriter<UnsupportedVersionError> {
        public override fun read(reader: ScaleCodecReader): UnsupportedVersionError = try {
            UnsupportedVersionError(
                reader.readUByte().toShort(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: UnsupportedVersionError) = try {
            writer.writeUByte(instance.version)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
