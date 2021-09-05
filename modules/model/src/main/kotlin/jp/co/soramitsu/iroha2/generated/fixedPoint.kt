//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UInt

/**
 * fixedPoint
 *
 * Generated from 'fixedPoint' regular structure
 */
public data class fixedPoint(
    public val `inner`: UInt
) {
    public companion object : ScaleReader<fixedPoint>, ScaleWriter<fixedPoint> {
        public override fun read(reader: ScaleCodecReader): fixedPoint = fixedPoint(
            reader.readUint32().toUInt(),
        )

        public override fun write(writer: ScaleCodecWriter, instance: fixedPoint) {
            writer.writeUint32(instance.`inner`.toInt())
        }
    }
}
