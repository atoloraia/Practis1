package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeEditPage {

  private final SelenideElement titleChallenge = $("input.sc-fotPbf.igAmbG.sc-jvtoKZ.kSnnXw");
  private final SelenideElement descriptionChallenge = $("textarea.sc-hctthz.pSSWt");

}
