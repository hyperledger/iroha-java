//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit
import kotlin.collections.List

/**
 * SignatureCheckCondition
 *
 * Generated from 'SignatureCheckCondition' enum
 */
public sealed class SignatureCheckCondition : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'AnyAccountSignatureOr' variant
     */
    public data class AnyAccountSignatureOr(
        public val vec: List<PublicKey>,
    ) : SignatureCheckCondition() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AnyAccountSignatureOr>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AnyAccountSignatureOr> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AnyAccountSignatureOr = try {
                AnyAccountSignatureOr(
                    reader.readVec(reader.readCompactInt()) { PublicKey.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AnyAccountSignatureOr,
            ): Unit = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    PublicKey.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AllAccountSignaturesAnd' variant
     */
    public data class AllAccountSignaturesAnd(
        public val vec: List<PublicKey>,
    ) : SignatureCheckCondition() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AllAccountSignaturesAnd>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AllAccountSignaturesAnd> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AllAccountSignaturesAnd = try {
                AllAccountSignaturesAnd(
                    reader.readVec(reader.readCompactInt()) { PublicKey.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.AllAccountSignaturesAnd,
            ): Unit = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    PublicKey.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object :
        ScaleReader<SignatureCheckCondition>,
        ScaleWriter<SignatureCheckCondition> {
        override fun read(reader: ScaleCodecReader): SignatureCheckCondition = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AnyAccountSignatureOr.read(reader)
            1 -> AllAccountSignaturesAnd.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AnyAccountSignatureOr.write(writer, instance as AnyAccountSignatureOr)
                1 -> AllAccountSignaturesAnd.write(writer, instance as AllAccountSignaturesAnd)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
