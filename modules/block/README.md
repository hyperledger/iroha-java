# Block

The `block` module contains:

- Genesis classes and some generated serializers and deserializers for data models
- Serializers necessary to serialise Rust-specific models to Kotlin models
- Deserializers necessary to deserialize models into JSON format, which is the format Iroha2 expects, for example, for [genesis.json](../test-tools/src/main/resources/genesis.json)

Note that you can also use `JSON_SERDE` mapper from [serde.kt](./src/main/kotlin/jp/co/soramitsu/iroha2/serde.kt) to deserialise models into JSON:

```kotlin
import jp.co.soramitsu.iroha2.iroha2Mapper
```