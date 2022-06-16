package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.CompactMode
import jp.co.soramitsu.iroha2.codec.CompactMode.Companion.byValue
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.and
import jp.co.soramitsu.iroha2.codec.shr
import java.math.BigInteger

/**
 * [Compact mode][CompactMode] SCALE reader for Big Integers
 */
class CompactBigIntReader : ScaleReader<BigInteger> {
    override fun read(reader: ScaleCodecReader): BigInteger {
        val type = reader.readUByte()
        val mode = byValue((type and 3).toByte())
        if (mode !== CompactMode.BIGINT) {
            reader.skip(-1)
            val value = intReader.read(reader)
            return BigInteger.valueOf(value.toLong())
        }
        val len = (type shr 2) + 4
        val value = reader.readByteArray(len)
        // LE encoded, so need to reverse it
        for (i in 0 until value.size / 2) {
            val temp = value[i]
            value[i] = value[value.size - i - 1]
            value[value.size - i - 1] = temp
        }
        // unsigned, i.e. always positive, signum=1
        return BigInteger(1, value)
    }

    companion object {
        private val intReader = CompactUIntReader()
    }
}
