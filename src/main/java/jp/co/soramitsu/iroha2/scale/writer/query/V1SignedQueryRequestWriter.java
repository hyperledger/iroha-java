package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.V1SignedQueryRequest;

import java.io.IOException;

public class V1SignedQueryRequestWriter implements ScaleWriter<V1SignedQueryRequest> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, V1SignedQueryRequest v1) throws IOException {
        scaleCodecWriter.write(new SignedQueryRequestWriter(), v1.getContent());
    }
}
