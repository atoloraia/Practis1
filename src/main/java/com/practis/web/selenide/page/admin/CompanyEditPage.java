package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyEditPage {

  private final SelenideElement headerNameElement = $("div.sc-kNzBZN.kCxzpy");
  private final SelenideElement companyNameFieldElement = $("input[name='companyName']");
}
