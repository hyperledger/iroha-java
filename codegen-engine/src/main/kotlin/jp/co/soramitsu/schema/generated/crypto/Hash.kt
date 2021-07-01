// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.ByteArray
import kotlin.Unit

/**
 * Hash
 *
 * Generated from 'iroha_crypto::Hash' tuple structure
 */
public class Hash(
  private val array: ByteArray
) {
  public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
    public override fun read(reader: ScaleCodecReader): Hash = Hash(reader.readByteArray())

    public override fun write(writer: ScaleCodecWriter, instance: Hash): Unit {
      writer.writeByteArray(instance.`array`)
    }
  }
}
