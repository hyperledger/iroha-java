package jp.co.soramitsu.schema.codegen

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.reader.ListReader
import java.util.AbstractMap.SimpleEntry
import java.util.stream.Collectors

class MapReader<K, V>(keyReader: ScaleReader<K>, valueReader: ScaleReader<V>) :
    ScaleReader<Map<K, V>> {

    private val listReader: ListReader<Map.Entry<K, V>> =
        ListReader(EntryReader(keyReader, valueReader))

    constructor(companion: String.Companion, valueReader: ScaleReader<V>) : this(ScaleReader<String> { p0 -> p0.readString() } as ScaleReader<K>, valueReader)

    override fun read(reader: ScaleCodecReader): Map<K, V> {
        return reader.read(listReader).stream()
            .collect(Collectors.toMap({ (key) -> key }) { (_, value) -> value })
    }

    private class EntryReader<K, V>(
        private val keyReader: ScaleReader<K>,
        private val valueReader: ScaleReader<V>
    ) : ScaleReader<Map.Entry<K, V>> {
        override fun read(reader: ScaleCodecReader): Map.Entry<K, V> {
            return SimpleEntry(reader.read(keyReader), reader.read(valueReader))
        }
    }

}
