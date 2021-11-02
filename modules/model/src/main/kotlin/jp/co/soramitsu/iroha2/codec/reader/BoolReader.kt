package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class BoolReader : ScaleReader<Boolean> {
    override fun read(rdr: ScaleCodecReader): Boolean {
        // TODO: через байты
        return when (val b = rdr.readCompactInt()) {
            0 -> false
            1 -> true
            else -> throw IllegalStateException("Not a boolean option: $b")
        }
    }
}
