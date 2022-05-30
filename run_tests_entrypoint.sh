#!/bin/bash

gradle test --no-watch-fs --info \
  --tests com.practis.selenide.LoginTest \
  --tests com.practis.selenide.admin.NewAdminTest \
  --tests com.practis.selenide.admin.NewCompanyTest \
  --tests com.practis.selenide.company.NewLabelTest \
  --tests com.practis.selenide.company.NewTeamTest \
  --tests com.practis.selenide.company.NewChallengeTest \
  --tests com.practis.selenide.company.NewScenarioTest