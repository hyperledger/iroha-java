package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.Transaction;
import jp.co.soramitsu.iroha2.model.events.reject.TransactionRejectionReason;

@JsonDeserialize(using = Status.StatusDeserializer.class)
public interface Status {

  class StatusDeserializer extends JsonDeserializer<Status> {

    public static final String VALIDATING_VARIANT_CLASS_NAME = Validating.class.getSimpleName();
    public static final String COMMITTED_VARIANT_CLASS_NAME = Committed.class.getSimpleName();
    public static final String REJECTED_VARIANT_CLASS_NAME = Rejected.class.getSimpleName();

    @Override
    public Status deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      final var tree = p.readValueAsTree();
      if (tree instanceof TextNode) {
        var variantName = ((TextNode) tree).asText();
        if (VALIDATING_VARIANT_CLASS_NAME.equals(variantName)) {
          return new Validating();
        } else if (COMMITTED_VARIANT_CLASS_NAME.equals(variantName)) {
          return new Committed();
        } else {
          final var errorName = String.format("Expected variant name one of %s or %s, but got %s",
              VALIDATING_VARIANT_CLASS_NAME, COMMITTED_VARIANT_CLASS_NAME, variantName);
          throw new JsonParseException(p, errorName);
        }
      } else if (tree instanceof ObjectNode) {
        var objectNode = ((ObjectNode) tree);
        final JsonNode jsonNode = objectNode
            .get(REJECTED_VARIANT_CLASS_NAME)
            .get(Transaction.class.getSimpleName());
        var reason = p.getCodec().treeToValue(jsonNode, TransactionRejectionReason.class);
        return new Rejected(reason);
      } else {
        throw new JsonParseException(p,
            "Expected status is represented as text or Json object, but node was " + tree
                .getClass());
      }
    }
  }
}
