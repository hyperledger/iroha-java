//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Boolean
import kotlin.Unit

/**
 * SignatureCheckCondition
 *
 * Generated from 'iroha_data_model::account::SignatureCheckCondition' tuple structure
 */
public class SignatureCheckCondition(
  public val evaluatesTo: EvaluatesTo<Boolean>
) {
  public companion object : ScaleReader<SignatureCheckCondition>,
      ScaleWriter<SignatureCheckCondition> {
    public override fun read(reader: ScaleCodecReader): SignatureCheckCondition =
        SignatureCheckCondition(Boolean.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition): Unit {
      Boolean.write(writer, instance.evaluatesTo)
    }
  }
}
