package jp.co.soramitsu.schema.scale.utils

import io.emeraldpay.polkaj.scale.ScaleCodecWriter

fun ScaleCodecWriter.directWrite(byteArray: ByteArray) {
    directWrite(byteArray, 0, byteArray.size)
}
