package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class ListReader<T>(private val scaleReader: ScaleReader<T>) : ScaleReader<List<T>?> {
    override fun read(rdr: ScaleCodecReader): List<T> {
        val size = rdr.readCompactInt()
        val result: MutableList<T> = ArrayList(size)
        for (i in 0 until size) {
            result.add(rdr.read(scaleReader))
        }
        return result
    }
}