package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import jp.co.soramitsu.iroha2.model.query.VersionedSignedQueryRequest;
import jp.co.soramitsu.iroha2.scale.writer.EnumerationUnionValue;
import jp.co.soramitsu.iroha2.scale.writer.NopWriter;

import java.io.IOException;

public class VersionedSignedQueryRequestWriter implements ScaleWriter<VersionedSignedQueryRequest> {

    private static UnionWriter<VersionedSignedQueryRequest> VERSIONED_REQUEST_UNION_WRITER = new UnionWriter<>(
            new NopWriter<>(), // 0 - must not be used
            new V1SignedQueryRequestWriter() // 1 - V1
    );

    @Override
    public void write(ScaleCodecWriter writer, VersionedSignedQueryRequest value) throws IOException {
        writer.write(VERSIONED_REQUEST_UNION_WRITER, new EnumerationUnionValue<>(value));
    }
}
