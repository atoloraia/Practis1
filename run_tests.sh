#!/bin/bash

docker run -u gradle \
  --net=chrome-net \
  -e BROWSER_URL=http://chrome:4444 \
  -v $PWD:/home/gradle/autotests \
  -w /home/gradle/autotests \
  --entrypoint ./run_tests_entrypoint.sh \
  gradle:7-jdk11
