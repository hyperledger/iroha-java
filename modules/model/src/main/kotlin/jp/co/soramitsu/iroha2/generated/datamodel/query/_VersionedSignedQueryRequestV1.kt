//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * _VersionedSignedQueryRequestV1
 *
 * Generated from 'iroha_data_model::query::_VersionedSignedQueryRequestV1' tuple structure
 */
public data class _VersionedSignedQueryRequestV1(
    public val signedQueryRequest: SignedQueryRequest
) {
    public companion object :
        ScaleReader<_VersionedSignedQueryRequestV1>,
        ScaleWriter<_VersionedSignedQueryRequestV1> {
        public override fun read(reader: ScaleCodecReader): _VersionedSignedQueryRequestV1 = try {
            _VersionedSignedQueryRequestV1(
                SignedQueryRequest.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: _VersionedSignedQueryRequestV1) =
            try {
                SignedQueryRequest.write(writer, instance.signedQueryRequest)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
