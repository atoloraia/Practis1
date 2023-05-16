package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class BillingReportPage {

    private final SelenideElement reportTitle = $("div[data-test='billing-report-page-title']");
    private final SelenideElement reportSubtitle =
            $("div[data-test='billing-report-page-subtitle']");
    private final SelenideElement pageDescription =
            $("div[data-test='billing-report-page-description']");
    private final SelenideElement backNavigation = $("div[data-test='back-arrow-button']");

    private final SelenideElement monthTitle = $("div[data-test='month-filter-title']");
    private final SelenideElement currentYearTitle = $("div[data-test='current-year-text']");
    private final SelenideElement yearNextButton = $("div[data-test='next-year-button']");
    private final SelenideElement yearPrevButton = $("div[data-test='prev-year-button']");
    private final ElementsCollection monthsTitle = $$("div[data-test='month-item']");

    private final SelenideElement currentMonthView = $("div[data-test='current-month']");
    //    private final SelenideElement currentMonthUnderline =
    //            $("div[data-test='current-month-underline']");
    private final SelenideElement selectedMonthView = $("div[data-test='selected-month']");
}
