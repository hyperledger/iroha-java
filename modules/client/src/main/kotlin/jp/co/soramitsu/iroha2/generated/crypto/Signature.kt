//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UByte
import kotlin.collections.MutableList

/**
 * Signature
 *
 * Generated from 'iroha_crypto::Signature' regular structure
 */
public class Signature(
    public val publicKey: PublicKey,
    public val signature: MutableList<UByte>
) {
    public companion object : ScaleReader<Signature>, ScaleWriter<Signature> {
        public override fun read(reader: ScaleCodecReader): Signature = Signature(
            PublicKey.read(reader) as PublicKey,
            MutableList(reader.readCompactInt()) { reader.readByte().toUByte() },
        )

        public override fun write(writer: ScaleCodecWriter, instance: Signature) {
            PublicKey.write(writer, instance.publicKey)
            writer.writeCompact(instance.signature.size)
            instance.signature.forEach { value -> writer.writeByte(value.toByte()) }
        }
    }
}
