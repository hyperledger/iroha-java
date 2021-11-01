package jp.co.soramitsu.iroha2.codec

interface ScaleWriter<T> {
    fun write(wrt: ScaleCodecWriter, value: T)
}
