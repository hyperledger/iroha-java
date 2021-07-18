//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.UInt
import kotlin.Unit

/**
 * SignedQueryRequest
 *
 * Generated from 'iroha_data_model::query::SignedQueryRequest' regular structure
 */
public class SignedQueryRequest(
  public val timestampMs: UInt<BigInteger>,
  public val signature: Signature,
  public val query: QueryBox
) {
  public companion object : ScaleReader<SignedQueryRequest>, ScaleWriter<SignedQueryRequest> {
    public override fun read(reader: ScaleCodecReader): SignedQueryRequest =
        SignedQueryRequest(jp.co.soramitsu.iroha2.scale.UByteReader(jp.co.soramitsu.iroha2.scale.UInt128Reader).read(reader),
    Signature.read(reader),
    QueryBox.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: SignedQueryRequest): Unit {
      writer.writeCompact(instance.timestampMs)
      Signature.write(writer, instance.signature)
      QueryBox.write(writer, instance.query)
    }
  }
}
