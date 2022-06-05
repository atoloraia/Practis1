#!/bin/bash

gradle test --no-watch-fs --info \
  --tests com.practis.selenide.company.NewChallengeTest
