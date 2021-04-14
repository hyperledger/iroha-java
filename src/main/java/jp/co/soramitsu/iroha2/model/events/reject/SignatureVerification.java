package jp.co.soramitsu.iroha2.model.events.reject;

import jp.co.soramitsu.iroha2.model.Signature;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SignatureVerification implements TransactionRejectionReason {

    @NonNull
    private Signature signature;

    @NonNull
    private String reason;
}
