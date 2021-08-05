package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonAlias;
import jp.co.soramitsu.iroha2.model.events.reject.RejectionReason;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Rejected implements Status {

  @NonNull
  @JsonAlias({"Transaction", "Block"})
  private RejectionReason reason;

}
