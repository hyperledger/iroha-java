//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.String
import kotlin.Unit

/**
 * SignatureVerificationFail
 *
 * Generated from 'iroha_data_model::events::pipeline::SignatureVerificationFail' regular structure
 */
public class SignatureVerificationFail(
  public val signature: Signature,
  public val reason: String
) {
  public companion object : ScaleReader<SignatureVerificationFail>,
      ScaleWriter<SignatureVerificationFail> {
    public override fun read(reader: ScaleCodecReader): SignatureVerificationFail =
        SignatureVerificationFail(Signature.read(reader),
    jp.co.soramitsu.iroha2.scale.StringReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: SignatureVerificationFail): Unit {
      Signature.write(writer, instance.signature)
      writer.writeAsList(instance.reason.encodeToByteArray())
    }
  }
}
