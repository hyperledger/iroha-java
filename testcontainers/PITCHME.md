# testcontainers-iroha

is a Java library that support JUnit tests, providing lightweight, throwaway instance of Iroha running in a Docker container.

---

@snap[north-west]
## Problem
@snapend

You want to do integration testing with Iroha, explore its API or even do acceptance testing.

---

@snap[north-west]
## Solution
@snapend

testcontainers-iroha

+++

@snap[north-west]
**Features**
@snapend

Write JUnit test with automatic Iroha lifecycle:
```java
class CoolTest {
  private IrohaContainer iroha = new IrohaContainer();

  @BeforeAll
  public void setup(){
    iroha.start();
  }

  @AfterAll
  public void cleanup(){
    iroha.stop();
  }

  @Test
  public void superTest(){
    IrohaAPI api = iroha.getApi();
    val publicKey = /*...*/;
    val testKeyPair = /*...*/;

    val tx = Transaction.builder("test@test")
      .createAccount("new", "test", publicKey)
      .sign(testKeyPair)
      .build();

    val testObs = new TestObserver<Endpoint.ToriiResponse>()
    api.transaction(tx)
      .blockingSubscribe(testObs);

    assertTrue(testObs.assertSubscribed());
    assertTrue(testObs.assertComplete());
    assertTrue(testObs.assertNoErrors());
    assertTrue(testObs.assertNoTimeout());
  }
}
```

@[2](Define IrohaContainer instance)
@[4-7](Define setup method. It starts Iroha when test set is started. `start()` is blocking.)
@[9-12](Define cleanup method. It stops all docker containers used in this test set.)
@[16](Get Iroha API, or host:port with `.getToriiAddress()`)
@[20-23](Create Protobuf transaction. Either with bindings, iroha-pure-java, or other library)
@[25](For testing purpose, you can use `TestObserver`)
@[26-27](Send transaction to Iroha, and wait until its commit (because you have `.blockingSubscribe()`))
@[29-32](Define assertions.)

+++
@snap[north-west]
**Features**
@snapend

Define custom genesis block or config.json:
```java
val tx = Transaction.builder(null, Instant.now())
          .addPeer("0.0.0.0:10001", keypair.getPublic())
          .createRole(defaultRole,
            // all permissions
            IntStream.range(0, 43)
              .boxed()
              .map(Primitive.RolePermission::forNumber)
              .collect(Collectors.toList())
          )
          .createDomain(domain, role)
          .createAccount(account, domain, defaultKeypair.getPublic())
          .sign(keypair)
          .build();

val gb = new GenesisBlockBuilder()
          .addTransaction(tx)
          .build();

val ic = new IrohaConfig().builder()
          .internal_port(10002)
          .load_delay(5000 /* ms */)
          .build();

PeerConfig config = PeerConfig.builder()
          .genesisBlock(gb)
          .irohaConfig(ic)
          .build();

IrohaContainer iroha = new IrohaContainer()
          .withPeerConfig(config);
```

@[1-13](Create any amount of transactions that you want to insert in genesis block)
@[15-17](Create GenesisBlockBuilder with given transaction(s))
@[19-22](Create config.json object)
@[24-27](Combine all configs as PeerConfig)
@[30](Set given config to IrohaContainer, start peer and use its API.)

+++

@snap[north-west]
**Features**
@snapend

Or even create a network of peers. Or, many independent networks...

```java
IrohaNetwork network = new IrohaNetwork(10 /* peers */);

IntStream.range(5, 10)
        .boxed()
        .parallel()
        .map(i -> new IrohaNetwork(i))
        .forEach(nw -> {
          List<IrohaApi> apis = nw.getApis();
          apis.parallelStream()
            .forEach(api -> api.transaction(tx));
        );
```
@[1](You also can create networks!)
@[3-6](Or even, multiple independent networks!)
@[8](This is how you can get access to API of each peer)
@[10](For example, you can send transactions in parallel to all peers in all networks)

+++
@snap[north-west]
**Features**
@snapend

- you can specify iroha and postgres versions manually
- create independent peers with `IrohaContainer`
- create independent **networks of peers** with `IrohaNetwork`
- config and keypairs are created in tmp dir on the host system, so you can read that path

+++

@snap[north-west]
**Features**
@snapend

- customize Genesis Block for single peer or network
- customize config.json for single peer, network as a whole, or for each peer in the network independently
- customize logger intercepter - you can observe Iroha logs during testing (or disable them), for each peer separately (if needed)

---
@snap[north-west]
**Quality Assurance**
@snapend
Continuous integration and integration testing

+++
@snap[north-west]
**Quality Assurance**
@snapend
Static analyzers: sonarqube, codacy, LGTM

+++

@snap[north-west]
**Quality Assurance**
@snapend

License Compliance analysis with FOSSA

+++
@snap[north-west]
**Quality Assurance**
@snapend

Code coverage is collected with codecov

+++
@snap[north-west]
**Quality Assurance**
@snapend

Peer code reviews

---

# FIN

![](https://www.lfasiallc.com/wp-content/uploads/2017/11/logo_soramitsu-1.png)
