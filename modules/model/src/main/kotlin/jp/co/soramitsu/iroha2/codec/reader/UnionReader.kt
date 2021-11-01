package jp.co.soramitsu.iroha2.codec.reader

import java.util.*
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.UnionValue

class UnionReader<T>(private val mapping: List<ScaleReader<out T>>) : ScaleReader<UnionValue<T>?> {
    constructor(vararg mapping: ScaleReader<out T>) : this(Arrays.asList<ScaleReader<out T>>(*mapping)) {}

    override fun read(rdr: ScaleCodecReader): UnionValue<T> {
        val index = rdr.readUByte()
        check(mapping.size > index) { "Unknown type index: $index" }
        val value = mapping[index].read(rdr) as T
        return UnionValue(index, value)
    }
}