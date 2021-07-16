//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * UnregisterBox
 *
 * Generated from 'iroha_data_model::isi::UnregisterBox' regular structure
 */
public class UnregisterBox(
  public val objectId: EvaluatesTo<IdBox>
) {
  public companion object : ScaleReader<UnregisterBox>, ScaleWriter<UnregisterBox> {
    public override fun read(reader: ScaleCodecReader): UnregisterBox =
        UnregisterBox(IdBox.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: UnregisterBox): Unit {
      IdBox.write(writer, instance.objectId)
    }
  }
}
