package jp.co.soramitsu.iroha2.scale

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

object UByteReader : ScaleReader<UByte> {
    override fun read(reader: ScaleCodecReader): UByte = reader.readUByte().toUByte()
}
