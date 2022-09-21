package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminPage {

  private final SelenideElement adminHeaderText =
      $("div[data-test='administrators-page-page-subtitle']");
  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");

  private final SelenideElement updatedTimestampText = $("span[data-test='table-timestamp-label']");
  private final SelenideElement updatedTimestampButton = $("button[data-test='table-timestamp-refresh']");

  private final SelenideElement paginationCounterText = $("div[data-test='table-paging-counter']");
  private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
  private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");

  private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

  //'Administrators' list columns
  private final SelenideElement administratorColumn = $("th[data-test='administrator-title-column']");
  private final SelenideElement emailAddressColumn = $("th[data-test='email-address-column']");
  private final SelenideElement dateCreatedColumn = $("th[data-test='date-created-column']");
  private final SelenideElement ownerColumn = $("th[data-test='owner-column']");


  //Administrator rows
  private final ElementsCollection administratorItemRow = $$("tr[data-test='administrator-item']");
  private final ElementsCollection administratorRow = $$("tr[data-test='administrator-item-title']");
  private final ElementsCollection emailAddressRow = $$("tr[data-test='administrator-item-email']");
  private final ElementsCollection dateCreatedRow = $$("tr[data-test='administrator-item-creation-date']");
  private final ElementsCollection ownerRow = $$("tr[data-test='administrator-item-creator']");

  private final ElementsCollection threeDotMenuEventRow = $$("tr[data-test='administrator-item-menu-button']");

  private final SelenideElement noAdministratorsSearchIcon = $("div[data-test='no-found-administrators-icon']");
  private final SelenideElement noAdministratorsSearchText = $("div[data-test='no-found-administrators-label']");
}
