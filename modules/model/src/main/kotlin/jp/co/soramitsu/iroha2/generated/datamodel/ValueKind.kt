//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.Boolean
import kotlin.Int

/**
 * ValueKind
 *
 * Generated from 'iroha_data_model::ValueKind' enum
 */
public sealed class ValueKind : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    public override fun equals(other: Any?) = when (this) {
        is U32 -> U32.equals(this, other)
        is U128 -> U128.equals(this, other)
        is Bool -> Bool.equals(this, other)
        is String -> String.equals(this, other)
        is Name -> Name.equals(this, other)
        is Fixed -> Fixed.equals(this, other)
        is Vec -> Vec.equals(this, other)
        is LimitedMetadata -> LimitedMetadata.equals(this, other)
        is Id -> Id.equals(this, other)
        is Identifiable -> Identifiable.equals(this, other)
        is PublicKey -> PublicKey.equals(this, other)
        is Parameter -> Parameter.equals(this, other)
        is SignatureCheckCondition -> SignatureCheckCondition.equals(this, other)
        is TransactionValue -> TransactionValue.equals(this, other)
        is TransactionQueryResult -> TransactionQueryResult.equals(this, other)
        is PermissionToken -> PermissionToken.equals(this, other)
        is Hash -> Hash.equals(this, other)
        is Block -> Block.equals(this, other)
        is BlockHeader -> BlockHeader.equals(this, other)
        is Ipv4Addr -> Ipv4Addr.equals(this, other)
        is Ipv6Addr -> Ipv6Addr.equals(this, other)
        else -> super.equals(other)
    }

    public override fun hashCode() = when (this) {
        is U32 -> U32.hashCode()
        is U128 -> U128.hashCode()
        is Bool -> Bool.hashCode()
        is String -> String.hashCode()
        is Name -> Name.hashCode()
        is Fixed -> Fixed.hashCode()
        is Vec -> Vec.hashCode()
        is LimitedMetadata -> LimitedMetadata.hashCode()
        is Id -> Id.hashCode()
        is Identifiable -> Identifiable.hashCode()
        is PublicKey -> PublicKey.hashCode()
        is Parameter -> Parameter.hashCode()
        is SignatureCheckCondition -> SignatureCheckCondition.hashCode()
        is TransactionValue -> TransactionValue.hashCode()
        is TransactionQueryResult -> TransactionQueryResult.hashCode()
        is PermissionToken -> PermissionToken.hashCode()
        is Hash -> Hash.hashCode()
        is Block -> Block.hashCode()
        is BlockHeader -> BlockHeader.hashCode()
        is Ipv4Addr -> Ipv4Addr.hashCode()
        is Ipv6Addr -> Ipv6Addr.hashCode()
        else -> super.hashCode()
    }

    /**
     * 'U32' variant
     */
    public class U32 : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U32>, ScaleWriter<U32> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): U32 = try {
                U32()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U32) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: U32, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.U32".hashCode()
        }
    }

    /**
     * 'U128' variant
     */
    public class U128 : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<U128>, ScaleWriter<U128> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): U128 = try {
                U128()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: U128) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: U128, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.U128".hashCode()
        }
    }

    /**
     * 'Bool' variant
     */
    public class Bool : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Bool>, ScaleWriter<Bool> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Bool = try {
                Bool()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Bool) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Bool, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Bool".hashCode()
        }
    }

    /**
     * 'String' variant
     */
    public class String : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<String>, ScaleWriter<String> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): String = try {
                String()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: String) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: String, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.String".hashCode()
        }
    }

    /**
     * 'Name' variant
     */
    public class Name : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Name>, ScaleWriter<Name> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Name = try {
                Name()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Name) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Name, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Name".hashCode()
        }
    }

    /**
     * 'Fixed' variant
     */
    public class Fixed : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Fixed>, ScaleWriter<Fixed> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Fixed = try {
                Fixed()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Fixed) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Fixed, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Fixed".hashCode()
        }
    }

    /**
     * 'Vec' variant
     */
    public class Vec : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Vec>, ScaleWriter<Vec> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Vec = try {
                Vec()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Vec) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Vec, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Vec".hashCode()
        }
    }

    /**
     * 'LimitedMetadata' variant
     */
    public class LimitedMetadata : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<LimitedMetadata>, ScaleWriter<LimitedMetadata> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): LimitedMetadata = try {
                LimitedMetadata()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: LimitedMetadata) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: LimitedMetadata, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.LimitedMetadata".hashCode()
        }
    }

    /**
     * 'Id' variant
     */
    public class Id : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Id>, ScaleWriter<Id> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Id = try {
                Id()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Id) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Id, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Id".hashCode()
        }
    }

    /**
     * 'Identifiable' variant
     */
    public class Identifiable : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Identifiable>, ScaleWriter<Identifiable> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): Identifiable = try {
                Identifiable()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Identifiable) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Identifiable, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Identifiable".hashCode()
        }
    }

    /**
     * 'PublicKey' variant
     */
    public class PublicKey : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): PublicKey = try {
                PublicKey()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PublicKey) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: PublicKey, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.PublicKey".hashCode()
        }
    }

    /**
     * 'Parameter' variant
     */
    public class Parameter : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Parameter>, ScaleWriter<Parameter> {
            public const val DISCRIMINANT: Int = 11

            public override fun read(reader: ScaleCodecReader): Parameter = try {
                Parameter()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Parameter) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Parameter, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Parameter".hashCode()
        }
    }

    /**
     * 'SignatureCheckCondition' variant
     */
    public class SignatureCheckCondition : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<SignatureCheckCondition>,
            ScaleWriter<SignatureCheckCondition> {
            public const val DISCRIMINANT: Int = 12

            public override fun read(reader: ScaleCodecReader): SignatureCheckCondition = try {
                SignatureCheckCondition()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: SignatureCheckCondition) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: SignatureCheckCondition, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.SignatureCheckCondition".hashCode()
        }
    }

    /**
     * 'TransactionValue' variant
     */
    public class TransactionValue : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): TransactionValue = try {
                TransactionValue()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionValue) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: TransactionValue, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.TransactionValue".hashCode()
        }
    }

    /**
     * 'TransactionQueryResult' variant
     */
    public class TransactionQueryResult : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<TransactionQueryResult>,
            ScaleWriter<TransactionQueryResult> {
            public const val DISCRIMINANT: Int = 14

            public override fun read(reader: ScaleCodecReader): TransactionQueryResult = try {
                TransactionQueryResult()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TransactionQueryResult) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: TransactionQueryResult, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.TransactionQueryResult".hashCode()
        }
    }

    /**
     * 'PermissionToken' variant
     */
    public class PermissionToken : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
            public const val DISCRIMINANT: Int = 15

            public override fun read(reader: ScaleCodecReader): PermissionToken = try {
                PermissionToken()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: PermissionToken, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.PermissionToken".hashCode()
        }
    }

    /**
     * 'Hash' variant
     */
    public class Hash : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
            public const val DISCRIMINANT: Int = 16

            public override fun read(reader: ScaleCodecReader): Hash = try {
                Hash()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Hash) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Hash, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Hash".hashCode()
        }
    }

    /**
     * 'Block' variant
     */
    public class Block : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 17

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Block, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Block".hashCode()
        }
    }

    /**
     * 'BlockHeader' variant
     */
    public class BlockHeader : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<BlockHeader>, ScaleWriter<BlockHeader> {
            public const val DISCRIMINANT: Int = 18

            public override fun read(reader: ScaleCodecReader): BlockHeader = try {
                BlockHeader()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: BlockHeader) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: BlockHeader, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.BlockHeader".hashCode()
        }
    }

    /**
     * 'Ipv4Addr' variant
     */
    public class Ipv4Addr : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv4Addr>, ScaleWriter<Ipv4Addr> {
            public const val DISCRIMINANT: Int = 19

            public override fun read(reader: ScaleCodecReader): Ipv4Addr = try {
                Ipv4Addr()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv4Addr) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Ipv4Addr, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Ipv4Addr".hashCode()
        }
    }

    /**
     * 'Ipv6Addr' variant
     */
    public class Ipv6Addr : ValueKind() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv6Addr>, ScaleWriter<Ipv6Addr> {
            public const val DISCRIMINANT: Int = 20

            public override fun read(reader: ScaleCodecReader): Ipv6Addr = try {
                Ipv6Addr()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv6Addr) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public fun equals(o1: Ipv6Addr, o2: Any?): Boolean = when (o2) {
                null -> false
                else -> o2::class == o1::class
            }

            public override fun hashCode(): Int = "datamodel.ValueKind.Ipv6Addr".hashCode()
        }
    }

    public companion object : ScaleReader<ValueKind>, ScaleWriter<ValueKind> {
        public override fun read(reader: ScaleCodecReader): ValueKind = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> U32.read(reader)
            1 -> U128.read(reader)
            2 -> Bool.read(reader)
            3 -> String.read(reader)
            4 -> Name.read(reader)
            5 -> Fixed.read(reader)
            6 -> Vec.read(reader)
            7 -> LimitedMetadata.read(reader)
            8 -> Id.read(reader)
            9 -> Identifiable.read(reader)
            10 -> PublicKey.read(reader)
            11 -> Parameter.read(reader)
            12 -> SignatureCheckCondition.read(reader)
            13 -> TransactionValue.read(reader)
            14 -> TransactionQueryResult.read(reader)
            15 -> PermissionToken.read(reader)
            16 -> Hash.read(reader)
            17 -> Block.read(reader)
            18 -> BlockHeader.read(reader)
            19 -> Ipv4Addr.read(reader)
            20 -> Ipv6Addr.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValueKind) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> U32.write(writer, instance as U32)
                1 -> U128.write(writer, instance as U128)
                2 -> Bool.write(writer, instance as Bool)
                3 -> String.write(writer, instance as String)
                4 -> Name.write(writer, instance as Name)
                5 -> Fixed.write(writer, instance as Fixed)
                6 -> Vec.write(writer, instance as Vec)
                7 -> LimitedMetadata.write(writer, instance as LimitedMetadata)
                8 -> Id.write(writer, instance as Id)
                9 -> Identifiable.write(writer, instance as Identifiable)
                10 -> PublicKey.write(writer, instance as PublicKey)
                11 -> Parameter.write(writer, instance as Parameter)
                12 -> SignatureCheckCondition.write(writer, instance as SignatureCheckCondition)
                13 -> TransactionValue.write(writer, instance as TransactionValue)
                14 -> TransactionQueryResult.write(writer, instance as TransactionQueryResult)
                15 -> PermissionToken.write(writer, instance as PermissionToken)
                16 -> Hash.write(writer, instance as Hash)
                17 -> Block.write(writer, instance as Block)
                18 -> BlockHeader.write(writer, instance as BlockHeader)
                19 -> Ipv4Addr.write(writer, instance as Ipv4Addr)
                20 -> Ipv6Addr.write(writer, instance as Ipv6Addr)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
