package jp.co.soramitsu.iroha2.codec

import jp.co.soramitsu.iroha2.MAX_POWER_OF_TWO
import jp.co.soramitsu.iroha2.codec.reader.BoolOptionalReader
import jp.co.soramitsu.iroha2.codec.reader.BoolReader
import jp.co.soramitsu.iroha2.codec.reader.CompactBigIntReader
import jp.co.soramitsu.iroha2.codec.reader.CompactUIntReader
import jp.co.soramitsu.iroha2.codec.reader.Int32Reader
import jp.co.soramitsu.iroha2.codec.reader.Int64Reader
import jp.co.soramitsu.iroha2.codec.reader.IntReader
import jp.co.soramitsu.iroha2.codec.reader.StringReader
import jp.co.soramitsu.iroha2.codec.reader.UByteReader
import jp.co.soramitsu.iroha2.codec.reader.UInt16Reader
import jp.co.soramitsu.iroha2.codec.reader.UInt32Reader
import jp.co.soramitsu.iroha2.codec.reader.UIntReader
import java.math.BigInteger
import kotlin.math.abs

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
        require(!(len < 0 && abs(len) > pos)) { "Position cannot be negative: $pos $len" }
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

    fun readUint64(): BigInteger {
        return UINT64.read(this)
    }

    fun readUint128(): BigInteger {
        return UINT128.read(this)
    }

    fun readUint256(): BigInteger {
        return UINT256.read(this)
    }

    fun readInt32(): Int {
        return INT32.read(this)
    }

    fun readInt64(): Long {
        return INT64.read(this)
    }

    fun readInt128(): BigInteger {
        return INT128.read(this)
    }

    fun readInt256(): BigInteger {
        return INT256.read(this)
    }

    fun readCompactInt(): Int {
        return COMPACT_UINT.read(this)
    }

    fun readBoolean(): Boolean {
        return BOOL.read(this)
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

    fun <T> readSet(size: Int, supplier: () -> T): MutableSet<T> {
        val set = HashSet<T>(capacity(size))
        for (index in 0 until size) {
            set.add(supplier())
        }
        return set
    }

    fun <K, V> readMap(size: Int, key: () -> K, value: () -> V): MutableMap<K, V> {
        val map = HashMap<K, V>(capacity(size))
        for (index in 0 until size) {
            map[key()] = value()
        }
        return map
    }

    fun <T> readVec(size: Int, supplier: () -> T): List<T> {
        return List(size) { supplier() }
    }

    /**
     * Read string, encoded as UTF-8 bytes
     *
     * @return string value
     */
    fun readString(): String {
        return String(readByteArray())
    }

    private fun capacity(expectedSize: Int): Int {
        return when {
            expectedSize < 0 -> throw IllegalArgumentException("Expected size cannot be negative but was: $expectedSize")
            expectedSize < 3 -> expectedSize + 1
            expectedSize < MAX_POWER_OF_TWO -> (expectedSize.toFloat() / 0.75f + 1.0f).toInt()
            else -> Int.MAX_VALUE
        }
    }

    companion object {
        val UBYTE = UByteReader()
        val UINT16 = UInt16Reader()
        val UINT32 = UInt32Reader()
        val UINT64 = UIntReader(64)
        val UINT128 = UIntReader(128)
        val UINT256 = UIntReader(256)
        val INT32 = Int32Reader()
        val INT64 = Int64Reader()
        val INT128 = IntReader(128)
        val INT256 = IntReader(256)
        val COMPACT_UINT = CompactUIntReader()
        val COMPACT_BIGINT = CompactBigIntReader()
        val BOOL = BoolReader()
        val BOOL_OPTIONAL = BoolOptionalReader()
        val STRING = StringReader()
    }
}
