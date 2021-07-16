//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Array
import kotlin.UByte
import kotlin.Unit

/**
 * Hash
 *
 * Generated from 'iroha_crypto::Hash' tuple structure
 */
public class Hash(
  public val array: Array<UByte>
) {
  public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
    public override fun read(reader: ScaleCodecReader): Hash = Hash(UByte.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Hash): Unit {
      UByte.write(writer, instance.array)
    }
  }
}
