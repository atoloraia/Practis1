package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AreYouSurePopUp {

  private final SelenideElement goBackButton = $(".sc-iAKVOt.ioplhQ.inverse");
  private final SelenideElement proceedButton = $(".sc-iAKVOt.ioplhQ.primary");

}
