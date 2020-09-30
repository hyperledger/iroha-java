package jp.co.soramitsu.iroha.testcontainers.network;

import iroha.protocol.TransactionOuterClass;
import java.io.Closeable;
import java.net.URI;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import jp.co.soramitsu.iroha.java.Transaction;
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer;
import jp.co.soramitsu.iroha.testcontainers.PeerConfig;
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder;
import jp.co.soramitsu.iroha.testcontainers.detail.IrohaConfig;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import org.testcontainers.containers.FailureDetectingExternalResource;
import org.testcontainers.containers.Network;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.shaded.com.google.common.io.Files;

public class IrohaNetwork extends FailureDetectingExternalResource implements Startable, Closeable,
    AutoCloseable {

  private GenesisBlockBuilder genesisBlockBuilder;
  private IrohaConfig irohaConfig = new IrohaConfig();

  @Getter
  private List<PeerDescriptor> peers;

  private String networkName = "iroha_net_1337";
  private Network network;

  /**
   * Creates network of peers.
   *
   * @param peers number of peers in this network.
   */
  public IrohaNetwork(int peers) {
    if (peers <= 0) {
      throw new IllegalArgumentException("peers must be positive");
    }

    Ed25519Sha3 ed = new Ed25519Sha3();

    this.peers = new ArrayList<>(peers);
    this.genesisBlockBuilder = new GenesisBlockBuilder();

    // init peers
    for (int i = 0; i < peers; ++i) {
      String name = "peer" + i;
      String address = String.format("%s:%d", name, irohaConfig.getInternal_port());
      String pgAddress = String.format("%s-postgres", name);

      KeyPair kp = ed.generateKeypair();
      PeerDescriptor pd = new PeerDescriptor(
          name,
          address,
          pgAddress,
          kp,
          new IrohaContainer()
      );

      // add this peer to peer list
      this.peers.add(pd);

      // add this peer to genesis block
      genesisBlockBuilder.addTransaction(
          Transaction.builder(null)
              .addPeer(address, kp.getPublic())
              .build()
              .build()
      );
    }
  }

  /**
   * Finalizes current configuration. Should be called manually to apply latest changes.
   *
   * Useful for debugging.
   */
  public IrohaNetwork configure() {
    val gb = genesisBlockBuilder.build();

    this.peers
        .forEach(pd -> {
          // update postgres host in config
          val ic = irohaConfig.clone();
          ic.getPg_opt().setHost(pd.getPostgresHost());

          val pc = PeerConfig.builder()
              .irohaConfig(ic)
              .genesisBlock(gb)
              .dir(Files.createTempDir())
              .build()
              .withPeerKeyPair(pd.getKeyPair());

          pd.getContainer().withPeerConfig(pc);
        });

    // init network
    network = Network.builder().id(networkName).build();

    return this;
  }

  /**
   * Set network name. Allows to run multiple independent networks.
   */
  public IrohaNetwork withNetworkName(@NonNull String networkName) {
    this.networkName = networkName;
    return this;
  }

  /**
   * Setter for shared iroha config.
   *
   * Postgres address will be overwritten. Everything else can be changed.
   */
  public IrohaNetwork withIrohaConfig(@NonNull IrohaConfig config) {
    this.irohaConfig = config;
    return this;
  }

  /**
   * Add transaction to genesis block.
   *
   * Peers are added automatically.
   */
  public IrohaNetwork addTransaction(@NonNull TransactionOuterClass.Transaction tx) {
    this.genesisBlockBuilder.addTransaction(tx);
    return this;
  }

  /**
   * Add default transaction to genesis block. Useful for testing.
   */
  public IrohaNetwork addDefaultTransaction() {
    this.genesisBlockBuilder.addDefaultTransaction(false);
    return this;
  }

  /**
   * Start iroha network. All peers are started simultaneously, but method itself is synchronous,
   * which means that it is safe to call start and then access API.
   */
  public void start() {
    configure();

    peers.stream()
        .map(pd -> pd.getContainer()
            .withNetwork(network)
            .withIrohaAlias(pd.getName())
            .withPostgresAlias(pd.getPostgresHost()))
        .forEach(IrohaContainer::start);
  }

  /**
   * Stop iroha network. Destroys all containers.
   */
  public void stop() {
    peers.stream()
        .map(PeerDescriptor::getContainer)
        .forEach(IrohaContainer::stop);
  }

  /**
   * Get list of URIs for iroha peers. Host will be "localhost", but port is assigned randomly.
   */
  public List<URI> getToriiAddresses() {
    return peers.stream()
        .map(PeerDescriptor::getContainer)
        .map(IrohaContainer::getToriiAddress)
        .collect(Collectors.toList());
  }

  /**
   * Get list of async wrappers over iroha api, one for each peer.
   */
  public List<IrohaAPI> getApis() {
    return peers.stream()
        .map(PeerDescriptor::getContainer)
        .map(IrohaContainer::getApi)
        .collect(Collectors.toList());
  }

  @Override
  public void close() {
    stop();
  }
}
