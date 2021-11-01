package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import java.math.BigInteger
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class UInt64Writer : ScaleWriter<BigInteger?> {
    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        require(value.compareTo(BigInteger.ZERO) >= 0) { "Negative values are not supported: $value" }
        require(value.compareTo(MAX_UINT64) <= 0) { "Value is to big for 64 bits. $value" }
        wrt.directWrite(value.and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(8).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(16).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(24).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(32).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(40).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(48).and(BigInteger.valueOf(255)).toInt())
        wrt.directWrite(value.shiftRight(56).and(BigInteger.valueOf(255)).toInt())
    }

    companion object {
        val MAX_UINT64 = BigInteger("18446744073709551615")
    }
}