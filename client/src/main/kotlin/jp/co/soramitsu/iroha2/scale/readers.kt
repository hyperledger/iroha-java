package jp.co.soramitsu.iroha2.scale

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.reader.BoolReader
import io.emeraldpay.polkaj.scale.reader.CompactUIntReader
import io.emeraldpay.polkaj.scale.reader.StringReader
import java.math.BigInteger

//todo impplement all numbers and types
object U8Reader : ScaleReader<UByte> {
    override fun read(reader: ScaleCodecReader) = reader.readUByte().toUByte()
}

object U16Reader : ScaleReader<UShort> {
    override fun read(reader: ScaleCodecReader) = reader.readUint16().toUShort()
}

object U32Reader : ScaleReader<UInt> {
    override fun read(reader: ScaleCodecReader) = reader.readUint32().toUInt()
}

//todo possibly incorrect
object U64Reader : ScaleReader<ULong> {
    override fun read(reader: ScaleCodecReader) = reader.readUint128().toLong().toULong()
}

object U128Reader : ScaleReader<BigInteger> {
    override fun read(reader: ScaleCodecReader): BigInteger = reader.readUint128()
}

object U256Reader : ScaleReader<BigInteger> {
    override fun read(reader: ScaleCodecReader): BigInteger = BigInteger(reader.readUint256())
}

object Compact32Reader : CompactUIntReader()

object StringReader : StringReader()

object BoolReader : BoolReader()

object ByteArrayReader : ScaleReader<ByteArray> {
    override fun read(reader: ScaleCodecReader): ByteArray = reader.readByteArray()
}

class SetReader<T>(private val innerValueReader: ScaleReader<T>) : ScaleReader<MutableSet<T>> {
    override fun read(reader: ScaleCodecReader): MutableSet<T> {
        val size = reader.readCompactInt()
        val result: MutableSet<T> = HashSet()
        for (i in 0 until size) {
            result.add(reader.read(this.innerValueReader))
        }
        return result
    }
}

class OptionReader<T>(private val innerValueReader: ScaleReader<T>) : ScaleReader<T?> {
    override fun read(reader: ScaleCodecReader): T? = reader.readOptional(innerValueReader).orElse(null)
}

class MapReader<K, V>(private val keyReader: ScaleReader<K>, private val valueReader: ScaleReader<V>) :
    ScaleReader<MutableMap<K, V>> {

    override fun read(reader: ScaleCodecReader): MutableMap<K, V> {
        val size = reader.readCompactInt()
        val result = HashMap<K, V>(size)
        for (i in 0 until size) {
            result[reader.read(keyReader)] = reader.read(valueReader)
        }
        return result
    }

}
