package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.billingReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;

public class BillingReportValidator {

  /**
   * Assert elements on Billing Report page.
   */
  public static void assertElementsOnBillingReportPage() {
    billingReportPage().getReportTitle().shouldBe(visible);
    billingReportPage().getReportTitle().shouldBe(exactText("Reports"));
    billingReportPage().getReportSubtitle().shouldBe(visible);
    billingReportPage().getReportSubtitle().shouldBe(exactText("Billing Report"));
    billingReportPage().getPageDescription().shouldBe(visible);
    billingReportPage()
        .getPageDescription()
        .shouldBe(exactText("Generate a list of monthly active users on the platform."));
    billingReportPage().getBackNavigation().shouldBe(visible);

    billingReportPage().getMonthTitle().shouldBe(visible);
    billingReportPage().getMonthTitle().shouldBe(exactText("Month"));
    billingReportPage().getYearTitle().shouldBe(visible);
    billingReportPage().getYearTitle().shouldBe(exactText("2023"));
    billingReportPage().getYearPrevButton().shouldBe(visible);
    billingReportPage().getYearPrevButton().shouldBe(enabled);
    billingReportPage().getYearNextButton().shouldBe(visible);
    billingReportPage().getYearNextButton().shouldBe(disabled);
    billingReportPage().getMonthsTitles().get(0).shouldBe(visible);
    billingReportPage().getSelectedMonthView().shouldBe(visible);

    reportsPage().getGenerateReportButton().shouldBe(visible);
    reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
    reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
    reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
    reportsPage().getGenerateReportButton().shouldBe(exactText("Generate"));
    reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));

    reportsPage().getClearReportButton().shouldBe(visible);
    reportsPage().getClearReportButton().shouldBe(attribute("disabled"));
    reportsPage().getClearReportButton().shouldBe(attribute("color", "gray"));
    reportsPage().getClearReportButton().shouldBe(attribute("width", "110px"));
    reportsPage().getClearReportButton().shouldBe(exactText("Clear"));
    reportsPage().getClearReportButton().shouldBe(attribute("type", "submit"));

    reportsPage().getEmailText().shouldBe(visible);
    reportsPage().getEmailText().shouldBe(matchText("Report will be sent to"));
  }
}
