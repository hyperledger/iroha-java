package jp.co.soramitsu.iroha2.codec

/**
 * SCALE codec reader for a complex data type
 *
 * @param <T> type </T>
 */
interface ScaleReader<T> {
    /**
     * Read SCALE value from the specified reader. The reader must be positioned on the beginning of the value
     *
     * @param reader reader with the encoded data
     * @return read value
     */
    fun read(reader: ScaleCodecReader): T

    /**
     * Decode data encoded as a SCALE value
     */
    fun decode(data: ByteArray) = ScaleCodecReader(data).read(this)
}
