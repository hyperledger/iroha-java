package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

/**
 * Read string, encoded as UTF-8 bytes
 */
class StringReader : ScaleReader<String> {
    override fun read(rdr: ScaleCodecReader): String {
        return rdr.readString()
    }
}
