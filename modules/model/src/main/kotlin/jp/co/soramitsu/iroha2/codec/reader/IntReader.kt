package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader
import java.math.BigInteger
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Read Java Integer encoded as 4 byte SCALE value. Please note that since Java Integer is signed type, it may
 * read negative values for some byte representations (i.e. when highest bit is set to 1). If you expect
 * to read positive numbers for all the possible range, you should use Uint32Reader, which returns Long values.
 *
 * @see UInt32Reader
 */
class Int32Reader : ScaleReader<Int> {
    override fun read(rdr: ScaleCodecReader): Int {
        val capacity = 4
        val buf = ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN)
        putBytes(buf, capacity, rdr)
        return buf.flip().int
    }
}

class Int64Reader : ScaleReader<Long> {
    override fun read(rdr: ScaleCodecReader): Long {
        val capacity = 8
        val buf = ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN)
        putBytes(buf, capacity, rdr)
        return buf.flip().long
    }
}

class IntReader(private val bit: Int) : ScaleReader<BigInteger> {
    override fun read(rdr: ScaleCodecReader): BigInteger {
        val capacity = bit / 8
        val buf = ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN)
        putBytes(buf, capacity, rdr)
        return BigInteger(buf.flip().array())
    }
}

private fun putBytes(buf: ByteBuffer, capacity: Int, rdr: ScaleCodecReader) {
    for (i in 1..capacity) {
        buf.put(rdr.readByte())
    }
}