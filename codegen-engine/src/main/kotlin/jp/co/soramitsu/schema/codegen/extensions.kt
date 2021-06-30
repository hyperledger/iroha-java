//package jp.co.soramitsu.schema.codegen
//
//import io.emeraldpay.polkaj.scale.ScaleCodecReader
//import io.emeraldpay.polkaj.scale.ScaleCodecWriter
//import jp.co.soramitsu.schema.generated.crypto.PublicKey
//
////todo length troubles
//fun Int.read(reader: ScaleCodecReader) = reader.readLong().toInt()
//
////todo is type correct?
//fun Int.write(writer: ScaleCodecWriter, instance: Int) = writer.directWrite(instance)
//
//fun ByteArray.read(reader: ScaleCodecReader): ByteArray = reader.readByteArray()
//
//fun ByteArray.write(writer: ScaleCodecWriter, instance: ByteArray) =  writer.writeByteArray(instance)
//
//fun String.read(reader: ScaleCodecReader): String = reader.readString();
//
//fun String.write(writer: ScaleCodecWriter, instance: String) = writer.writeString(instance)
//
//fun  List<PublicKey>.read(reader: ScaleCodecReader): List<PublicKey> {
//    val size: Int = reader.readCompactInt()
//    val result: MutableList<PublicKey> = ArrayList(size)
//
//    for (i in 0 until size) {
//        result.add(reader.read(PublicKey.READER))
//    }
//
//    return result}
