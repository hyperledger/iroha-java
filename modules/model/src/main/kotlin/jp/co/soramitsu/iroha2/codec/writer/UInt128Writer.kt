package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import java.math.BigInteger
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.codec.reader.UInt128Reader

class UInt128Writer : ScaleWriter<BigInteger?> {
    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        require(value.signum() >= 0) { "Negative numbers are not supported by Uint128" }
        val array = value.toByteArray()
        var pos = 0
        // sometimes BigInteger gives an extra zero byte in the start of the array
        if (array[0] == 0) {
            pos++
        }
        val len = array.size - pos
        require(len <= UInt128Reader.SIZE_BYTES) { "Value is to big for 128 bits. Has: " + len * 8 + " bits" }
        val encoded = ByteArray(UInt128Reader.SIZE_BYTES)
        System.arraycopy(array, pos, encoded, encoded.size - len, len)
        UInt128Reader.reverse(encoded)
        wrt.directWrite(encoded, 0, UInt128Reader.SIZE_BYTES)
    }
}