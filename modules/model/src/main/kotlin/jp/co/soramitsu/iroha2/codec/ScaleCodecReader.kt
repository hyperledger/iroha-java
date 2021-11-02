package jp.co.soramitsu.iroha2.codec

import jp.co.soramitsu.iroha2.codec.reader.BoolOptionalReader
import jp.co.soramitsu.iroha2.codec.reader.BoolReader
import jp.co.soramitsu.iroha2.codec.reader.CompactBigIntReader
import jp.co.soramitsu.iroha2.codec.reader.CompactUIntReader
import jp.co.soramitsu.iroha2.codec.reader.Int32Reader
import jp.co.soramitsu.iroha2.codec.reader.StringReader
import jp.co.soramitsu.iroha2.codec.reader.UByteReader
import jp.co.soramitsu.iroha2.codec.reader.UInt128Reader
import jp.co.soramitsu.iroha2.codec.reader.UInt16Reader
import jp.co.soramitsu.iroha2.codec.reader.UInt32Reader
import java.math.BigInteger

/**
 * SCALE codec reader
 */
class ScaleCodecReader(private val source: ByteArray) {
    private var pos = 0

    /**
     * @return true if has more elements
     */
    operator fun hasNext(): Boolean {
        return pos < source.size
    }

    /**
     * Move reader position forward (or backward for negative value)
     *
     * @param len amount to bytes to skip
     */
    fun skip(len: Int) {
        require(!(len < 0 && Math.abs(len) > pos)) { "Position cannot be negative: $pos $len" }
        pos += len
    }

    /**
     * Specify a new position
     *
     * @param pos position
     */
    fun seek(pos: Int) {
        require(pos >= 0) { "Position cannot be negative: $pos" }
        require(pos < source.size) { "Position " + pos + " must be strictly smaller than source length: " + source.size }
        this.pos = pos
    }

    /**
     * @return a next single byte from reader
     */
    fun readByte(): Byte {
        if (!hasNext()) {
            throw IndexOutOfBoundsException("Cannot read " + pos + " of " + source.size)
        }
        return source[pos++]
    }

    fun <T> read(scaleReader: ScaleReader<T>?): T {
        // TODO: try scaleReader param non nullable (?)
        if (scaleReader == null) {
            throw NullPointerException("ItemReader cannot be null")
        }
        return scaleReader.read(this)
    }

    fun <T> readNullable(scaleReader: ScaleReader<T>?): T? {
        if (scaleReader is BoolReader || scaleReader is BoolOptionalReader) {
            return BOOL_OPTIONAL.read(this) as T?
        }
        return when (readBoolean()) {
            true -> read(scaleReader)
            else -> null
        }
    }

    fun readUByte(): Int {
        return UBYTE.read(this)
    }

    fun readUint16(): Int {
        return UINT16.read(this)
    }

    fun readUint32(): Long {
        return UINT32.read(this)
    }

    fun readUint128(): BigInteger {
        return UINT128.read(this)
    }

    fun readCompactInt(): Int {
        return COMPACT_UINT.read(this)
    }

    fun readBoolean(): Boolean {
        return BOOL.read(this)
    }

//    /**
//     * Read optional value from the reader
//     *
//     * @param scaleReader reader implementation
//     * @param <T>         resulting type
//     * @return optional read value
//     </T> */
//    fun <T> readOptional(scaleReader: ScaleReader<T>): T? {
//        if (scaleReader is BoolReader || scaleReader is BoolOptionalReader) {
//            return BOOL_OPTIONAL.read(this) as T?
//        }
//        return when (readBoolean()) {
//            true -> read(scaleReader)
//            else -> null
//        }
//    }

    fun readUint256(): ByteArray {
        return readByteArray(32)
    }

    fun readByteArray(): ByteArray {
        val len = readCompactInt()
        return readByteArray(len)
    }

    fun readByteArray(len: Int): ByteArray {
        val result = ByteArray(len)
        System.arraycopy(source, pos, result, 0, result.size)
        pos += len
        return result
    }

    /**
     * Read string, encoded as UTF-8 bytes
     *
     * @return string value
     */
    fun readString(): String {
        return String(readByteArray())
    }

    companion object {
        val UBYTE = UByteReader()
        val UINT16 = UInt16Reader()
        val UINT32 = UInt32Reader()
        val UINT128 = UInt128Reader()
        val INT32 = Int32Reader()
        val COMPACT_UINT = CompactUIntReader()
        val COMPACT_BIGINT = CompactBigIntReader()
        val BOOL = BoolReader()
        val BOOL_OPTIONAL = BoolOptionalReader()
        val STRING = StringReader()
    }
}
