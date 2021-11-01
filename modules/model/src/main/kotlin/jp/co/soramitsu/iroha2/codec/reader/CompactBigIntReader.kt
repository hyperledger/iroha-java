package jp.co.soramitsu.iroha2.codec.reader

import java.math.BigInteger
import jp.co.soramitsu.iroha2.codec.CompactMode
import jp.co.soramitsu.iroha2.codec.CompactMode.Companion.byValue
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class CompactBigIntReader : ScaleReader<BigInteger?> {
    override fun read(rdr: ScaleCodecReader): BigInteger {
        val type = rdr.readUByte()
        val mode = byValue((type and 3).toByte())
        if (mode !== CompactMode.BIGINT) {
            rdr.skip(-1)
            val value = intReader.read(rdr)
            return BigInteger.valueOf(value.toLong())
        }
        val len = (type shr 2) + 4
        val value = rdr.readByteArray(len)
        //LE encoded, so need to reverse it
        for (i in 0 until value.size / 2) {
            val temp = value[i]
            value[i] = value[value.size - i - 1]
            value[value.size - i - 1] = temp
        }
        //unsigned, i.e. always positive, signum=1
        return BigInteger(1, value)
    }

    companion object {
        private val intReader = CompactUIntReader()
    }
}