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

