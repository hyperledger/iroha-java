#!/bin/bash

cargo +nightly-2023-06-25 build --release -Zbuild-std -Zbuild-std-features=panic_immediate_abort
cp ./target/wasm32-unknown-unknown/release/iroha_java_validator.wasm ../resources/validator.wasm
