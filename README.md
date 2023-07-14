# Iroha2 Java

[![](https://img.shields.io/jitpack/v/hyperledger/iroha-java.svg)](https://jitpack.io/#hyperledger/iroha-java/iroha2-dev-SNAPSHOT)
[![Iroha2-java-dev ci passed](https://github.com/hyperledger/iroha-java/actions/workflows/iroha2-ci.yml/badge.svg?branch=iroha2-dev)](https://github.com/hyperledger/iroha-java/actions/workflows/iroha2-ci.yml)

The repository contains the client library of [Hyperledger Iroha2](https://github.com/hyperledger/iroha2) written in Kotlin. This includes:

- [x] [client](./modules/client) to work with Iroha2 API
- [x] [admin-client](./modules/admin-client/) with Iroha2Client extension
- [x] [codegen](./modules/codegen) to generate models into [model](./modules/model) by Iroha2 schema
- [x] [model](./modules/model) with models generated by [codegen](./modules/codegen)
- [x] [block](./modules/block) with genesis classes and some generated serializers and deserializers for data models
- [x] [test-tools](./modules/test-tools/) to create tests for Iroha2

## Iroha version

Docker image: [hyperledger/iroha2](https://hub.docker.com/r/hyperledger/iroha2/tags):lts@sha256:4b61b866e15039989e689e3403986ebb207628c36d956d2dc99078e19764e212

## Get Started

Check out [Hyperledger Iroha2 Tutorial](https://hyperledger.github.io/iroha-2-docs/) that introduces you to Iroha2 concepts and features and also provides you with a step-by-step guide for Kotlin/Java.

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
    api 'com.github.hyperledger.iroha-java:test-tools:iroha2-dev-SNAPSHOT'
}
```

To get specific javadoc/lib version(s), refer to [JitPack features](https://jitpack.io/docs/#features).

## If you have any questions

Please [contact the community](https://github.com/hyperledger/iroha#need-help) to get answers as soon as possible.
