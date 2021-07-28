//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UByte
import kotlin.Unit
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
      PublicKey.read(reader),
      MutableList(reader.readCompactInt()) {reader.readByte().toUByte()},
    )

    public override fun write(writer: ScaleCodecWriter, instance: Signature): Unit {

        writer.writeCompact(instance.signature.size)
        repeat(instance.signature.size) { writer.writeByte(instance.it.toByte()) }
    }
  }
}
