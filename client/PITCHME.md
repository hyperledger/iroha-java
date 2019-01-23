# Iroha Pure Java
Implementation of Iroha Client library

---

@snap[north-west]
## Problem
@snapend

You want to be able to send transactions to iroha from Java client.

Iroha team provides Java bindings over Iroha C++ client library. Several problems exist with this approach.

+++

@snap[north-west]
**Bindings**
@snapend

Use JNI - you need to build C++ library for target architecture. To do so, you need to be able to build iroha (this is not fun).

+++

@snap[north-west]
**Bindings**
@snapend

Can not simply use maven to import lib, because of JNI.

+++

@snap[north-west]
**Bindings**
@snapend

Contain validation for all fields. This means that you can not create invalid transaction on client side.

This may be useful for testing purpose.

+++

@snap[north-west]
**Bindings**
@snapend

Have unstable proto API.

+++

@snap[north-west]
**Bindings**
@snapend

Are not versioned.

---

@snap[north-west]
## Solution
@snapend

Iroha exposes public GRPC API. GRPC consumes protobuf objects.

iroha-pure-java provides thin java layer over protobuf messages and GRPC API and it solves problems mentioned before.

+++

@snap[north-west]
**Features**
@snapend

No JNI, only Java8 implementation

+++
@snap[north-west]
**Features**
@snapend

Can be imported via jitpack:
https://jitpack.io/#warchant/iroha-pure-java

+++
@snap[north-west]
**Features**
@snapend

Version policy is the folloving:

- 1-1 mapping between iroha version and branch name
- master contains latest code for latest iroha version
- github releases policy
  - current: semver
  - after stable iroha release - `iroha-{version}-{iroha-pure-java-version}`

+++
@snap[north-west]
**Features**
@snapend

Validation of fields can be disabled for test purpose

+++
@snap[north-west]
**Features**
@snapend

Maintains stable java API even if protobuf messages are changed

+++
@snap[north-west]
**Features**
@snapend

RX API abstraction of Iroha GRPC API

+++
@snap[north-west]
**Features**
@snapend

Includes ed25519-sha3 crypto

---

## How do I ...

+++

@snap[north-west]
**Generate ed25519-sha3 keypair**
@snapend

```java
Ed25519Sha3 crypto = new Ed25519Sha3();
KeyPair keypair1 = crypto.generateKeypair();
KeyPair keypair2 = crypto.generateKeypair(new byte[]{/*32 bytes seed */});
```
@[2](Generate random keypair)
@[3](Generate keypair from seed)

+++
@snap[north-west]
**Create protobuf transaction**
@snapend

```java
val time = System.currentTimeMillis();
val tx = Transaction.builder("sender@account", time)
      .createAccount("account1", "domain", defaultKeypair.getPublic())
      .transferAsset("account@domain", "account1@domain", "usd#domain", "description", new BigDecimal(5))
      .sign(keypair1)
      .sign(keypair2)
      .sign(keypair3)
      .build()
```
@[2](Specify sender and current timestamp)
@[3-4](Specify a list of commands in a transaction)
@[5-7](Sign as many times as you need. Useful for MST.)
@[8](Get protobuf transaction, which is ready to be sent to Iroha)

+++

@snap[north-west]
**Create unsigned transaction**
@snapend

```java
val time = System.currentTimeMillis();
val tx = Transaction.builder("sender@account", time)
      .createAccount("account1", "domain", defaultKeypair.getPublic())
      .transferAsset("account@domain", "account1@domain", "usd#domain", "description", new BigDecimal(5))
      .build()
      .build()
```
@[5](First `build()` returns internal `Transaction` object)
@[6](Second `build()` returns protobuf Transaction)

+++

@snap[north-west]
**Get hash of transaction**
@snapend

```java
val tx = Transaction.builder("sender@account", time)
      .createAccount("account1", "domain", defaultKeypair.getPublic())
      .transferAsset("account@domain", "account1@domain", "usd#domain", "description", new BigDecimal(5))
      .build()
byte[] hash = tx.hash();
// -----------------------------------
val protoTx = tx.build();
byte[] hash = Utils.hash(protoTx);
```
@[1-5](First way is to create internal Transaction and then call `.hash()`)
@[7-8](Second way is to calculate hash from protobuf transaction)

+++

@snap[north-west]
**Send transaction to Iroha**
@snapend

```java
IrohaAPI api = new IrohaAPI("192.168.0.3", 50051);
api.transaction(tx).subscribe(new Observer<Endpoint.ToriiResponse>() {
    @Override
    void onSubscribe(@NonNull Disposable d) {}

    @Override
    void onNext(@NonNull Endpoint.ToriiResponse t) {}

    @Override
    void onError(@NonNull Throwable e) {}

    @Override
    void onComplete() {}
}))
```
@[1](First, create IrohaAPI wrapper, pass iroha peer host and port)
@[2](Send transaction. You can do `.blockingSubscribe()` or `.subscribe()` (non-blocking))
@[4](`onSubscribed` is called when transaction has been sent)
@[7](`onNext` is called whenever new transaction status is received)
@[10](`onError` is called when error occurs. `e` contains error message)
@[13](`onComplete` is called when transaction has been committed)

+++

@snap[north-west]
**Create query**
@snapend

```java
val time = System.currentTimeMillis();
val counter = 1;
val protoQuery = Query.builder("account@domain", time, counter)
        .getAccountAssetTransactions(accountId, assetId)
        .buildSigned(keyPair1);
        // or
        .buildUnsigned();
```

@[4](Specify query type)
@[5](Build signed query)
@[7](Or, build unsigned query (for testing purpose))

---
@snap[north-west]
**Quality Assurance**
@snapend
Continuous integration and integration testing

+++
@snap[north-west]
**Quality Assurance**
@snapend
Static analyzers: sonarqube, codacy, LGTM

+++

@snap[north-west]
**Quality Assurance**
@snapend

License Compliance analysis with FOSSA

+++
@snap[north-west]
**Quality Assurance**
@snapend

Code coverage is collected with codecov

+++
@snap[north-west]
**Quality Assurance**
@snapend

Peer code reviews

---

# FIN

![](https://www.lfasiallc.com/wp-content/uploads/2017/11/logo_soramitsu-1.png)
