package jp.co.soramitsu.iroha.testcontainers.network;

import java.security.KeyPair;
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PeerDescriptor {

  private String name;
  private String address;
  private String postgresHost;
  private KeyPair keyPair;
  private IrohaContainer container;
}
