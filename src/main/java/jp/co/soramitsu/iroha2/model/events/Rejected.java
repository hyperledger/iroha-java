package jp.co.soramitsu.iroha2.model.events;

import jp.co.soramitsu.iroha2.model.events.reject.RejectionReason;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
    public class Rejected implements Status {

      @NonNull
      private RejectionReason rejectedReason;
    }