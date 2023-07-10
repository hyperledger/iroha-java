package jp.co.soramitsu.iroha2.codec

import java.nio.ByteBuffer

fun Long.toBytes(): ByteArray = ByteBuffer.allocate(Long.SIZE_BYTES).also { it.putLong(this) }.array()

infix fun Short.and(other: Short): Short = (this.toInt() and other.toInt()).toShort()

infix fun Short.shr(other: Short): Short = (this.toInt() shr other.toInt()).toShort()

infix fun Short.shl(other: Short): Short = (this.toInt() shl other.toInt()).toShort()
