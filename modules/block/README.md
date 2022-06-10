# Block

The `block` module contains:

- Genesis classes and some generated serializers and deserializers for data models
- Serializers necessary to serialise Rust-specific models to Kotlin models
- Deserializers necessary to deserialise models into JSON format, which is the format Iroha2 expects, for example, for [genesis.json](../testcontainers/src/main/resources/genesis.json)

Note that you can also use `iroha2Mapper` from [deserializers.kt](./src/main/kotlin/jp/co/soramitsu/iroha2/deserializers.kt) <!-- The link is broken --> to deserialise models into JSON:

```kotlin
import jp.co.soramitsu.iroha2.iroha2Mapper
```