package jp.co.soramitsu.iroha2.codec.writer

import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class UInt32Writer : ScaleWriter<Int> {
    override fun write(wrt: ScaleCodecWriter, value: Int) {
        require(value >= 0) { "Negative values are not supported: $value" }
        wrt.directWrite(value and 0xff)
        wrt.directWrite(value shr 8 and 0xff)
        wrt.directWrite(value shr 16 and 0xff)
        wrt.directWrite(value shr 24 and 0xff)
    }
}
