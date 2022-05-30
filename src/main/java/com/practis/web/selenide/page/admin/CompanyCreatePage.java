package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitSeconds;
import static java.lang.Thread.sleep;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewAdminInput;
import com.practis.dto.NewCompanyInput;
import com.practis.web.page.admin.CompanyNewPage;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class CompanyCreatePage {

  private static final String VALIDATION_SELECTOR = ".sc-hxaKgE.jGPiQi";

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

  /**
   * Fill create Company form.
   */
  public void fillCreateCompanyForm(final NewCompanyInput input, final int rowNum) {
    companyNameFieldElements.get(rowNum).sendKeys(input.getName());
    companyEmailFieldElements.get(rowNum).sendKeys(input.getEmail());
    firstNameFieldElements.get(rowNum).sendKeys(input.getFirstName());
    lastNameFieldElements.get(rowNum).sendKeys(input.getLastName());
  }

  public void addRow() {
    addRowLinkElement.click();
  }

  public void deleteRow(final int rowNum) {
    deleteRowButtonElements.get(rowNum).click();
  }

  @SneakyThrows
  public void clickInvite() {
    sleep(2000);
    inviteButtonElement.click();
  }


}
