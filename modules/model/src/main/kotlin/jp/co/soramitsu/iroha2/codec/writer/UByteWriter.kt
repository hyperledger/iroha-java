package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class UByteWriter : ScaleWriter<Int?> {
    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: Int) {
        require(!(value < 0 || value > 0xff)) { "Only values in range 0..255 are supported: $value" }
        wrt.directWrite(value)
    }
}