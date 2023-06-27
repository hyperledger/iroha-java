#!/bin/bash

cargo +nightly-2022-12-22 build --release -Z build-std -Z build-std-features=panic_immediate_abort --target wasm32-unknown-unknown
cp ./target/wasm32-unknown-unknown/release/create_nft_for_every_user_smartcontract.wasm ../modules/client/src/test/resources/create_nft_for_every_user_smartcontract.wasm
