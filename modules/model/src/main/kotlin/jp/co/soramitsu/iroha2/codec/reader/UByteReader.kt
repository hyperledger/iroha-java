package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class UByteReader : ScaleReader<Short> {
    override fun read(reader: ScaleCodecReader): Short {
        val x = reader.readByte()
        return if (x < 0) {
            (256 + x).toShort()
        } else x.toShort()
    }
}
