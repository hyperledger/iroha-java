package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.CompactMode
import jp.co.soramitsu.iroha2.codec.CompactMode.Companion.byValue
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.and
import jp.co.soramitsu.iroha2.codec.shl
import jp.co.soramitsu.iroha2.codec.shr

class CompactUIntReader : ScaleReader<Int> {
    /**
     * @param reader reader with the encoded data
     * @return integer value
     * @throws UnsupportedOperationException if the value is encoded with more than four bytes (use [CompactBigIntReader])
     */
    override fun read(reader: ScaleCodecReader): Int {
        val i = reader.readUByte()
        val mode = byValue((i and 3).toByte())
        if (mode === CompactMode.SINGLE) {
            return i.toInt() shr 2
        }
        if (mode === CompactMode.TWO) {
            return ((i shr 2) + (reader.readUByte() shl 6))
        }
        if (mode === CompactMode.FOUR) {
            return (i shr 2) +
                (reader.readUByte() shl 6) +
                (reader.readUByte() shl 6 + 8) +
                (reader.readUByte() shl 6 + 2 * 8)
        }
        throw UnsupportedOperationException("Mode $mode is not implemented")
    }
}
