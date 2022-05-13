#!/bin/bash

docker run --rm \
  --net=chrome-net \
  -e BROWSER_URL=http://chrome:4444 \
  -e AUTOMATION_RUN_CI=true \
  -e TEST_RAIL_TEST_RUN="${TEST_RAIL_TEST_RUN}" \
  -v ./:/home/gradle/autotests \
  -w /home/gradle/autotests \
  --entrypoint ./run_tests_entrypoint.sh \
  gradle:7-jdk11
