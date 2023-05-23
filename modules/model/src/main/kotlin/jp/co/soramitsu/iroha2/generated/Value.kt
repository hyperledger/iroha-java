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
import kotlin.collections.List

/**
 * Value
 *
 * Generated from 'Value' enum
 */
public sealed class Value : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Bool' variant
     */
    public data class Bool(
        public val bool: jp.co.soramitsu.iroha2.generated.Bool
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Bool>, ScaleWriter<Bool> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Bool = try {
                Bool(
                    jp.co.soramitsu.iroha2.generated.Bool.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Bool) = try {
                jp.co.soramitsu.iroha2.generated.Bool.write(writer, instance.bool)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'String' variant
     */
    public data class String(
        public val string: kotlin.String
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<String>, ScaleWriter<String> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): String = try {
                String(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: String) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Name' variant
     */
    public data class Name(
        public val name: jp.co.soramitsu.iroha2.generated.Name
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Name>, ScaleWriter<Name> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Name = try {
                Name(
                    jp.co.soramitsu.iroha2.generated.Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Name) = try {
                jp.co.soramitsu.iroha2.generated.Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Vec' variant
     */
    public data class Vec(
        public val vec: List<Value>
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Vec>, ScaleWriter<Vec> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Vec = try {
                Vec(
                    reader.readVec(reader.readCompactInt()) { Value.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Vec) = try {
                writer.writeCompact(instance.vec.size)
                instance.vec.forEach { value ->
                    Value.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'LimitedMetadata' variant
     */
    public data class LimitedMetadata(
        public val metadata: Metadata
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<LimitedMetadata>, ScaleWriter<LimitedMetadata> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): LimitedMetadata = try {
                LimitedMetadata(
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: LimitedMetadata) = try {
                Metadata.write(writer, instance.metadata)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataLimits' variant
     */
    public data class MetadataLimits(
        public val limits: Limits
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataLimits>, ScaleWriter<MetadataLimits> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): MetadataLimits = try {
                MetadataLimits(
                    Limits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataLimits) = try {
                Limits.write(writer, instance.limits)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TransactionLimits' variant
     */
    public data class TransactionLimits(
        public val transactionLimits: jp.co.soramitsu.iroha2.generated.TransactionLimits
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TransactionLimits>, ScaleWriter<TransactionLimits> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): TransactionLimits = try {
                TransactionLimits(
                    jp.co.soramitsu.iroha2.generated.TransactionLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionLimits) = try {
                jp.co.soramitsu.iroha2.generated.TransactionLimits.write(writer, instance.transactionLimits)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'LengthLimits' variant
     */
    public data class LengthLimits(
        public val lengthLimits: jp.co.soramitsu.iroha2.generated.LengthLimits
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<LengthLimits>, ScaleWriter<LengthLimits> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): LengthLimits = try {
                LengthLimits(
                    jp.co.soramitsu.iroha2.generated.LengthLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: LengthLimits) = try {
                jp.co.soramitsu.iroha2.generated.LengthLimits.write(writer, instance.lengthLimits)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Id' variant
     */
    public data class Id(
        public val idBox: IdBox
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Id>, ScaleWriter<Id> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Id = try {
                Id(
                    IdBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Id) = try {
                IdBox.write(writer, instance.idBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Identifiable' variant
     */
    public data class Identifiable(
        public val identifiableBox: IdentifiableBox
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Identifiable>, ScaleWriter<Identifiable> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): Identifiable = try {
                Identifiable(
                    IdentifiableBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Identifiable) = try {
                IdentifiableBox.write(writer, instance.identifiableBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PublicKey' variant
     */
    public data class PublicKey(
        public val publicKey: jp.co.soramitsu.iroha2.generated.PublicKey
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): PublicKey = try {
                PublicKey(
                    jp.co.soramitsu.iroha2.generated.PublicKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PublicKey) = try {
                jp.co.soramitsu.iroha2.generated.PublicKey.write(writer, instance.publicKey)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'SignatureCheckCondition' variant
     */
    public data class SignatureCheckCondition(
        public val signatureCheckCondition: jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<SignatureCheckCondition>,
            ScaleWriter<SignatureCheckCondition> {
            public const val DISCRIMINANT: Int = 11

            public override fun read(reader: ScaleCodecReader): SignatureCheckCondition = try {
                SignatureCheckCondition(
                    jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition) = try {
                jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.write(
                    writer,
                    instance.signatureCheckCondition
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TransactionValue' variant
     */
    public data class TransactionValue(
        public val transactionValue: jp.co.soramitsu.iroha2.generated.TransactionValue
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
            public const val DISCRIMINANT: Int = 12

            public override fun read(reader: ScaleCodecReader): TransactionValue = try {
                TransactionValue(
                    jp.co.soramitsu.iroha2.generated.TransactionValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionValue) = try {
                jp.co.soramitsu.iroha2.generated.TransactionValue.write(writer, instance.transactionValue)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TransactionQueryResult' variant
     */
    public data class TransactionQueryResult(
        public val transactionQueryResult: jp.co.soramitsu.iroha2.generated.TransactionQueryResult
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<TransactionQueryResult>,
            ScaleWriter<TransactionQueryResult> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): TransactionQueryResult = try {
                TransactionQueryResult(
                    jp.co.soramitsu.iroha2.generated.TransactionQueryResult.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionQueryResult) = try {
                jp.co.soramitsu.iroha2.generated.TransactionQueryResult.write(
                    writer,
                    instance.transactionQueryResult
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionToken' variant
     */
    public data class PermissionToken(
        public val permissionToken: jp.co.soramitsu.iroha2.generated.PermissionToken
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
            public const val DISCRIMINANT: Int = 14

            public override fun read(reader: ScaleCodecReader): PermissionToken = try {
                PermissionToken(
                    jp.co.soramitsu.iroha2.generated.PermissionToken.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
                jp.co.soramitsu.iroha2.generated.PermissionToken.write(writer, instance.permissionToken)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Hash' variant
     */
    public data class Hash(
        public val hash: jp.co.soramitsu.iroha2.generated.Hash
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
            public const val DISCRIMINANT: Int = 15

            public override fun read(reader: ScaleCodecReader): Hash = try {
                Hash(
                    jp.co.soramitsu.iroha2.generated.Hash.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Hash) = try {
                jp.co.soramitsu.iroha2.generated.Hash.write(writer, instance.hash)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val versionedCommittedBlock: VersionedCommittedBlock
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 16

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block(
                    VersionedCommittedBlock.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
                VersionedCommittedBlock.write(writer, instance.versionedCommittedBlock)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'BlockHeader' variant
     */
    public data class BlockHeader(
        public val blockHeader: jp.co.soramitsu.iroha2.generated.BlockHeader
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlockHeader>, ScaleWriter<BlockHeader> {
            public const val DISCRIMINANT: Int = 17

            public override fun read(reader: ScaleCodecReader): BlockHeader = try {
                BlockHeader(
                    jp.co.soramitsu.iroha2.generated.BlockHeader.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlockHeader) = try {
                jp.co.soramitsu.iroha2.generated.BlockHeader.write(writer, instance.blockHeader)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Ipv4Addr' variant
     */
    public data class Ipv4Addr(
        public val ipv4Addr: jp.co.soramitsu.iroha2.generated.Ipv4Addr
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv4Addr>, ScaleWriter<Ipv4Addr> {
            public const val DISCRIMINANT: Int = 18

            public override fun read(reader: ScaleCodecReader): Ipv4Addr = try {
                Ipv4Addr(
                    jp.co.soramitsu.iroha2.generated.Ipv4Addr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv4Addr) = try {
                jp.co.soramitsu.iroha2.generated.Ipv4Addr.write(writer, instance.ipv4Addr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Ipv6Addr' variant
     */
    public data class Ipv6Addr(
        public val ipv6Addr: jp.co.soramitsu.iroha2.generated.Ipv6Addr
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv6Addr>, ScaleWriter<Ipv6Addr> {
            public const val DISCRIMINANT: Int = 19

            public override fun read(reader: ScaleCodecReader): Ipv6Addr = try {
                Ipv6Addr(
                    jp.co.soramitsu.iroha2.generated.Ipv6Addr.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv6Addr) = try {
                jp.co.soramitsu.iroha2.generated.Ipv6Addr.write(writer, instance.ipv6Addr)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Numeric' variant
     */
    public data class Numeric(
        public val numericValue: NumericValue
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Numeric>, ScaleWriter<Numeric> {
            public const val DISCRIMINANT: Int = 20

            public override fun read(reader: ScaleCodecReader): Numeric = try {
                Numeric(
                    NumericValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Numeric) = try {
                NumericValue.write(writer, instance.numericValue)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Validator' variant
     */
    public data class Validator(
        public val validator: jp.co.soramitsu.iroha2.generated.Validator
    ) : Value() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Validator>, ScaleWriter<Validator> {
            public const val DISCRIMINANT: Int = 21

            public override fun read(reader: ScaleCodecReader): Validator = try {
                Validator(
                    jp.co.soramitsu.iroha2.generated.Validator.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Validator) = try {
                jp.co.soramitsu.iroha2.generated.Validator.write(writer, instance.validator)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Value>, ScaleWriter<Value> {
        public override fun read(reader: ScaleCodecReader): Value = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Bool.read(reader)
            1 -> String.read(reader)
            2 -> Name.read(reader)
            3 -> Vec.read(reader)
            4 -> LimitedMetadata.read(reader)
            5 -> MetadataLimits.read(reader)
            6 -> TransactionLimits.read(reader)
            7 -> LengthLimits.read(reader)
            8 -> Id.read(reader)
            9 -> Identifiable.read(reader)
            10 -> PublicKey.read(reader)
            11 -> SignatureCheckCondition.read(reader)
            12 -> TransactionValue.read(reader)
            13 -> TransactionQueryResult.read(reader)
            14 -> PermissionToken.read(reader)
            15 -> Hash.read(reader)
            16 -> Block.read(reader)
            17 -> BlockHeader.read(reader)
            18 -> Ipv4Addr.read(reader)
            19 -> Ipv6Addr.read(reader)
            20 -> Numeric.read(reader)
            21 -> Validator.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Value) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Bool.write(writer, instance as Bool)
                1 -> String.write(writer, instance as String)
                2 -> Name.write(writer, instance as Name)
                3 -> Vec.write(writer, instance as Vec)
                4 -> LimitedMetadata.write(writer, instance as LimitedMetadata)
                5 -> MetadataLimits.write(writer, instance as MetadataLimits)
                6 -> TransactionLimits.write(writer, instance as TransactionLimits)
                7 -> LengthLimits.write(writer, instance as LengthLimits)
                8 -> Id.write(writer, instance as Id)
                9 -> Identifiable.write(writer, instance as Identifiable)
                10 -> PublicKey.write(writer, instance as PublicKey)
                11 -> SignatureCheckCondition.write(writer, instance as SignatureCheckCondition)
                12 -> TransactionValue.write(writer, instance as TransactionValue)
                13 -> TransactionQueryResult.write(writer, instance as TransactionQueryResult)
                14 -> PermissionToken.write(writer, instance as PermissionToken)
                15 -> Hash.write(writer, instance as Hash)
                16 -> Block.write(writer, instance as Block)
                17 -> BlockHeader.write(writer, instance as BlockHeader)
                18 -> Ipv4Addr.write(writer, instance as Ipv4Addr)
                19 -> Ipv6Addr.write(writer, instance as Ipv6Addr)
                20 -> Numeric.write(writer, instance as Numeric)
                21 -> Validator.write(writer, instance as Validator)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
