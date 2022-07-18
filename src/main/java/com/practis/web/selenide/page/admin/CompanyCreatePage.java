package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyCreatePage {

  private static final String VALIDATION_SELECTOR = ".sc-hxaKgE.jGPiQi";

  private final SelenideElement newPractisCompanyTitle =
      $("div[data-test='new-company-page-title']");

  private final ElementsCollection inputRow = $$("div[data-test='new-company']");
  private final SelenideElement addRowLink = $("a[data-test='new-company-item-add']");

  private final ElementsCollection companyNameField = $$("input[name*='name']");
  private final ElementsCollection companyEmailField = $$("input[name*='ownerEmail']");
  private final ElementsCollection firstNameField = $$("input[name*='ownerFirstName']");
  private final ElementsCollection lastNameField = $$("input[name*='ownerLastName']");
  private final ElementsCollection addAnotherButton = $$("a[data-test='new-company-add']");
  private final ElementsCollection deleteRowButton =
      $$("div[data-test='new-company-item-delete']");

  private final SelenideElement inviteButton = $("button[type='submit']");

}
