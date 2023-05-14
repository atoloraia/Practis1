package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class BillingReportPage {

  private final SelenideElement reportTitle =
      $("div[data-test='user-activity-report-page-title']");
  private final SelenideElement reportSubtitle =
      $("div[data-test='user-activity-report-page-subtitle']");
  private final SelenideElement pageDescription =
      $("div[data-test='user-activity-report-page-description']");
  private final SelenideElement backNavigation = $("div[data-test='back-arrow-button']");

  private final SelenideElement monthTitle = $("div[data-test='teams-filter-title']");
  private final SelenideElement yearTitle =
      $("div[data-test='team-filter-item-checkbox-label']");
  private final SelenideElement yearNextButton =
      $("div[data-test='team-filter-item-checkbox-label']");
  private final SelenideElement yearPrevButton =
      $("div[data-test='team-filter-item-checkbox-label']");
  private final SelenideElement selectedMonthView =
      $("div[data-test='team-filter-item-checkbox-view']");
  private final ElementsCollection monthsTitles =
      $$("div[data-test='team-filter-item-checkbox-view']");
}