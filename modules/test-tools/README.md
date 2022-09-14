# Test tools

Your Iroha test have to inherit from `IrohaTest` class.
You must provide one of Iroha client classes:
- `Iroha2Client`
- `AdminIroha2Client`
- `Iroha2AsyncClient`
- `AdminIroha2AsyncClient` 
- `*AsyncClient` (for usage from Java)

You can override `txTimeout` parameter as well.

The `test-tools` module also provides useful annotation `@WithIroha` to isolate tests.
It uses JUnit 5 API to run Iroha2 peer in a Docker container with a predefined state before test execution, and it utilises resources after.

## Usage

```kotlin
class Test : IrohaTest<AdminIroha2Client>() {

    lateinit var client: AdminIroha2Client

    @Test // this annotation is needed for proper test suite detection by IDE
    @WithIroha(DefaultGenesis::class)
    fun `awesome test`(): Unit = runBlocking {
        val health = client.health()
        assert(health == 200)
    }   
}
```

# Test Containers for Iroha2

The `test-tools` module contains the Docker image for a single Iroha2 peer and Iroha2 network.

## Usage

In a single peer test, [IrohaRunnerExtension](./src/main/kotlin/jp/co/soramitsu/iroha2/testengine/IrohaRunnerExtension.kt) calls [IrohaContainer](./src/main/kotlin/jp/co/soramitsu/iroha2/testengine/IrohaContainer.kt) that starts Iroha2 Docker containers:

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

You can modify Iroha2 configurations with [IrohaConfig](./src/main/kotlin/jp/co/soramitsu/iroha2/testengine/IrohaConfig.kt).

## Examples

- [genesis.json](./src/main/resources/genesis.json)
- [config.json](./src/main/resources/config.json)

## More Tests

You can find more tests [in the `client` module](../client/src/test/kotlin/jp/co/soramitsu/iroha2).
