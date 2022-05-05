#!/bin/bash

sh stop_chrome.sh
docker network create chrome-net
# https://github.com/SeleniumHQ/docker-selenium
# http://localhost:7900
docker run -d --name chrome \
  --net chrome-net \
  -p 4444:4444 \
  -p 7900:7900 \
  -e VNC_NO_PASSWORD=1 \
  --shm-size="2g" \
  seleniarm/standalone-chromium:101.0
  #selenium/standalone-chrome:4.1.4-20220427