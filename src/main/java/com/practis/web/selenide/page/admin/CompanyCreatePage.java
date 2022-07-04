package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyCreatePage {

  private static final String VALIDATION_SELECTOR = ".sc-hxaKgE.jGPiQi";

  private final SelenideElement newPractisCompanyTitle = $(".sc-jpYdbp.qzHOj");

  private final ElementsCollection inputRowElements = $$("div[data-test='new-company']");
  private final SelenideElement addRowLinkElement = $("a[data-test='new-company-add']");

  private final ElementsCollection companyNameFieldElements = $$("input[name*='name']");
  private final ElementsCollection companyEmailFieldElements = $$("input[name*='ownerEmail']");
  private final ElementsCollection firstNameFieldElements = $$("input[name*='ownerFirstName']");
  private final ElementsCollection lastNameFieldElements = $$("input[name*='ownerLastName']");
  private final ElementsCollection addAnotherButtonElements = $$("a[data-test='new-company-add']");
  private final ElementsCollection deleteRowButtonElements =
      $$("div[data-test='new-company-delete']");

  private final SelenideElement inviteButtonElement = $("button[type='submit']");

}
