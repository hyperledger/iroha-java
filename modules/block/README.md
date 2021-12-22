# Block

- Genesis classes and some generated datamodels serializers and deserializers
- Serializers necessary to serialize Rust specific models to Kotlin models
- Deserializers necessary to deserialize models to json form which Iroha2 expects for [genesis.json](../testcontainers/src/main/resources/genesis.json) for example.
  You can use iroha2Mapper from [deserializers.kt](./src/main/kotlin/jp/co/soramitsu/iroha2/deserializers.kt) for it.
  ```kotlin
  import jp.co.soramitsu.iroha2.iroha2Mapper
  ```