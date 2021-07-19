//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

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
  public val array: ByteArray
) {
  public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
    public override fun read(reader: ScaleCodecReader): Hash =
        Hash(jp.co.soramitsu.iroha2.scale.ByteArrayReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Hash): Unit {
      jp.co.soramitsu.iroha2.scale.ByteArrayWriter.write(writer, instance.array)
    }
  }
}
