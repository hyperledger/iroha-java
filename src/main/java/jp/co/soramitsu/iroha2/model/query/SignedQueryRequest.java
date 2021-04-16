package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.Signature;
import jp.co.soramitsu.iroha2.model.U128;
import lombok.Data;

/**
 * Data model class for query request
 */
@Data
public class SignedQueryRequest {
    private U128 timestamp;
    private Signature signature;
    private Query query;
}
