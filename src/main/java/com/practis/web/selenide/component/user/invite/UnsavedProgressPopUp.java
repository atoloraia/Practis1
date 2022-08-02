package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UnsavedProgressPopUp {

  private final SelenideElement unsavedProgressTitle = $(".sc-faAiPf.bbExFz");
  private final SelenideElement areYouSureText = $(".sc-edaPmx.coRKfw");
  private final SelenideElement exitButton = $(".sc-iAKVOt.fbfeDh.inverse");
  private final SelenideElement goBackButton = $(".sc-iAKVOt.ioplhQ.primary");
}
