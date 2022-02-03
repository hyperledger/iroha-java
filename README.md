[![](https://img.shields.io/jitpack/v/hyperledger/iroha-java.svg)](https://jitpack.io/#hyperledger/iroha-java/iroha2-dev-SNAPSHOT)

# Iroha2-java

Client library of [Iroha2](https://github.com/hyperledger/iroha2) written in Kotlin

- [x] [SDK](./modules/client) to work with Iroha2 API
- [x] [codegen](./modules/codegen) to generate models into [model](./modules/model) by Iroha2 schema
- [x] [model](./modules/model) generated models by [codegen](./modules/codegen)
- [x] [block](./modules/block) genesis classes and some generated datamodels serializers and deserializers
- [x] [testcontainers](./modules/testcontainers) wrapper for convenient integration testing with Iroha

## Usage

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    api 'com.github.hyperledger.iroha-java:client:iroha2-dev-SNAPSHOT'
    api 'com.github.hyperledger.iroha-java:model:iroha2-dev-SNAPSHOT'
    api 'com.github.hyperledger.iroha-java:block:iroha2-dev-SNAPSHOT'
    api 'com.github.hyperledger.iroha-java:testcontainers:iroha2-dev-SNAPSHOT'
}
```
[Read more](https://jitpack.io/docs/#features) to get specific javadoc/lib version(s)

## If you have any questions

Please contact the community [here](https://github.com/hyperledger/iroha#need-help) to get answers as soon as possible.
