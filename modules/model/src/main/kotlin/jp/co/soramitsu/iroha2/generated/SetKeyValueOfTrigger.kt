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
 * SetKeyValueOfTrigger
 *
 * Generated from 'SetKeyValueOfTrigger' regular structure
 */
public data class SetKeyValueOfTrigger(
    public val objectId: TriggerId,
    public val key: Name,
    public val `value`: MetadataValueBox,
) {
    public companion object : ScaleReader<SetKeyValueOfTrigger>, ScaleWriter<SetKeyValueOfTrigger> {
        override fun read(reader: ScaleCodecReader): SetKeyValueOfTrigger = try {
            SetKeyValueOfTrigger(
                TriggerId.read(reader),
                Name.read(reader),
                MetadataValueBox.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SetKeyValueOfTrigger): Unit = try {
            TriggerId.write(writer, instance.objectId)
            Name.write(writer, instance.key)
            MetadataValueBox.write(writer, instance.`value`)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
