# Testcontainers-iroha

Testcontainers image for single Iroha2 peer and Iroha2 network

# Usage

## Single Peer

```java
@org.junit.jupiter.api.extension.ExtendWith(IrohaRunnerExtension::class)
class SinglePeerTest {
    
    @Test
    @WithIroha
    public someTest() {
        // ...
    }
}
```

[IrohaRunnerExtension](../client/src/test/kotlin/jp/co/soramitsu/iroha2/engine/IrohaRunnerExtension.kt) calls [IrohaContainer](./src/main/kotlin/jp/co/soramitsu/iroha2/testcontainers/IrohaContainer.kt) that starts Iroha2 docker containers. 
Also, you can modify Iroha2 config with [IrohaConfig](./src/main/kotlin/jp/co/soramitsu/iroha2/testcontainers/IrohaConfig.kt)

Example of [genesis.json](./src/main/resources/genesis.json)

More tests you can see [here](../client/src/test/kotlin/jp/co/soramitsu/iroha2)
