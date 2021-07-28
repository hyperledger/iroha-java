//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * GrantBox
 *
 * Generated from 'iroha_data_model::isi::GrantBox' regular structure
 */
public class GrantBox(
  public val `object`: EvaluatesTo<Value>,
  public val destinationId: EvaluatesTo<IdBox>
) {
  public companion object : ScaleReader<GrantBox>, ScaleWriter<GrantBox> {
    public override fun read(reader: ScaleCodecReader): GrantBox = GrantBox(
      EvaluatesTo<Value>.read(reader),
      EvaluatesTo<IdBox>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: GrantBox): Unit {


    }
  }
}
