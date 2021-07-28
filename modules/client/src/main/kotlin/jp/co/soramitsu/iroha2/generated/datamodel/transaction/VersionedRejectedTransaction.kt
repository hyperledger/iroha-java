//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int

/**
 * VersionedRejectedTransaction
 *
 * Generated from 'iroha_data_model::transaction::VersionedRejectedTransaction' enum
 */
public sealed class VersionedRejectedTransaction {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'V1' variant
     */
    public class V1(
        private val _VersionedRejectedTransactionV1: _VersionedRejectedTransactionV1
    ) : VersionedRejectedTransaction() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<V1>, ScaleWriter<V1> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): V1 = V1(
                _VersionedRejectedTransactionV1.read(reader) as _VersionedRejectedTransactionV1,
            )

            public override fun write(writer: ScaleCodecWriter, instance: V1) {
                _VersionedRejectedTransactionV1.write(writer, instance._VersionedRejectedTransactionV1)
            }
        }
    }

    public companion object :
        ScaleReader<VersionedRejectedTransaction>,
        ScaleWriter<VersionedRejectedTransaction> {
        public override fun read(reader: ScaleCodecReader): VersionedRejectedTransaction = when (
            val
            discriminant = reader.readUByte()
        ) {
            1 -> V1.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: VersionedRejectedTransaction) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                1 -> V1.write(writer, instance as V1)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
