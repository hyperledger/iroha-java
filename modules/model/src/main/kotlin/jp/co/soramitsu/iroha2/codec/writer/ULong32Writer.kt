package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class ULong32Writer : ScaleWriter<Long?> {
    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: Long) {
        require(value >= 0) { "Negative values are not supported: $value" }
        require(value <= 0xffffffffL) { "Value is too high: $value" }
        wrt.directWrite((value and 0xff).toInt())
        wrt.directWrite((value shr 8 and 0xff).toInt())
        wrt.directWrite((value shr 16 and 0xff).toInt())
        wrt.directWrite((value shr 24 and 0xff).toInt())
    }
}