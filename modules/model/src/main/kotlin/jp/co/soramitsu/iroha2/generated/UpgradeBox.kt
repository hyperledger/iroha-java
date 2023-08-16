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
 * UpgradeBox
 *
 * Generated from 'UpgradeBox' regular structure
 */
public data class UpgradeBox(
    public val `object`: EvaluatesTo<UpgradableBox>,
) {
    public companion object : ScaleReader<UpgradeBox>, ScaleWriter<UpgradeBox> {
        override fun read(reader: ScaleCodecReader): UpgradeBox = try {
            UpgradeBox(
                EvaluatesTo.read(reader) as EvaluatesTo<UpgradableBox>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: UpgradeBox): Unit = try {
            EvaluatesTo.write(writer, instance.`object`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
