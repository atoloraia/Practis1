package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminEditPage {

  private final SelenideElement headerNameElement = $("div.sc-kBzhNE.dzWNwL");
  private final SelenideElement nameInfoElement = $("div[data-test='user-profile-full-name']");
  private final SelenideElement emailInfoElement = $("div[data-test='user-profile-email']");
  private final SelenideElement firstNameFieldElement = $("input[name='firstName']");
  private final SelenideElement lastNameFieldElement = $("input[name='lastName']");
  private final SelenideElement emailFieldElement = $("input[name='email']");
}
