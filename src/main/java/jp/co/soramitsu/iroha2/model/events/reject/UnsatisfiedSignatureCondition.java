package jp.co.soramitsu.iroha2.model.events.reject;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UnsatisfiedSignatureCondition implements TransactionRejectionReason {

  @NonNull
  private String reason;
}
