package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioEditPage {

  private final SelenideElement titleScenario = $(".sc-fotPbf.igAmbG.sc-cYvfTo.dVexzz");
  private final SelenideElement descriptionScenario = $("textarea.sc-hctthz.pSSWt");

}
