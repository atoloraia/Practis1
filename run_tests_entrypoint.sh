#!/bin/bash

gradle test --no-watch-fs --info --tests com.practis.selenide.LoginTest \
  --com.practis.selenide..admin.NewAdminTest \
  --com.practis.selenide..admin.NewCompanyTest \
  --com.practis.selenide..company.NewLabelTest \
  --com.practis.selenide..company.NewTeamTest \
  --com.practis.selenide..company.NewChallengeTest