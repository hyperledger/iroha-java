package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class UByteReader : ScaleReader<Int> {
    override fun read(reader: ScaleCodecReader): Int {
        val x = reader.readByte()
        return if (x < 0) {
            256 + x.toInt()
        } else x.toInt()
    }
}
