package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class BoolReader : ScaleReader<Boolean?> {
    override fun read(rdr: ScaleCodecReader): Boolean {
        val b = rdr.readByte()
        if (b.toInt() == 0) {
            return false
        }
        if (b.toInt() == 1) {
            return true
        }
        throw IllegalStateException("Not a boolean value: $b")
    }
}