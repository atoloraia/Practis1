package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminEditPage {

  private final SelenideElement headerNameText = $(".sc-kBzhNE.kcWpwu");
  private final SelenideElement headerNameElement = $(".sc-gJbFMZ.iMtpYR");
  private final SelenideElement roleTitle = $(".sc-cqJjjq.bsVUvl");
  private final SelenideElement nameInfoElement = $("div[data-test='user-profile-full-name']");
  private final SelenideElement emailInfoElement = $("div[data-test='user-profile-email']");

  private final ElementsCollection editUserDetailsButton = $$(".sc-cTAHMi.hKvjHs");

  private final SelenideElement firstNameFieldElement = $("input[name='firstName']");
  private final SelenideElement lastNameFieldElement = $("input[name='lastName']");
  private final SelenideElement emailFieldElement = $("input[name='email']");
  private final SelenideElement mobileFieldElement = $(".sc-jeqYYF.bUmwjZ");

  private final SelenideElement deleteButton = $(".sc-gWXaA-D.havJTT.primary");
  private final SelenideElement updateButton = $(".sc-gWXaA-D.clQFRU.primary");

}
