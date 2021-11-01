package jp.co.soramitsu.iroha2.codec.reader

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

/**
 * Read a Java enum value. The reader reads a one byte and returns a Enum value which Ordinal value is equal to it.
 * <br></br>
 * If you need to read an enumeration with assigned value, i.e. Rust style enum, you should use [UnionReader] instead.
 *
 * @param <T> type of Enum
 * @see UnionReader
</T> */
class EnumReader<T : Enum<*>?>(values: Array<T>?) : ScaleReader<T> {
    private val values: Array<T>
    override fun read(rdr: ScaleCodecReader): T {
        val id = rdr.readUByte()
        for (t in values) {
            if (t!!.ordinal == id) {
                return t
            }
        }
        throw IllegalStateException("Unknown enum value: $id")
    }

    /**
     * Define reader by specifying list of possible values. In most of the cases it would be:
     * `new EnumReader(MyEnum.values()`
     *
     * @param values list of enum values
     */
    init {
        if (values == null) {
            throw NullPointerException("List of enums is null")
        }
        require(values.size != 0) { "List of enums is empty" }
        this.values = values
    }
}