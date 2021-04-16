package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import io.emeraldpay.polkaj.scale.reader.UnsupportedReader;
import jp.co.soramitsu.iroha2.model.query.VersionedQueryResult;

public class VersionedQueryResultReader implements ScaleReader<VersionedQueryResult> {

  private static final UnionReader<VersionedQueryResult> VERSIONED_QUERY_RESULT__READER = new UnionReader<>(
      new UnsupportedReader(), // 0 - not used
      new V1QueryResultReader() //1 - V1
  );

  @Override
  public VersionedQueryResult read(ScaleCodecReader scaleCodecReader) {
    return scaleCodecReader.read(VERSIONED_QUERY_RESULT__READER).getValue();
  }
}
