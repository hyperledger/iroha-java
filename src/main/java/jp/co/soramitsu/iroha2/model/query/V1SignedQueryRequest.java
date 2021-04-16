package jp.co.soramitsu.iroha2.model.query;

import lombok.Data;
import lombok.NonNull;

@Data
public class V1SignedQueryRequest implements VersionedSignedQueryRequest {

    @NonNull
    private SignedQueryRequest content;

    @Override
    public int getIndex() {
        return 1;
    }
}
