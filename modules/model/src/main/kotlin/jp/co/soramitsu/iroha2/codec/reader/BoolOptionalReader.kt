package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class BoolOptionalReader : ScaleReader<Boolean?> {
    override fun read(rdr: ScaleCodecReader): Boolean? {
        return when (val b = rdr.readByte().toInt()) {
            0 -> null
            1 -> false
            2 -> true
            else -> throw IllegalStateException("Not a boolean option: $b")
        }
    }
}
