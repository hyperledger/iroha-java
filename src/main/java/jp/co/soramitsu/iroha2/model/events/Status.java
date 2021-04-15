package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Validating.class, name = "Validating"),
        @JsonSubTypes.Type(value = Rejected.class, name = "Rejected"),
        @JsonSubTypes.Type(value = Committed.class, name = "Committed"),
})
public interface Status {

}