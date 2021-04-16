package jp.co.soramitsu.iroha2.model.events.reject;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = TransactionRejectionReason.class, name = "Transaction"),
    @JsonSubTypes.Type(value = BlockRejectionReason.class, name = "Block"),
})
public interface RejectionReason {

}
