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
    public override fun read(reader: ScaleCodecReader): Signature =
        Signature(PublicKey.read(reader),
    reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(UByte)))

    public override fun write(writer: ScaleCodecWriter, instance: Signature): Unit {
      PublicKey.write(writer, instance.publicKey)
      writer.write(io.emeraldpay.polkaj.scale.writer.ListWriter(UByte), instance.signature)
    }
  }
}
