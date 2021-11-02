package jp.co.soramitsu.iroha2.codec.writer

import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import java.math.BigInteger

class UInt64Writer : ScaleWriter<BigInteger> {
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        require(value >= BigInteger.ZERO) { "Negative values are not supported: $value" }
        require(value <= MAX_UINT64) { "Value is to big for 64 bits. $value" }
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
