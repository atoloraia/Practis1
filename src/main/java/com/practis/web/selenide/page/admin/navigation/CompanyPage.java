package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyPage {

  private final SelenideElement companyHeaderText =
      $("div[data-test='company-accounts-page-page-subtitle']");
  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");

  private final SelenideElement updatedTimestampText = $("span[data-test='table-timestamp-label']");
  private final SelenideElement updatedTimestampButton = $("button[data-test='table-timestamp-refresh']");

  private final SelenideElement paginationCounterText = $("div[data-test='table-paging-counter']");
  private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
  private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");

  private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

  //'Company Accounts' list columns
  private final SelenideElement companyColumn = $("th[data-test='company-title-column']");
  private final SelenideElement companyOwnerColumn = $("th[data-test='company-owner-column']");
  private final SelenideElement dateActivatedColumn = $("th[data-test='date-activated-column']");
  private final SelenideElement ownerColumn = $("th[data-test='owner-column']");

  //Company rows
  private final ElementsCollection companyItemRow = $$("tr[data-test='company-item']");
  private final ElementsCollection companyRow = $$("tr[data-test='company-item-title']");
  private final ElementsCollection companyOwnerRow = $$("tr[data-test='company-item-creator']");
  private final ElementsCollection dateActivatedRow = $$("tr[data-test='company-item-date-activated']");
  private final ElementsCollection ownerRow = $$("tr[data-test='company-item-owner']");

  private final ElementsCollection threeDotMenuEventRow = $$("tr[data-test='company-item-menu-button']");

  private final SelenideElement noCompanySearchIcon = $("div[data-test='no-found-companies-icon']");
  private final SelenideElement noCompanySearchText = $("div[data-test='no-found-companies-label']");
}
