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
 * MismatchOfAssetDefinitionId
 *
 * Generated from 'MismatchOfAssetDefinitionId' regular structure
 */
public data class MismatchOfAssetDefinitionId(
    public val expected: AssetDefinitionId,
    public val `actual`: AssetDefinitionId,
) {
    public companion object :
        ScaleReader<MismatchOfAssetDefinitionId>,
        ScaleWriter<MismatchOfAssetDefinitionId> {
        override fun read(reader: ScaleCodecReader): MismatchOfAssetDefinitionId = try {
            MismatchOfAssetDefinitionId(
                AssetDefinitionId.read(reader),
                AssetDefinitionId.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: MismatchOfAssetDefinitionId): Unit = try {
            AssetDefinitionId.write(writer, instance.expected)
            AssetDefinitionId.write(writer, instance.`actual`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
