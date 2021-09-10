#!/usr/bin/env bash

export TAG=ghcr.io/hyperledger/iroha:df8b9258ff546ebf7dcdbe6ca2f52ac82f94922138649fe9fc875558b0c3e715-debug

docker build -t $TAG --compress --squash .
docker push $TAG
