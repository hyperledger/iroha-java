#!/usr/bin/env bash

export TAG=warchantua/iroha:1.0.0

docker build -t $TAG --compress --squash .
docker push $TAG
