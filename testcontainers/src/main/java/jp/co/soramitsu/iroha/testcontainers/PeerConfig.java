package jp.co.soramitsu.iroha.testcontainers;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import iroha.protocol.BlockOuterClass;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder;
import jp.co.soramitsu.iroha.testcontainers.detail.IrohaConfig;
import jp.co.soramitsu.iroha.testcontainers.detail.LoggerConfig;
import jp.co.soramitsu.iroha.testcontainers.detail.RuntimeIOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.testcontainers.shaded.com.google.common.io.Files;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

/**
 *
 */
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PeerConfig {

  public static final String peerKeypairName = "iroha_peer_key";
  private static final ObjectMapper mapper = new ObjectMapper()
      .setSerializationInclusion(Include.NON_NULL);

  @Getter
  private final Map<String, KeyPair> keyPairMap = new HashMap<String, KeyPair>() {{
    // default keypair for a single peer
    put(peerKeypairName, GenesisBlockBuilder.defaultKeyPair);
  }};

  @Builder.Default
  @Getter
  private File dir = Files.createTempDir();

  @SneakyThrows
  public void deleteTempDir() {
    FileUtils.deleteDirectory(dir);
  }

  @Override
  @SneakyThrows
  public void finalize() {
    deleteTempDir();
  }

  @Builder.Default
  @Getter
  private IrohaConfig irohaConfig = new IrohaConfig();

  @Builder.Default
  @Getter
  private BlockOuterClass.Block genesisBlock = new GenesisBlockBuilder()
      .addDefaultTransaction()
      .build();

  @Getter
  private LoggerConfig loggerConfig;

  private void writeToFile(String filename, String data) throws IOException {
    File file = new File(dir, filename);
    PrintWriter writer = new PrintWriter(file);
    writer.write(data);
    writer.close();
  }

  private void writeKey(String filename, byte[] key) throws IOException {
    writeToFile(filename, DatatypeConverter.printHexBinary(key).toLowerCase());
  }

  private void writeJsonConfig() throws IOException {
    byte[] data = mapper.writeValueAsBytes(irohaConfig);
    writeToFile(IrohaConfig.defaultConfigFileName, new String(data, UTF_8));
  }

  private void writeGenesisBlock() throws IOException {
    String json = JsonFormat.printer().print(genesisBlock);
    writeToFile(
        GenesisBlockBuilder.defaultGenesisBlockName,
        json
    );
  }

  public void writeLoggerConfig() throws IOException {
    byte[] data = mapper.writeValueAsBytes(loggerConfig);
    writeToFile(IrohaConfig.defaultConfigFileName, new String(data, UTF_8));
  }

  public PeerConfig withPeerKeyPair(KeyPair keyPair) {
    return withKeyPair(peerKeypairName, keyPair);
  }

  public PeerConfig withKeyPair(@NonNull String name, @NonNull KeyPair keyPair) {
    keyPairMap.put(name, keyPair);

    try {
      writeKey(name + ".pub", keyPair.getPublic().getEncoded());
      writeKey(name + ".priv", keyPair.getPrivate().getEncoded());
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }

    return this;
  }

  public void save() {
    try {
      withPeerKeyPair(keyPairMap.get(peerKeypairName));
      writeJsonConfig();
      writeGenesisBlock();
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
  }
}
