package jp.co.soramitsu.iroha2;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import jp.co.soramitsu.iroha2.TransactionTerminalStatusWebSocketListener.TerminalStatus;
import jp.co.soramitsu.iroha2.json.writer.V1SubscriptionRequestWriter;
import jp.co.soramitsu.iroha2.model.V1Transaction;
import jp.co.soramitsu.iroha2.model.events.EntityType;
import jp.co.soramitsu.iroha2.model.events.SubscriptionRequest;
import jp.co.soramitsu.iroha2.model.events.SubscriptionRequest.Pipeline;
import jp.co.soramitsu.iroha2.model.query.QueryResult;
import jp.co.soramitsu.iroha2.model.query.V1QueryResult;
import jp.co.soramitsu.iroha2.model.query.V1SignedQueryRequest;
import jp.co.soramitsu.iroha2.scale.reader.query.QueryResultReader;
import jp.co.soramitsu.iroha2.scale.reader.query.V1QueryResultReader;
import jp.co.soramitsu.iroha2.scale.reader.query.VersionedQueryResultReader;
import jp.co.soramitsu.iroha2.scale.writer.VersionedTransactionWriter;
import jp.co.soramitsu.iroha2.scale.writer.query.VersionedSignedQueryRequestWriter;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.BytesRequestContent;
import org.eclipse.jetty.client.util.FutureResponseListener;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.time.Duration;
import java.util.concurrent.Future;

public class Iroha2Api {

  private static final V1SubscriptionRequestWriter V1_SUBSCRIPTION_REQUEST_WRITER = new V1SubscriptionRequestWriter();

  URI queryUri;
  URI instructionUri;
  URI eventUri;
  HttpClient httpClient = new HttpClient();

  public Iroha2Api(String url) {
    queryUri = URI.create("http://" + url + "/query");
    instructionUri = URI.create("http://" + url + "/instruction");
    eventUri = URI.create("ws://" + url + "/events");
  }

  private byte[] send(URI uri, HttpMethod method, byte[] data) throws Exception {
    if (!httpClient.isStarted()) {
      httpClient.start();
    }

    ContentResponse response = httpClient
        .newRequest(uri)
        .method(method)
        .body(new BytesRequestContent("text/plain", data))
        .send();

    if (response.getStatus() != HttpStatus.OK_200) {
      throw new RuntimeException(
          "Get status not OK: " + response.getStatus() + " Content: " + response
              .getContentAsString());
    }

    return response.getContent();
  }

  /**
   * Send query request
   *
   * @param request - build and signed request
   * @return query result object
   */
  public QueryResult query(V1SignedQueryRequest request) throws Exception {
    ByteArrayOutputStream encoded = new ByteArrayOutputStream();
    ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
    codec.write(new VersionedSignedQueryRequestWriter(), request);

    byte[] responseContent = send(queryUri, HttpMethod.GET, encoded.toByteArray());

    ScaleCodecReader reader = new ScaleCodecReader(responseContent);
    return reader.read(new QueryResultReader());
  }

  /**
   * Sends instructions to iroha2
   *
   * @param transaction - build and signed transaction
   */
  public byte[] instruction(V1Transaction transaction) throws Exception {
    ByteArrayOutputStream encoded = new ByteArrayOutputStream();
    ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
    codec.write(new VersionedTransactionWriter(), transaction);

    send(instructionUri, HttpMethod.POST, encoded.toByteArray());

    return transaction.getTransaction().getHash();
  }

  /**
   * Sends transaction and get terminal status subscription.
   */
  public Future<TerminalStatus> instructionAsync(V1Transaction transaction)
      throws Exception {
    // subscribe to events
    byte[] hash = transaction.getTransaction().getHash();
    SubscriptionRequest subscriptionRequest = new SubscriptionRequest(
        new Pipeline(EntityType.Transaction, hash)
    );
    TransactionTerminalStatusWebSocketListener listener = new TransactionTerminalStatusWebSocketListener(
        EntityType.Transaction,
        hash
    );
    events(subscriptionRequest, listener);

    ByteArrayOutputStream encoded = new ByteArrayOutputStream();
    ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
    codec.write(new VersionedTransactionWriter(), transaction);

    if (!httpClient.isStarted()) {
      httpClient.start();
    }

    Request request = httpClient
        .newRequest(instructionUri)
        .method(HttpMethod.POST)
        .body(new BytesRequestContent("text/plain", encoded.toByteArray()));

    request.send(new FutureResponseListener(request));

    return listener.getResult();
  }

  /**
   * Subscribes to events
   *
   * @param subscriptionRequest - request
   * @param listener            - web socket messages handler
   */
  public void events(SubscriptionRequest subscriptionRequest, Listener listener) {
    WebSocket socket = java.net.http.HttpClient
        .newHttpClient()
        .newWebSocketBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .buildAsync(eventUri, listener)
        .join();

    String json = V1_SUBSCRIPTION_REQUEST_WRITER.write(subscriptionRequest);
    socket.sendText(json, true).join();
  }

  protected String bytesToJsonString(byte[] bytes) {
    if (bytes.length == 0) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < bytes.length - 1; i++) {
      sb.append(Byte.toUnsignedInt(bytes[i]));
      sb.append(", ");
    }
    sb.append(Byte.toUnsignedInt(bytes[bytes.length - 1]));
    sb.append(']');
    return sb.toString();
  }

}
