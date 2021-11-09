package jp.co.soramitsu.iroha2.codec

/**
 * SCALE codec reader for a complex data type
 * @param <T> type
</T> */
interface ScaleReader<T> {
    /**
     * Reads value from specified reader. The reader must be positioned on the beginning of the value
     *
     * @param rdr reader with the encoded data
     * @return read value
     */
    fun read(rdr: ScaleCodecReader): T

    fun decode(data: ByteArray) = ScaleCodecReader(data).read(this)
}
