package jp.co.soramitsu.iroha2.codec.writer

import java.io.IOException
import java.util.*
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.codec.UnionValue

class UnionWriter<T>(mapping: List<ScaleWriter<out T>>) : ScaleWriter<UnionValue<T>?> {
    private val mapping: MutableList<ScaleWriter<T>>

    constructor(vararg mapping: ScaleWriter<out T>) : this(Arrays.asList<ScaleWriter<out T>>(*mapping)) {}

    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: UnionValue<T>) {
        wrt.directWrite(value.index)
        val actual = value.value
        mapping[value.index].write(wrt, actual)
    }

    init {
        this.mapping = ArrayList(mapping.size)
        for (t in mapping) {
            this.mapping.add(t as ScaleWriter<T>)
        }
    }
}