package jp.co.soramitsu.iroha2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.soramitsu.iroha2.model.events.*;
import jp.co.soramitsu.iroha2.model.events.reject.RejectionReason;
import jp.co.soramitsu.iroha2.model.events.reject.TransactionRejectionReason;
import lombok.Data;

import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

/**
 * Listener waits for terminal status (committed or rejected).
 * Result can be obtained with getResult() method.
 */
public class TransactionTerminalStatusWebSocketListener implements Listener {

  @Data
  static class TerminalStatus {

    private boolean committed;
    private TransactionRejectionReason reason;

    public TerminalStatus(boolean committed) {
      this.committed = committed;
    }

    public TerminalStatus(boolean committed, TransactionRejectionReason reason) {
      this.committed = committed;
      this.reason = reason;
    }

  }

  CompletableFuture<TerminalStatus> result = new CompletableFuture<>();
  EntityType entityType;
  byte[] hash;

  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

  public TransactionTerminalStatusWebSocketListener(EntityType entityType, byte[] hash) {
    this.entityType = entityType;
    this.hash = hash;
  }

  @Override
  public void onOpen(WebSocket webSocket) {
    WebSocket.Listener.super.onOpen(webSocket);
  }

  @Override
  public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
    System.err.println(data);
    final V1VersionedEvent event;
    try {
      event = JSON_MAPPER.readValue(data.toString(), V1VersionedEvent.class);
    } catch (JsonProcessingException e) {
      System.out.println(e);
      return CompletableFuture.failedFuture(new RuntimeException("Could not deserialize json", e));
    }
    System.out.println("00 --> ");
    final var eventVariant = event.getContent();
    if (eventVariant instanceof Pipeline) {
      Pipeline pipeline = (Pipeline) eventVariant;
      System.out.println(10 + " --> " +  pipeline);
      if (pipeline.getEntityType() == entityType && Arrays.equals(pipeline.getHash(), hash)) {
        System.out.println(20 + " --> " +  pipeline);
        if (pipeline.getStatus() instanceof Committed) {
          result.complete(new TerminalStatus(true));
        } else if (pipeline.getStatus() instanceof Rejected) {
          result.complete(
              new TerminalStatus(false, (TransactionRejectionReason) ((Rejected)pipeline.getStatus()).getReason())
          );
        }
      }
    }

    // event received response
    webSocket.sendText("{\"version\":\"1\",\"content\":null}", true).join();
    return Listener.super.onText(webSocket, data, last);
  }

  public Future<TerminalStatus> getResult() {
    return result;
  }
}
