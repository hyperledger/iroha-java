#!/usr/bin/env bash
#ATTENTION PLEASE
#this is my image and it should be changed to official before merging!!!!
export TAG=pawlak00/my_iroha_builds:1.2.x

docker build -t $TAG --compress --squash .
docker push $TAG
