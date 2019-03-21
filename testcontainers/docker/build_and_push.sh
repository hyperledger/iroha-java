#!/usr/bin/env bash

export TAG=warchantua/iroha:1.0.0_rc5

docker build -t $TAG --compress --squash .
docker push $TAG
