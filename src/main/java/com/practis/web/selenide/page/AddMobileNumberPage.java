package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AddMobileNumberPage {

  private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");

  private final SelenideElement addMobileTitle = $("div[data-test='add-mobile-number-title']");
  private final SelenideElement descriptionText =
      $("div[data-test='add-mobile-number-description']");
  private final SelenideElement mobileField = $("div[data-test='mobile-number-input']");
  private final SelenideElement mobileInput = $(".PhoneInputInput");
  private final SelenideElement mobileFieldText = $("div[data-test='mobile-number-input-text']");
  private final SelenideElement sendCodeButton = $("button[data-test='send-code-button']");
  private final SelenideElement addLaterButton = $("button[data-test='add-later-button']");
}
