#!/usr/bin/env bash

export TAG=warchantua/iroha:1.0.1

docker build -t $TAG --compress --squash .
docker push $TAG
