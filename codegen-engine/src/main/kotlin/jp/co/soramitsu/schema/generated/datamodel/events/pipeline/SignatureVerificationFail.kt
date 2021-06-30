// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.crypto.Signature
import kotlin.String
import kotlin.Unit

/**
 * SignatureVerificationFail
 *
 * Generated from 'iroha_data_model::events::pipeline::SignatureVerificationFail' regular structure
 */
public class SignatureVerificationFail(
  private val signature: Signature,
  private val reason: String
) : ScaleReader<SignatureVerificationFail>, ScaleWriter<SignatureVerificationFail> {
  public override fun read(reader: ScaleCodecReader): SignatureVerificationFail =
      SignatureVerificationFail(signature.read(reader), reason.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: SignatureVerificationFail): Unit {
    signature.write(writer, instance.signature),
    reason.write(writer, instance.reason)
  }
}
