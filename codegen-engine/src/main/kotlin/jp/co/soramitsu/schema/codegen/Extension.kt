package jp.co.soramitsu.schema.codegen

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

//todo length troubles
fun Int.read() = ScaleReader<Int> { it.readLong() }
