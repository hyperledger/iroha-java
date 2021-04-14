//package jp.co.soramitsu.iroha2.json.reader;
//
//import com.google.gson.*;
//import jp.co.soramitsu.iroha2.model.events.EntityType;
//import jp.co.soramitsu.iroha2.model.events.Event;
//import jp.co.soramitsu.iroha2.model.events.Data;
//import jp.co.soramitsu.iroha2.model.events.EventType;
//import jp.co.soramitsu.iroha2.model.events.Pipeline;
//import jp.co.soramitsu.iroha2.model.events.Committed;
//import jp.co.soramitsu.iroha2.model.events.Rejected;
//import jp.co.soramitsu.iroha2.model.events.Status;
//import jp.co.soramitsu.iroha2.model.events.Validating;
//import jp.co.soramitsu.iroha2.model.events.V1VersionedEvent;
//
//import java.lang.reflect.Type;
//
//public class EventReader implements JsonReader<V1VersionedEvent> {
//
//  private static class EventDeserializer implements JsonDeserializer<Event> {
//
//    @Override
//    public Event deserialize(JsonElement json, Type typeOfT,
//        JsonDeserializationContext context) throws JsonParseException {
//      EventType eventType;
//      if (json.getAsJsonObject().get("Data") != null) {
//        eventType = new Data();
//      } else if (json.getAsJsonObject().get("Pipeline") != null) {
//        JsonObject pipeline = json.getAsJsonObject().get("Pipeline").getAsJsonObject();
//        EntityType entityType;
//        String entityString = pipeline.get("entity_type").getAsString();
//        if (entityString.equals("Transaction")) {
//          entityType = EntityType.Transaction;
//        } else if (entityString.equals("Block")) {
//          entityType = EntityType.Block;
//        } else {
//          throw new JsonParseException("Unexpected entity_type: " + entityString);
//        }
//
//        JsonElement statusJsonElement = pipeline.get("status");
//        Status status;
//        if (statusJsonElement.isJsonObject()) {
//          JsonObject statusJsonObject = statusJsonElement.getAsJsonObject();
//          status = new Rejected(statusJsonObject.get("Rejected").getAsString());
//        } else {
//          String statusString = statusJsonElement.getAsString();
//          if (statusString.equals("Validating")) {
//            status = new Validating();
//          } else if (statusString.equals("Committed")) {
//            status = new Committed();
//          } else {
//            throw new JsonParseException("Unexpected status: " + statusString);
//          }
//        }
//
//        JsonArray bytes = pipeline.get("hash").getAsJsonArray();
//        byte[] hash = new byte[bytes.size()];
//        for (int i = 0; i < bytes.size(); ++i) {
//          hash[i] = bytes.get(i).getAsByte();
//        }
//        eventType = new Pipeline(entityType, status, hash);
//      } else {
//        throw new JsonParseException("Unexpected event type.");
//      }
//
//      return new Event(eventType);
//    }
//  }
//
//  private static final Gson GSON = new GsonBuilder()
//      .registerTypeAdapter(Event.class, new EventDeserializer()).create();
//
//  @Override
//  public V1VersionedEvent read(String json) {
//    //FIX me - if
//    System.out.println("11111111111111111");
//    //FIX me
//    var foo = GSON.fromJson(json, V1VersionedEvent.class);
//    System.out.println("222222222222222222 " + foo);
//    return foo;
//  }
//
//}
