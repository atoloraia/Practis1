package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySelector {

  private final SelenideElement companySelector = $("div[data-test='companyDropDownToggleButton']");
  private final ElementsCollection companiesUnderSelector = $$("div[data-test*='company_']");
  private final SelenideElement adminCompanyElement = $("div[data-test*='practisAdminItemTitle']");

  /**
   * Selects admin.
   */
  public void selectAdmin() {
    companySelector.click();
    adminCompanyElement.click();
  }

  /**
   * Selects given company.
   */
  public void selectCompany(final String company) {
    companySelector.click();
    adminCompanyElement.click();
  }
}
