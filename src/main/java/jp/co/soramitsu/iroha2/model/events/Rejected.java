package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jp.co.soramitsu.iroha2.model.events.reject.BlockRejectionReason;
import jp.co.soramitsu.iroha2.model.events.reject.NotPermitted;
import jp.co.soramitsu.iroha2.model.events.reject.RejectionReason;
import jp.co.soramitsu.iroha2.model.events.reject.TransactionRejectionReason;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.IOException;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor

//@JsonDeserialize(using = Rejected.RejectedDeserializer.class)
public class Rejected implements Status {

    @NonNull
    @JsonAlias({"Transaction", "Block"})
    private RejectionReason reason;

    public static class RejectedDeserializer extends JsonDeserializer<Rejected> {

        @Override
        public Rejected deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            final var s = p.getCodec().readValue(p, Rejected.class);
            System.out.println(s);
            return s;
        }
    }
}