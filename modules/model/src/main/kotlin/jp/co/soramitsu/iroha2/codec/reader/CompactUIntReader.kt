package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.CompactMode
import jp.co.soramitsu.iroha2.codec.CompactMode.Companion.byValue
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class CompactUIntReader : ScaleReader<Int?> {
    /**
     * @param rdr reader with the encoded data
     * @return integer value
     * @throws UnsupportedOperationException if the value is encoded with more than four bytes (use [CompactBigIntReader])
     */
    override fun read(rdr: ScaleCodecReader): Int {
        val i = rdr.readUByte()
        val mode = byValue((i and 3).toByte())
        if (mode === CompactMode.SINGLE) {
            return i shr 2
        }
        if (mode === CompactMode.TWO) {
            return ((i shr 2)
                + (rdr.readUByte() shl 6))
        }
        if (mode === CompactMode.FOUR) {
            return (i shr 2) +
                (rdr.readUByte() shl 6) +
                (rdr.readUByte() shl 6 + 8) +
                (rdr.readUByte() shl 6 + 2 * 8)
        }
        throw UnsupportedOperationException("Mode $mode is not implemented")
    }
}