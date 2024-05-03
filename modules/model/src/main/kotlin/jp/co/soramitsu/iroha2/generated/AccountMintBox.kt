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

/**
 * AccountMintBox
 *
 * Generated from 'AccountMintBox' enum
 */
public sealed class AccountMintBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'PublicKey' variant
     */
    public data class PublicKey(
        public val mintOfPublicKeyAndAccount: MintOfPublicKeyAndAccount,
    ) : AccountMintBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountMintBox.PublicKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountMintBox.PublicKey> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountMintBox.PublicKey = try {
                PublicKey(
                    MintOfPublicKeyAndAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountMintBox.PublicKey,
            ): Unit = try {
                MintOfPublicKeyAndAccount.write(writer, instance.mintOfPublicKeyAndAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SignatureCheckCondition' variant
     */
    public data class SignatureCheckCondition(
        public val mintOfSignatureCheckConditionAndAccount: MintOfSignatureCheckConditionAndAccount,
    ) : AccountMintBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountMintBox.SignatureCheckCondition>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountMintBox.SignatureCheckCondition> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountMintBox.SignatureCheckCondition = try {
                SignatureCheckCondition(
                    MintOfSignatureCheckConditionAndAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountMintBox.SignatureCheckCondition,
            ): Unit =
                try {
                    MintOfSignatureCheckConditionAndAccount.write(
                        writer,
                        instance.mintOfSignatureCheckConditionAndAccount,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    public companion object : ScaleReader<AccountMintBox>, ScaleWriter<AccountMintBox> {
        override fun read(reader: ScaleCodecReader): AccountMintBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> PublicKey.read(reader)
            1 -> SignatureCheckCondition.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AccountMintBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> PublicKey.write(writer, instance as PublicKey)
                1 -> SignatureCheckCondition.write(writer, instance as SignatureCheckCondition)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
