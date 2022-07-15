package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class PublishPractisSetPopUp {


  private final SelenideElement goBackButton = $("button.sc-jcFkyM.gNfFbi.inverse");
  private final SelenideElement publishButton = $("button.sc-iAKVOt.ioplhQ.primary");

  public void goBack() {
    goBackButton.click();
  }

  public void publish() {
    publishButton.click();
  }
}
