package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewAdminInput;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class AdminCreatePage {

  private static final String VALIDATION_SELECTOR = ".sc-bTfYlY.agMza";

  private final ElementsCollection inputRowElements = $$("div[data-test='new-admin']");
  private final SelenideElement addRowLinkElement = $("a[data-test='new-admin-add']");

  private final ElementsCollection emailFieldElements = $$("input[name*='email']");
  private final ElementsCollection firstNameFieldElements = $$("input[name*='firstName']");
  private final ElementsCollection lastNameFieldElements = $$("input[name*='lastName']");
  private final ElementsCollection passwordFieldElements = $$("input[name*='password']");
  private final ElementsCollection showPasswordElements =
      $$("div[data-test='new-admin-password-show']");
  private final ElementsCollection hidePasswordElements =
      $$("div[data-test='new-admin-password-hide']");
  private final ElementsCollection deleteRowButtonElements =
      $$("div[data-test='new-admin-delete']");

  private final SelenideElement createButtonElement = $("button[type='submit']");

  public SelenideElement getPasswordValidationMessage() {
    return passwordFieldElements.get(0).parent().parent().find(VALIDATION_SELECTOR);
  }

  /**
   * Fill create Admin form.
   */
  public void fillCreateAdminForm(final NewAdminInput input, final int rowNum) {
    emailFieldElements.get(rowNum).sendKeys(input.getEmail());
    firstNameFieldElements.get(rowNum).sendKeys(input.getFirstName());
    lastNameFieldElements.get(rowNum).sendKeys(input.getLastName());
    passwordFieldElements.get(rowNum).sendKeys(input.getPassword());
  }

  public void deleteRow(final int rowNum) {
    deleteRowButtonElements.get(rowNum).click();
  }

  public void addRow() {
    addRowLinkElement.click();
  }

  public void showPassword(final int rowNum) {
    showPasswordElements.get(rowNum).click();
  }

  public void hidePassword(final int rowNum) {
    hidePasswordElements.get(rowNum).click();
  }

  @SneakyThrows
  public void clickCreate() {
    sleep(2000);
    createButtonElement.click();
  }
}
