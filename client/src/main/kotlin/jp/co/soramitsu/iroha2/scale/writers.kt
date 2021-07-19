package jp.co.soramitsu.iroha2.scale

import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.writer.BoolWriter
import java.math.BigInteger
import java.util.Optional.ofNullable

object StringWriter : ScaleWriter<String> {
    override fun write(writer: ScaleCodecWriter, instance: String) =
        writer.writeAsList(instance.toByteArray(Charsets.UTF_8))
}

object ByteArrayWriter : ScaleWriter<ByteArray> {
    override fun write(writer: ScaleCodecWriter, instance: ByteArray) = writer.writeByteArray(instance)
}

class SetWriter<T>(private val innerValueWriter: ScaleWriter<T>) : ScaleWriter<Set<T>> {
    override fun write(writer: ScaleCodecWriter, instance:Set<T>) {
        writer.writeCompact(instance.size)
        instance.forEach { writer.write(innerValueWriter, it)}
    }
}

object U8Writer : ScaleWriter<UByte> {
    override fun write(writer: ScaleCodecWriter, instance:UByte) = writer.writeByte(instance.toByte())
}

object U16Writer : ScaleWriter<UShort> {
    override fun write(writer: ScaleCodecWriter, instance:UShort) = writer.writeUint16(instance.toInt())
}

object U32Writer : ScaleWriter<UInt> {
    override fun write(writer: ScaleCodecWriter, instance:UInt) = writer.writeUint32(instance.toInt())
}

//todo possibly incorrect
object U64Writer : ScaleWriter<ULong> {
    override fun write(writer: ScaleCodecWriter, instance:ULong) = writer.writeUint128(BigInteger.valueOf(instance.toLong()))
}

object U128Writer : ScaleWriter<BigInteger> {
    override fun write(writer: ScaleCodecWriter, instance:BigInteger) = writer.writeUint128(instance)
}

object U256Writer : ScaleWriter<BigInteger> {
    override fun write(writer: ScaleCodecWriter, instance:BigInteger) = writer.writeUint256(instance.toByteArray())
}

object BoolWriter: BoolWriter()

class OptionWriter<T>(private val innerValueWriter: ScaleWriter<T>) : ScaleWriter<T?> {
    override fun write(writer: ScaleCodecWriter, instance: T?) = writer.writeOptional(innerValueWriter, ofNullable(instance))
}

class MapWriter<K, V>(private val keyWriter: ScaleWriter<K>, private val valueWriter: ScaleWriter<V>) :
    ScaleWriter<MutableMap<K, V>> {

    override fun write(writer: ScaleCodecWriter, instance: MutableMap<K, V>) {
        writer.writeCompact(instance.size)
        instance.forEach{ (key, value) ->
            writer.write(keyWriter, key)
            writer.write(valueWriter, value)
        }
    }

}
