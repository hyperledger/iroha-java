package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.V1QueryResult;

public class V1QueryResultReader implements ScaleReader<V1QueryResult> {

    private static final QueryResultReader QUERY_RESULT_READER = new QueryResultReader();

    @Override
    public V1QueryResult read(ScaleCodecReader scaleCodecReader) {
        return new V1QueryResult(scaleCodecReader.read(QUERY_RESULT_READER));
    }
}
