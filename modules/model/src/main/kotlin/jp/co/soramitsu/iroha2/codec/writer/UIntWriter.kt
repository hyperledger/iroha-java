package jp.co.soramitsu.iroha2.codec.writer

import jp.co.soramitsu.iroha2.codec.IntMax
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import java.math.BigInteger

open class UIntWriter(private val bit: Int) : ScaleWriter<BigInteger> {
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        require(value >= BigInteger.ZERO) { "Negative values are not supported: $value" }
        require(value <= IntMax.uintMaxValue(bit)) { "Value is too big for U$bit: $value" }

        wrt.directWrite(value.and(BigInteger.valueOf(255)))
        for (n in 8..bit - 8 step 8) {
            wrt.directWrite(value.shiftRight(n).and(BigInteger.valueOf(255)))
        }
    }
}

class UInt16Writer : ScaleWriter<Int> {
    override fun write(wrt: ScaleCodecWriter, value: Int) {
        require(value >= 0) { "Negative values are not supported: $value" }
        wrt.directWrite(value and 0xff)
        wrt.directWrite(value shr 8 and 0xff)
    }
}

class UInt32Writer : ScaleWriter<Long> {
    override fun write(wrt: ScaleCodecWriter, value: Long) {
        require(value >= 0) { "Negative values are not supported: $value" }
        require(value <= 0xffffffffL) { "Value is too big for U32: $value" }
        wrt.directWrite(value and 0xff)
        wrt.directWrite(value shr 8 and 0xff)
        wrt.directWrite(value shr 16 and 0xff)
        wrt.directWrite(value shr 24 and 0xff)
    }
}
