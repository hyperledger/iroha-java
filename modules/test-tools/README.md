# Test tools

You Iroha test have to inherited from IrohaTest class.
As generic must be provided one of Iroha client classes: `Iroha2Client, AdminIroha2Client, Iroha2AsyncClient, AdminIroha2AsyncClient` 
(`*AsyncClient` for usage from Java)

You can override `txTimeout` parameter as well

Also provides useful annotation `@WithIroha` to isolate tests.
It uses JUnit 5 API to run Iroha2 peer in docker container with predefined state before test execution and utilize resources after

## Usage

```kotlin
class Test : IrohaTest<AdminIroha2Client>() {

    lateinit var client: AdminIroha2Client

    @Test // this annotation needed for proper test suite detecting by IDE
    @WithIroha(DefaultGenesis::class)
    fun `awesome test`(): Unit = runBlocking {
        val health = client.health()
        assert(health == 200)
    }   
}
```

