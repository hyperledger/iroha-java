[![Build Status](https://travis-ci.org/Warchant/testcontainers-iroha.svg?branch=master)](https://travis-ci.org/Warchant/testcontainers-iroha)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ce56f4b975e1469da6b7ecfc8b98d879)](https://www.codacy.com/app/Warchant/testcontainers-iroha?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Warchant/testcontainers-iroha&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Warchant/testcontainers-iroha/branch/master/graph/badge.svg)](https://codecov.io/gh/Warchant/testcontainers-iroha)
[![](https://jitpack.io/v/Warchant/testcontainers-iroha.svg)](https://jitpack.io/#Warchant/testcontainers-iroha)
[![GitPitch](https://gitpitch.com/assets/badge.svg)](https://gitpitch.com/Warchant/testcontainers-iroha/master) 

# testcontainers-iroha
Testcontainers image for single Iroha peer and iroha network. 

# Features

- run one or multiple independent iroha **peers** (not connected to a network) with `IrohaContainer` class.
- run one or multiple independent iroha **networks** with `IrohaNetwork` class. All peers within a network are connected to a single network.
- define custom genesis block or peer config for peer/network at run-time.
- select iroha or postgres version at run-time.

# Install

https://jitpack.io/#warchant/testcontainers-iroha

# Usage

## Single Peer

```java
class SinglePeerTest {

  IrohaContainer iroha = new IrohaContainer();

  @BeforeAll
  public void beforeAll(){
    iroha.start(); // starts iroha and postgres
  }

  @AfterAll
  public void afterAll(){
    iroha.stop(); // stops iroha and postgres
  }

  @Test
  public TestWithIroha (){
    URI toriiAddr = iroha.getToriiAddress();  // iroha API host:port (torii)
    IrohaAPI api  = iroha.getApi();  // or use async Iroha API wrapper directly
    // ...
  }
}
```

`IrohaContainer` starts Postgres and Iroha docker containers with given `PeerConfig`.
There is a default config for test purposes.


### Configuration

- genesis block: see [GenesisBlockBuilder.java](https://github.com/Warchant/testcontainers-iroha/blob/master/src/main/java/jp/co/soramitsu/iroha/testcontainers/detail/GenesisBlockBuilder.java).
- json config: see [IrohaConfig.java](https://github.com/Warchant/testcontainers-iroha/blob/master/src/main/java/jp/co/soramitsu/iroha/testcontainers/detail/IrohaConfig.java).
- postgres config: see [PostgresConfig.java](https://github.com/Warchant/testcontainers-iroha/blob/master/src/main/java/jp/co/soramitsu/iroha/testcontainers/detail/PostgresConfig.java).

To change default configuration:
```java
IrohaContainer iroha = new IrohaContainer()
    .withPeerConfig( /* pass config here */ );

iroha.start();
...
iroha.stop();
```

## Network Of Peers

```java
class IrohaNetworkTest {

  IrohaNetwork iroha = new IrohaNetwork(5 /* peers */);

  // networks are completely independent
  IrohaNetwork network2 = new IrohaNetwork(5 /* peers */)
            .withNetworkName("someUniqueName");

  @BeforeAll
  public void beforeAll(){
    iroha.start(); // starts all containers
  }

  @AfterAll
  public void afterAll(){
    iroha.stop(); // stops all containers
  }

  @Test
  public TestWithIroha (){
    List<URI> toriiAddr = iroha.getToriiAddress();  // list of iroha API host:port (torii)
    List<IrohaAPI> apis = iroha.getApis();  // or list of async Iroha API wrappers, 1 per peer
    // ...
  }
}
```

### Configuration

To change default configuration:
```java
IrohaNetwork network = new IrohaNetwork(5 /* peers */);

// setup shared iroha config
network.withIrohaConfig(IrohaConfig.builder()
  .setMst_enable(true)
  .build())

// to change genesis block (peers are added automatically)
network.addTransaction(tx);

network.start();
...
network.stop();
```

# Known Issues

If you get an Exception:
```
com.github.dockerjava.api.exception.DockerException: Mounts denied
```
You are probably on MAC and you need to add `/var/folder` to docker paths:

<img src="https://i.imgur.com/K3F1qo0.png" width="400">

Please refer to https://github.com/testcontainers/testcontainers-java/issues/730

