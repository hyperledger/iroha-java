package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class UInt16Reader : ScaleReader<Int> {
    override fun read(rdr: ScaleCodecReader): Int {
        var result = 0
        result += rdr.readUByte()
        result += rdr.readUByte() shl 8
        return result
    }
}
