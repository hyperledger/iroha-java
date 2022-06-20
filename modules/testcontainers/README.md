# Test Containers for Iroha2

The `testcontainers` module contains the Docker image for a single Iroha2 peer and Iroha2 network.

## Usage

In a single peer test, [IrohaRunnerExtension](../client/src/test/kotlin/jp/co/soramitsu/iroha2/engine/IrohaRunnerExtension.kt) calls [IrohaContainer](./src/main/kotlin/jp/co/soramitsu/iroha2/testcontainers/IrohaContainer.kt) that starts Iroha2 Docker containers:

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

You can modify Iroha2 configurations with [IrohaConfig](./src/main/kotlin/jp/co/soramitsu/iroha2/testcontainers/IrohaConfig.kt).

## Examples

- [genesis.json](./src/main/resources/genesis.json)
- [config.json](./src/main/resources/config.json)

## More Tests

You can find more tests [in the `client` module](../client/src/test/kotlin/jp/co/soramitsu/iroha2).
