package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningDeleteTeamPopUp {
  private final SelenideElement warningTitle = $(".sc-hluend.kOnqWv");
  private final SelenideElement description = $(".sc-gSiHgH.krELBB");
  private final SelenideElement goBackButton = $(".sc-iAKVOt.ioplhQ.inverse");
  private final SelenideElement proceedButton = $(".sc-iAKVOt.ioplhQ.primary");
}
