package jp.co.soramitsu.iroha2.codec.writer

import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter

class ListWriter<T>(private val scaleWriter: ScaleWriter<T>) : ScaleWriter<List<T>> {
    override fun write(wrt: ScaleCodecWriter, value: List<T>) {
        wrt.writeCompact(value.size)
        for (item in value) {
            scaleWriter.write(wrt, item)
        }
    }
}
