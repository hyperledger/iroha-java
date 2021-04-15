package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jp.co.soramitsu.iroha2.model.events.reject.RejectionReason;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.WRAPPER_OBJECT
//)
public class Rejected implements Status {

    @NonNull
    @JsonAlias({"Transaction", "Block"})
    private RejectionReason reason;

}