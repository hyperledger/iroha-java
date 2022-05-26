This smartcontract can be built using the following command:
```groovy
cargo +nightly-2022-04-20 build --release -Z build-std -Z build-std-features=panic_immediate_abort --target wasm32-unknown-unknown
```