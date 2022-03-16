# Test tools

Provides useful annotation `@WithIroha` to isolate tests.
It uses JUnit 5 API to run Iroha2 peer in docker container with predefined state before test execution and utilize resources after

## Usage

```kotlin
@ExtendWith(IrohaRunnerExtension::class)
class Test {

    lateinit var client: AdminIroha2Client

    @Test //this annotation needed for proper test suite detecting by IDE
    @WithIroha(SomeGenesis::class)
    fun `awesome test`(): Unit = runBlocking {
        val health = client.health()
        assert(health == 200)
    }   
}
```

