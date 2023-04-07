This smartcontract can be built using the following commands:
```groovy
docker image rm build-rust-smart-contract:latest
docker image build -t build-rust-smart-contract .
docker container create --name test-smartcontract build-rust-smart-contract
docker container cp test-smartcontract:/test-smart-contract/target/wasm32-unknown-unknown/release/create_nft_for_every_user_smartcontract.wasm .
docker container rm test-smartcontract
mv create_nft_for_every_user_smartcontract.wasm ../modules/client/src/test/resources/create_nft_for_every_user_smartcontract.wasm
```

Or you can run build script
```groovy
./build.sh
```