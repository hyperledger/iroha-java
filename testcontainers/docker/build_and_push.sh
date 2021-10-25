#!/usr/bin/env bash

export TAG=ghcr.io/hyperledger/iroha:1.3.0-rc.1

docker build -t $TAG --compress --squash .
docker push $TAG
