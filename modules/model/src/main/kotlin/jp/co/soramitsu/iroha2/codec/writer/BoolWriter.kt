package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class BoolWriter : ScaleWriter<Boolean?> {
    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: Boolean) {
        if (value) {
            wrt.directWrite(1)
        } else {
            wrt.directWrite(0)
        }
    }
}