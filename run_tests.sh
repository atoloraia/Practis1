#!/bin/bash

printenv;

docker run --rm \
  --net=chrome-net \
  -e BROWSER_URL=http://chrome:4444 \
  -e AUTOMATION_RUN_CI=true \
  -e TEST_RAIL_TEST_RUN="${TEST_RAIL_TEST_RUN}" \
  -e WEB_USER_ID="${WEB_USER_ID}" \
  -e WEB_USER_LOGIN="${WEB_USER_LOGIN}" \
  -e WEB_USER_PASSWORD="${WEB_USER_PASSWORD}" \
  -e WEB_APP_URL="${WEB_APP_URL}" \
  -e WEB_APP_ADMIN_URL="${WEB_APP_ADMIN_URL}" \
  -e WEB_REST_PRACTIS_URL="${WEB_REST_PRACTIS_URL}" \
  -e WEB_REST_PRACTIS_V2_URL="${WEB_REST_PRACTIS_V2_URL}" \
  -v $PWD:/home/gradle/autotests \
  -w /home/gradle/autotests \
  --entrypoint ./run_tests_entrypoint.sh \
  gradle:7-jdk11
