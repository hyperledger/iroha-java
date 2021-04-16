package jp.co.soramitsu.iroha2.model.events.reject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotPermitted implements TransactionRejectionReason {

  @NonNull
  private String reason;
}
