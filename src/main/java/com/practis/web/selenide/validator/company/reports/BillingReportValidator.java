package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.billingReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;

public class BillingReportValidator {

    /** Assert elements on Billing Report page. */
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
        billingReportPage().getCurrentYearTitle().shouldBe(visible);
        billingReportPage().getCurrentYearTitle().shouldBe(exactText("2023"));
        billingReportPage().getYearPrevButton().shouldBe(visible);
        billingReportPage().getYearPrevButton().shouldBe(enabled);
        billingReportPage().getYearNextButton().shouldBe(visible);

        billingReportPage().getMonthsTitle().get(0).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(1).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(2).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(3).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(4).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(5).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(6).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(7).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(8).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(9).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(10).shouldBe(visible);

        billingReportPage().getCurrentMonthView().shouldBe(visible);

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

    /** Assert Selected Month on Billing Report page. */
    public static void assertSelectedMonthOnBillingReportPage() {
        billingReportPage().getSelectedMonthView().shouldBe(visible);
    }

    /** Assert Selected Month on Billing Report page. */
    public static void assertCurrentMonthOnBillingReportPage() {
        billingReportPage().getCurrentMonthView().shouldBe(visible);
        billingReportPage().getSelectedMonthView().shouldBe(visible);
    }

    /** Assert Selected Month on Billing Report page. */
    public static void assertHiddenCurrentMonthOnBillingReportPage() {
        billingReportPage().getCurrentMonthView().shouldBe(hidden);
        billingReportPage().getSelectedMonthView().shouldBe(hidden);
        billingReportPage().getMonthsTitle().get(0).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(0).shouldBe(exactText("Jan"));
        billingReportPage().getMonthsTitle().get(1).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(1).shouldBe(exactText("Feb"));
        billingReportPage().getMonthsTitle().get(2).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(2).shouldBe(exactText("Mar"));
        billingReportPage().getMonthsTitle().get(3).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(3).shouldBe(exactText("Apr"));
        billingReportPage().getMonthsTitle().get(4).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(4).shouldBe(exactText("May"));
        billingReportPage().getMonthsTitle().get(5).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(5).shouldBe(exactText("Jun"));
        billingReportPage().getMonthsTitle().get(6).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(6).shouldBe(exactText("Jul"));
        billingReportPage().getMonthsTitle().get(7).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(7).shouldBe(exactText("Aug"));
        billingReportPage().getMonthsTitle().get(8).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(8).shouldBe(exactText("Sep"));
        billingReportPage().getMonthsTitle().get(9).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(9).shouldBe(exactText("Oct"));
        billingReportPage().getMonthsTitle().get(10).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(10).shouldBe(exactText("Nov"));
        billingReportPage().getMonthsTitle().get(11).shouldBe(visible);
        billingReportPage().getMonthsTitle().get(11).shouldBe(exactText("Dec"));
    }
}
