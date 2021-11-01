package jp.co.soramitsu.iroha2.codec.reader

import java.nio.ByteBuffer
import java.nio.ByteOrder
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

/**
 * Read Java Integer encoded as 4 byte SCALE value. Please note that since Java Integer is signed type, it may
 * read negative values for some of the byte representations (i.e. when highest bit is set to 1). If you expect
 * to read positive numbers for all of the possible range, you should use Uint32Reader, which returns Long values.
 *
 * @see UInt32Reader
 */
class Int32Reader : ScaleReader<Int?> {
    override fun read(rdr: ScaleCodecReader): Int {
        val buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
        buf.put(rdr.readByte())
        buf.put(rdr.readByte())
        buf.put(rdr.readByte())
        buf.put(rdr.readByte())
        return buf.flip().int
    }
}