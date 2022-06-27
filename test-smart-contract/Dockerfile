FROM rust:1.61 as builder
WORKDIR /test-smart-contract
COPY . .
RUN rustup install nightly-2022-04-20
RUN rustup default nightly-2022-04-20-x86_64-unknown-linux-gnu
RUN rustup component add rust-src --toolchain nightly-2022-04-20-x86_64-unknown-linux-gnu
RUN cargo update
RUN cargo +nightly-2022-04-20 build --release -Z build-std -Z build-std-features=panic_immediate_abort --target wasm32-unknown-unknown

FROM debian:buster-slim
COPY --from=builder /test-smart-contract /test-smart-contract
