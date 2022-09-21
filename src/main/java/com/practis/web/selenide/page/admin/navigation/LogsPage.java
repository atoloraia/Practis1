package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LogsPage {

  private final SelenideElement logsTitle = $("div[data-test='events-log-page-subtitle']");

  private final SelenideElement updatedTimestampText = $("span[data-test='table-timestamp-label']");
  private final SelenideElement updateButton = $("button[data-test='table-timestamp-refresh']");

  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
  private final SelenideElement searchCleanIcon = $("div[data-test='table-search-input-clear']");

  private final SelenideElement filtersButton = $("button[data-test='filters-selector']");
  private final SelenideElement calendarField = $("div[data-test='calendar-selector']");
  private final SelenideElement paginationCounterText = $("div[data-test='table-paging-counter']");
  private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
  private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");
  private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

  //'Events Log' list columns
  private final SelenideElement eventsColumn = $("th[data-test='event-title-column']");
  private final SelenideElement companyColumn = $("th[data-test='company-column']");
  private final SelenideElement creatorColumn = $("th[data-test='creator-column']");
  private final SelenideElement roleColumn = $("th[data-test='role-column']");
  private final SelenideElement dateCreatedColumn = $("th[data-test='date-created-column']");
  private final SelenideElement detailsColumn = $("th[data-test='details-column']");

  //Event rows
  private final ElementsCollection eventRow = $$("tr[data-test='event-item']");
  private final ElementsCollection eventsRow = $$("tr[data-test='event-item-title']");
  private final ElementsCollection companyEventRow = $$("tr[data-test='event-item-company-title']");
  private final ElementsCollection creatorEventRow = $$("tr[data-test='event-item-creator']");
  private final ElementsCollection roleEventRow = $$("tr[data-test='role-column']");
  private final ElementsCollection dateCreatedEventRow = $$("tr[data-test='date-created-column']");
  private final ElementsCollection detailsEventRow = $$("tr[data-test='details-column']");
  private final ElementsCollection threeDotMenuEventRow =
      $$("tr[data-test='event-item-menu-button']");

  private final SelenideElement noEventsSearchIcon = $("div[data-test='no-found-events-icon']");
  private final SelenideElement noEventsSearchText = $("div[data-test='no-found-events-label']");
  private final SelenideElement noEventsFilterIcon = $("div[data-test='no-filtered-events-icon']");
  private final SelenideElement noEventsFilterText = $("div[data-test='no-filtered-events-label']");
}
