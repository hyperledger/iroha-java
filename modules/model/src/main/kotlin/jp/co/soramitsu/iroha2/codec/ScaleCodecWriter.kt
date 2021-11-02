package jp.co.soramitsu.iroha2.codec

import jp.co.soramitsu.iroha2.codec.writer.BoolOptionalWriter
import jp.co.soramitsu.iroha2.codec.writer.BoolWriter
import jp.co.soramitsu.iroha2.codec.writer.CompactBigIntWriter
import jp.co.soramitsu.iroha2.codec.writer.CompactUIntWriter
import jp.co.soramitsu.iroha2.codec.writer.UInt128Writer
import jp.co.soramitsu.iroha2.codec.writer.UInt16Writer
import jp.co.soramitsu.iroha2.codec.writer.UInt32Writer
import jp.co.soramitsu.iroha2.codec.writer.ULong32Writer
import java.io.Closeable
import java.io.IOException
import java.io.OutputStream
import java.math.BigInteger

class ScaleCodecWriter(private val out: OutputStream) : Closeable {
    fun writeUint256(value: ByteArray) {
        require(value.size == 32) { "Value must be 32 byte array" }
        writeByteArray(value)
    }

    fun writeByteArray(value: ByteArray) {
        out.write(value, 0, value.size)
    }

    fun writeAsList(value: ByteArray) {
        writeCompact(value.size)
        out.write(value, 0, value.size)
    }

    /**
     * Write the byte into output stream as-is directly, the input is supposed to be already encoded
     *
     * @param b byte to write
     * @throws IOException if failed to write
     */
    fun directWrite(b: Int) {
        out.write(b)
    }

    /**
     * Write the bytes into output stream as-is directly, the input is supposed to be already encoded
     *
     * @param b bytes to write
     * @param off offset
     * @param len length
     * @throws IOException if failed to write
     */
    fun directWrite(b: ByteArray?, off: Int, len: Int) {
        out.write(b, off, len)
    }

    fun flush() {
        out.flush()
    }

    override fun close() {
        out.close()
    }

    fun <T> write(writer: ScaleWriter<T>, value: T) {
        writer.write(this, value)
    }

    fun <T> writeNullable(writer: ScaleWriter<T>, value: T?) {
        if (writer is BoolOptionalWriter || writer is BoolWriter) {
            BOOL_OPT.write(this, value as Boolean?)
        } else {
            if (value == null) {
                BOOL.write(this, false)
            } else {
                BOOL.write(this, true)
                writer.write(this, value)
            }
        }
    }

    fun writeByte(value: Int) {
        directWrite(value)
    }

    fun writeByte(value: Byte) {
        directWrite(value.toInt())
    }

    fun writeUint16(value: Int) {
        UINT16.write(this, value)
    }

    fun writeUint32(value: Int) {
        UINT32.write(this, value)
    }

    fun writeUint32(value: Long) {
        ULONG32.write(this, value)
    }

    fun writeUint128(value: BigInteger) {
        UINT128.write(this, value)
    }

    fun writeCompact(value: Int) {
        COMPACT_UINT.write(this, value)
    }

//    fun <T> writeOptional(writer: ScaleWriter<T>, value: T?) {
//        if (writer is BoolOptionalWriter || writer is BoolWriter) {
//            BOOL_OPT.write(this, value as Boolean?)
//        } else {
//            if (value == null) {
//                BOOL.write(this, false)
//            } else {
//                BOOL.write(this, true)
//                writer.write(this, value)
//            }
//        }
//    }

    companion object {
        val COMPACT_UINT = CompactUIntWriter()
        val COMPACT_BIGINT = CompactBigIntWriter()
        val UINT16 = UInt16Writer()
        val UINT32 = UInt32Writer()
        val UINT128 = UInt128Writer()
        val ULONG32 = ULong32Writer()
        val BOOL = BoolWriter()
        val BOOL_OPT = BoolOptionalWriter()
    }
}
