package jp.co.soramitsu.iroha2.model.events.reject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnexpectedGenesisAccountSignature implements TransactionRejectionReason {

}
