package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pipeline.class, name = "Pipeline"),
        @JsonSubTypes.Type(value = jp.co.soramitsu.iroha2.model.events.Data.class, name = "Data")
})
public interface Event {

}