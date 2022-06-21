package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminCreatePage {

  private static final String VALIDATION_SELECTOR = ".sc-bTfYlY.agMza";

  private final SelenideElement newPractisAdminTitle = $(".sc-cKAPaJ.bVdFio");

  private final ElementsCollection inputRowElements = $$("div[data-test='new-admin']");
  private final SelenideElement addRowLinkElement = $("a[data-test='new-admin-add']");
  private final ElementsCollection deleteRowButtonElements =
      $$("div[data-test='new-admin-delete']");

  final ElementsCollection emailFieldElements = $$("input[name*='email']");
  private final ElementsCollection firstNameFieldElements = $$("input[name*='firstName']");
  private final ElementsCollection lastNameFieldElements = $$("input[name*='lastName']");
  private final ElementsCollection passwordFieldElements = $$("input[name*='password']");
  private final SelenideElement passwordValidationError = $(".sc-kHOZQx.iFA-DON");

  private final ElementsCollection showPasswordElements =
      $$("div[data-test='new-admin-password-show']");
  private final ElementsCollection hidePasswordElements =
      $$("div[data-test='new-admin-password-hide']");

  private final SelenideElement createButtonElement = $("button[type='submit']");
}
