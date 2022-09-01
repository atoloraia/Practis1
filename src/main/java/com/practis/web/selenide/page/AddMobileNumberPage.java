package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AddMobileNumberPage {

  private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");

  private final SelenideElement addMobileTitle = $(".sc-kkueyQ.fucpOK");
  private final SelenideElement descriptionText = $(".sc-hcZayR.gYzbcF");
  private final SelenideElement mobileField = $(".PhoneInputInput");
  private final SelenideElement mobileFieldText = $(".sc-kEBGff.fYoRHd");
  private final SelenideElement sendCodeButton = $(".sc-iAKVOt.gUcUEX.primary");
  private final SelenideElement addLaterButton = $(".sc-iAKVOt.gUcUEX.inverse");
}
