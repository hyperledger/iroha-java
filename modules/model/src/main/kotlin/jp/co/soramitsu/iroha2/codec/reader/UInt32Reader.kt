package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class UInt32Reader : ScaleReader<Long?> {
    override fun read(rdr: ScaleCodecReader): Long {
        var result: Long = 0
        result += rdr.readUByte().toLong()
        result += rdr.readUByte().toLong() shl 8
        result += rdr.readUByte().toLong() shl 2 * 8
        result += rdr.readUByte().toLong() shl 3 * 8
        return result
    }
}