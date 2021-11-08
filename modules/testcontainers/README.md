# testcontainers-iroha

Testcontainers image for single Iroha2 peer and iroha2 network

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

`IrohaRunnerExtension` calls `IrohaContainer` that starts Iroha2 docker containers
