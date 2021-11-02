package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader
import java.math.BigInteger

class UInt128Reader : ScaleReader<BigInteger> {
    override fun read(rdr: ScaleCodecReader): BigInteger {
        val value = rdr.readByteArray(SIZE_BYTES)
        reverse(value)
        return BigInteger(1, value)
    }

    companion object {
        const val SIZE_BYTES = 16
        fun reverse(value: ByteArray) {
            for (i in 0 until value.size / 2) {
                val other = value.size - i - 1
                val tmp = value[other]
                value[other] = value[i]
                value[i] = tmp
            }
        }
    }
}
