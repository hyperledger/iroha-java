#!/bin/bash
git clone https://github.com/hyperledger/iroha.git
cd iroha || exit
# specific version of iroha2 to test with
git checkout iroha2-dev
docker-compose -f docker-compose.yml up -d --build --force-recreate
sleep 10
