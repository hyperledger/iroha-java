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
import kotlin.Boolean
import kotlin.Int
import kotlin.Unit
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
        public val bool: Boolean,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Bool>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Bool> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Bool = try {
                Bool(
                    reader.readBoolean(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Bool,
            ): Unit = try {
                if (instance.bool) { writer.directWrite(1) } else { writer.directWrite(0) }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'String' variant
     */
    public data class String(
        public val string: kotlin.String,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.String>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.String> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.String =
                try {
                    String(
                        reader.readString(),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.String,
            ): Unit = try {
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
        public val name: jp.co.soramitsu.iroha2.generated.Name,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Name>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Name> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Name = try {
                Name(
                    jp.co.soramitsu.iroha2.generated.Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Name,
            ): Unit = try {
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
        public val vec: List<Value>,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Vec>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Vec> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Vec = try {
                Vec(
                    reader.readVec(reader.readCompactInt()) { Value.read(reader) },
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Vec,
            ): Unit = try {
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
        public val metadata: Metadata,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.LimitedMetadata>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.LimitedMetadata> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.LimitedMetadata = try {
                LimitedMetadata(
                    Metadata.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.LimitedMetadata,
            ): Unit = try {
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
        public val limits: Limits,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.MetadataLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.MetadataLimits> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.MetadataLimits = try {
                MetadataLimits(
                    Limits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.MetadataLimits,
            ): Unit = try {
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
        public val transactionLimits: jp.co.soramitsu.iroha2.generated.TransactionLimits,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.TransactionLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.TransactionLimits> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.TransactionLimits = try {
                TransactionLimits(
                    jp.co.soramitsu.iroha2.generated.TransactionLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.TransactionLimits,
            ): Unit = try {
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
        public val lengthLimits: jp.co.soramitsu.iroha2.generated.LengthLimits,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.LengthLimits>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.LengthLimits> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.LengthLimits = try {
                LengthLimits(
                    jp.co.soramitsu.iroha2.generated.LengthLimits.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.LengthLimits,
            ): Unit = try {
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
        public val idBox: IdBox,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Id>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Id> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Id = try {
                Id(
                    IdBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Id,
            ): Unit = try {
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
        public val identifiableBox: IdentifiableBox,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Identifiable>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Identifiable> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Identifiable = try {
                Identifiable(
                    IdentifiableBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Identifiable,
            ): Unit = try {
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
        public val publicKey: jp.co.soramitsu.iroha2.generated.PublicKey,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.PublicKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.PublicKey> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.PublicKey = try {
                PublicKey(
                    jp.co.soramitsu.iroha2.generated.PublicKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.PublicKey,
            ): Unit = try {
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
        public val signatureCheckCondition: jp.co.soramitsu.iroha2.generated.SignatureCheckCondition,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.SignatureCheckCondition>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.SignatureCheckCondition> {
            public const val DISCRIMINANT: Int = 11

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.SignatureCheckCondition = try {
                SignatureCheckCondition(
                    jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.SignatureCheckCondition,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.SignatureCheckCondition.write(
                    writer,
                    instance.signatureCheckCondition,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TransactionQueryOutput' variant
     */
    public data class TransactionQueryOutput(
        public val transactionQueryOutput: jp.co.soramitsu.iroha2.generated.TransactionQueryOutput,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.TransactionQueryOutput>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.TransactionQueryOutput> {
            public const val DISCRIMINANT: Int = 12

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.TransactionQueryOutput = try {
                TransactionQueryOutput(
                    jp.co.soramitsu.iroha2.generated.TransactionQueryOutput.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.TransactionQueryOutput,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.TransactionQueryOutput.write(
                    writer,
                    instance.transactionQueryOutput,
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
        public val permissionToken: jp.co.soramitsu.iroha2.generated.PermissionToken,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.PermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.PermissionToken> {
            public const val DISCRIMINANT: Int = 13

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.PermissionToken = try {
                PermissionToken(
                    jp.co.soramitsu.iroha2.generated.PermissionToken.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.PermissionToken,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.PermissionToken.write(writer, instance.permissionToken)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionTokenSchema' variant
     */
    public data class PermissionTokenSchema(
        public val permissionTokenSchema: jp.co.soramitsu.iroha2.generated.PermissionTokenSchema,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.PermissionTokenSchema>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.PermissionTokenSchema> {
            public const val DISCRIMINANT: Int = 14

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.PermissionTokenSchema = try {
                PermissionTokenSchema(
                    jp.co.soramitsu.iroha2.generated.PermissionTokenSchema.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.PermissionTokenSchema,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.PermissionTokenSchema.write(
                    writer,
                    instance.permissionTokenSchema,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Hash' variant
     */
    public data class Hash(
        public val hashValue: HashValue,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Hash>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Hash> {
            public const val DISCRIMINANT: Int = 15

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Hash = try {
                Hash(
                    HashValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Hash,
            ): Unit = try {
                HashValue.write(writer, instance.hashValue)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val versionedCommittedBlock: VersionedCommittedBlock,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Block>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Block> {
            public const val DISCRIMINANT: Int = 16

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Block =
                try {
                    Block(
                        VersionedCommittedBlock.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Block,
            ): Unit = try {
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
        public val blockHeader: jp.co.soramitsu.iroha2.generated.BlockHeader,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.BlockHeader>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.BlockHeader> {
            public const val DISCRIMINANT: Int = 17

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.BlockHeader = try {
                BlockHeader(
                    jp.co.soramitsu.iroha2.generated.BlockHeader.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.BlockHeader,
            ): Unit = try {
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
        public val ipv4Addr: jp.co.soramitsu.iroha2.generated.Ipv4Addr,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Ipv4Addr>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Ipv4Addr> {
            public const val DISCRIMINANT: Int = 18

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Ipv4Addr =
                try {
                    Ipv4Addr(
                        jp.co.soramitsu.iroha2.generated.Ipv4Addr.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Ipv4Addr,
            ): Unit = try {
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
        public val ipv6Addr: jp.co.soramitsu.iroha2.generated.Ipv6Addr,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Ipv6Addr>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Ipv6Addr> {
            public const val DISCRIMINANT: Int = 19

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Ipv6Addr =
                try {
                    Ipv6Addr(
                        jp.co.soramitsu.iroha2.generated.Ipv6Addr.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Ipv6Addr,
            ): Unit = try {
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
        public val numericValue: NumericValue,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Numeric>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Numeric> {
            public const val DISCRIMINANT: Int = 20

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Numeric =
                try {
                    Numeric(
                        NumericValue.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Numeric,
            ): Unit = try {
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
        public val validator: jp.co.soramitsu.iroha2.generated.Validator,
    ) : Value() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Value.Validator>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Value.Validator> {
            public const val DISCRIMINANT: Int = 21

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Value.Validator = try {
                Validator(
                    jp.co.soramitsu.iroha2.generated.Validator.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Value.Validator,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Validator.write(writer, instance.validator)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Value>, ScaleWriter<Value> {
        override fun read(reader: ScaleCodecReader): Value {
            val discriminant = reader.readUByte()
            return when (discriminant) {
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
                12 -> TransactionQueryOutput.read(reader)
                13 -> PermissionToken.read(reader)
                14 -> PermissionTokenSchema.read(reader)
                15 -> Hash.read(reader)
                16 -> Block.read(reader)
                17 -> BlockHeader.read(reader)
                18 -> Ipv4Addr.read(reader)
                19 -> Ipv6Addr.read(reader)
                20 -> Numeric.read(reader)
                21 -> Validator.read(reader)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }

        override fun write(writer: ScaleCodecWriter, instance: Value) {
            writer.directWrite(instance.discriminant())
            val discriminant = instance.discriminant()
            when (discriminant) {
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
                12 -> TransactionQueryOutput.write(writer, instance as TransactionQueryOutput)
                13 -> PermissionToken.write(writer, instance as PermissionToken)
                14 -> PermissionTokenSchema.write(writer, instance as PermissionTokenSchema)
                15 -> Hash.write(writer, instance as Hash)
                16 -> Block.write(writer, instance as Block)
                17 -> BlockHeader.write(writer, instance as BlockHeader)
                18 -> Ipv4Addr.write(writer, instance as Ipv4Addr)
                19 -> Ipv6Addr.write(writer, instance as Ipv6Addr)
                20 -> Numeric.write(writer, instance as Numeric)
                21 -> Validator.write(writer, instance as Validator)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
