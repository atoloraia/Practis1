package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AIAssessmentPage {

  private final SelenideElement alAssessmentTitle = $("div[data-test='ai-assessment-page-subtitle']");

  private final SelenideElement updatedTimestampText = $("span[data-test='table-timestamp-label']");
  private final SelenideElement updatedTimestampButton = $("button[data-test='table-timestamp-refresh']");

  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
  private final SelenideElement searchCleanIcon = $("div[data-test='table-search-input-clear']");

  private final SelenideElement assessmentFiltersButton = $("button[data-test='filters-selector']");
  private final SelenideElement assessmentCalendarField = $("div[data-test='calendar-selector']");
  private final SelenideElement paginationCounterText = $("div[data-test='table-paging-counter']");
  private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
  private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");
  private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

  //Al Assessment list columns
  private final SelenideElement idColumn = $("th[data-test='id-column']");
  private final SelenideElement companyColumn = $("th[data-test='company-column']");
  private final SelenideElement usersColumn = $("th[data-test='users-column']");
  private final SelenideElement scenarioColumn = $("th[data-test='scenario-column']");
  private final SelenideElement repColumn = $("th[data-test='rep-column']");
  private final SelenideElement dateColumn = $("th[data-test='date-column']");
  private final SelenideElement modeColumn = $("th[data-test='mode-column']");
  private final SelenideElement accuracyColumn = $("th[data-test='accuracy-column']");
  private final SelenideElement flagColumn = $("th[data-test='flag-column']");

  //Assessment row values
  private final ElementsCollection assessmentRow = $$("tr[data-test='assessment-item']");
  private final ElementsCollection idAssessmentRow = $$("tr[data-test='assessment-item-id']");
  private final ElementsCollection companyAssessmentRow = $$("tr[data-test='assessment-item-company-title']");
  private final ElementsCollection userAssessmentRow = $$("tr[data-test='aassessment-item-user-title']");
  private final ElementsCollection scenarioAssessmentRow = $$("tr[data-test='assessment-item-scenario']");
  private final ElementsCollection repAssessmentRow = $$("tr[data-test='assessment-item-rep-count']");
  private final ElementsCollection dateAssessmentRow = $$("tr[data-test='assessment-item-creation-date']");
  private final ElementsCollection modeAssessmentRow = $$("tr[data-test='assessment-item-mode']");
  private final ElementsCollection percentageAssessmentRow = $$("a[data-test='assessment-item-accuracy-percentage']");
  private final ElementsCollection playButtonAssessmentRow = $$("a[data-test='assessment-item-accuracy-play']");
  private final ElementsCollection flagAssessmentRow = $$("a[data-test='assessment-item-flag']");

  private final SelenideElement noAssessmentSearchIcon = $("div[data-test='no-found-ai-assessment-icon']");
  private final SelenideElement noAssessmentSearchText = $("div[data-test='no-found-ai-assessment-label']");
  private final SelenideElement noAssessmentFilterIcon = $("div[data-test='no-ai-assessment-icon']");
  private final SelenideElement noAssessmentFilterText = $("div[data-test='no-ai-assessment-label']");
}
