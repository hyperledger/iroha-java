package jp.co.soramitsu.iroha2.codec.writer

import jp.co.soramitsu.iroha2.codec.IntMax
import jp.co.soramitsu.iroha2.codec.IntMin
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import java.math.BigInteger

class Int32Writer : ScaleWriter<Int> {
    override fun write(wrt: ScaleCodecWriter, value: Int) {
        require(Int.MIN_VALUE <= value) { "Value is too small for I32: $value" }
        require(value <= Int.MAX_VALUE) { "Value is too big for I32: $value" }

        wrt.directWrite(value and 0xff)
        wrt.directWrite(value shr 8 and 0xff)
        wrt.directWrite(value shr 16 and 0xff)
        wrt.directWrite(value shr 24 and 0xff)
    }
}

class Int64Writer : ScaleWriter<Long> {
    override fun write(wrt: ScaleCodecWriter, value: Long) {
        require(Long.MIN_VALUE <= value) { "Value is too small for I64: $value" }
        require(value <= Long.MAX_VALUE) { "Value is too big for I64: $value" }

        wrt.directWrite(value and 0xff)
        wrt.directWrite(value shr 8 and 0xff)
        wrt.directWrite(value shr 16 and 0xff)
        wrt.directWrite(value shr 24 and 0xff)
        wrt.directWrite(value shr 32 and 0xff)
        wrt.directWrite(value shr 40 and 0xff)
        wrt.directWrite(value shr 48 and 0xff)
        wrt.directWrite(value shr 56 and 0xff)
    }
}

class IntWriter(private val bit: Int) : ScaleWriter<BigInteger> {
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        require(IntMin.intMinValue(bit) <= value) { "Value is too small for I$bit: $value" }
        require(value <= IntMax.intMaxValue(bit)) { "Value is too big for I$bit: $value" }

        wrt.directWrite(value.and(BigInteger.valueOf(255)))
        for (n in 8..bit - 8 step 8) {
            wrt.directWrite(value.shiftRight(n).and(BigInteger.valueOf(255)))
        }
    }
}
