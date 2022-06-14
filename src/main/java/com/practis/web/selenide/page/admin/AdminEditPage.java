package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminEditPage {

  private final SelenideElement headerNameElement = $("div.sc-hgKjYe.jKMUGV");
  private final SelenideElement nameInfoElement = $("div.sc-jefHGm.bjntlk");
  private final SelenideElement emailInfoElement = $(".sc-cqJjjq.jGWsIf");
  private final SelenideElement firstNameFieldElement = $("input[name='firstName']");
  private final SelenideElement lastNameFieldElement = $("input[name='lastName']");
  private final SelenideElement emailFieldElement = $("input[name='email']");
}
