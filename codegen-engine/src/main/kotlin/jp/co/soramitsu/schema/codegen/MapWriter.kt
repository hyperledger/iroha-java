package jp.co.soramitsu.schema.codegen

import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.writer.ListWriter
import java.io.IOException

class MapWriter<K, V>(keyWriter: ScaleWriter<K>, valueWriter: ScaleWriter<V>) :
    ScaleWriter<Map<K, V>> {
    private val listWriter: ListWriter<Map.Entry<K, V>> =
        ListWriter(EntryWriter(keyWriter, valueWriter))

    constructor(companion: String.Companion, valueWriter: ScaleWriter<V>) : this(ScaleWriter<String>  { writer,instance -> writer.writeString(instance)} as ScaleWriter<K>, valueWriter)

    @Throws(IOException::class)
    override fun write(writer: ScaleCodecWriter, value: Map<K, V>) {
        listWriter.write(writer, ArrayList(value.entries))
    }

    private class EntryWriter<K, V>(
        private val keyWriter: ScaleWriter<K>,
        private val valueWriter: ScaleWriter<V>
    ) : ScaleWriter<Map.Entry<K, V>> {
        @Throws(IOException::class)
        override fun write(writer: ScaleCodecWriter, value: Map.Entry<K, V>) {
            keyWriter.write(writer, value.key)
            valueWriter.write(writer, value.value)
        }
    }

}
