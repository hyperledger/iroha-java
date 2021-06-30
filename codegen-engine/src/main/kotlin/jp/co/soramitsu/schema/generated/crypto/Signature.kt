// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Any
import kotlin.Unit

/**
 * Signature
 *
 * Generated from 'iroha_crypto::Signature' regular structure
 */
public class Signature(
  private val publicKey: PublicKey,
  private val signature: Any
) : ScaleReader<Signature>, ScaleWriter<Signature> {
  public override fun read(reader: ScaleCodecReader): Signature = Signature(publicKey.read(reader),
      signature.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: Signature): Unit {
    publicKey.write(writer, instance.publicKey),
    signature.write(writer, instance.signature)
  }
}
