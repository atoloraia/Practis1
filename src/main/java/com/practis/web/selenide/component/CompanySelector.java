package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitElementExists;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySelector {

  private final SelenideElement companySelector = $("div[data-test='companyDropDownToggleButton']");
  private final ElementsCollection companiesUnderSelector = $$("div[data-test*='company-']");
  private final SelenideElement adminCompanyElement = $("div[data-test*='practisAdminItemTitle']");

  public void open() {
    companySelector.click();
  }

  public SelenideElement findCompany(final String name) {
    return awaitElementExists(10,
        () -> companySelector().getCompaniesUnderSelector().find(matchText(name)));
  }
}
