[![](https://img.shields.io/jitpack/v/hyperledger/iroha-java.svg)](https://jitpack.io/#hyperledger/iroha-java)

# iroha-java

Client library of [Iroha](https://github.com/hyperledger/iroha) written completely in Java 8, which includes:

- [x] [SDK](./client) to work with Iroha API
- [x] async wrapper over Iroha API
- [x] [testcontainers](./testcontainers) wrapper for convenient integration testing with Iroha.
- [x] [examples in java](./client/src/test/java/jp/co/soramitsu/iroha/java/) and [tests in groovy](./client/src/test/groovy/jp/co/soramitsu/iroha/java/)

## usage

| Module         | Javadoc                                                                                       | How to Import                                                         |
|----------------|-----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| Client SDK     | [master](https://jitpack.io/com/github/hyperledger/iroha-java/client/master/javadoc/)         | `compile com.github.hyperledger.iroha-java:client:master`             |
| Testcontainers | [master](https://jitpack.io/com/github/hyperledger/iroha-java/testcontainers/master/javadoc/) | `testCompile com.github.hyperledger.iroha-java:testcontainers:master` |


[Read more](https://jitpack.io/docs/#features) to get specific javadoc/lib version(s).

## compatibility and release policy

- **master** branch of `hyperledger/iroha-java` is compatible with **master** branch of `hyperledger/iroha`
- branches `v1.0.0_*` are compatible with tagged releases of Iroha. Example: `v1.0.0_rc2`. 

## contribute

Please read [CONTRIBUTING.md](./CONTRIBUTING.md).
