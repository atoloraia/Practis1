#!/bin/bash

docker stop chrome
docker rm chrome
docker network rm chrome-net
