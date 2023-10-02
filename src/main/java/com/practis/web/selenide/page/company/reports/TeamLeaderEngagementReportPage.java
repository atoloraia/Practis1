package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamLeaderEngagementReportPage {

    private final SelenideElement reportTitle = $("div[data-test='team-leader-report-page-title']");
    private final SelenideElement reportSubtitle =
            $("div[data-test='team-leader-report-page-subtitle']");
    private final SelenideElement pageDescription =
            $("div[data-test='team-leader-report-page-description']");
    private final SelenideElement backNavigation = $("div[data-test='back-arrow-button']");

    private final SelenideElement teamsFilterTitle = $("div[data-test='teams-filter-title']");
    private final ElementsCollection teamsTitle =
            $$("div[data-test='team-filter-item-checkbox-label']");
    private final ElementsCollection teamsCheckboxButton =
            $$("div[data-test='team-filter-item-checkbox-view']");
    private final ElementsCollection teamsSelectedCheckboxButton =
            $$("input[data-test='team-filter-item-checkbox-checked']");
    private final SelenideElement teamsSelectionText =
            $("div[data-test='teams-selection-counter']");
    private final SelenideElement teamsSelectAllButton = $("span[data-test='teams-select-all']");
    private final SelenideElement teamsUnselectAllButton =
            $("span[data-test='teams-unselect-all']");

    private final SelenideElement dateRangeFilterTitle =
            $("div[data-test='date-range-filter-title']");
    private final SelenideElement dateRangeFilterSubtitle =
            $("div[data-test='date-range-filter-subtitle']");
    private final SelenideElement dateRangeClear = $("div[data-test='date-range-filter-clear']");
    private final SelenideElement calendarNavigation = $(".react-calendar__navigation");
    private final SelenideElement calendarContainer = $(".react-calendar__viewContainer");
    private final ElementsCollection calendarDates =
            $$("button[class='react-calendar__tile react-calendar__month-view__days__day']");
    private final SelenideElement todayDate =
            $(
                    "button[class='react-calendar__tile react-calendar__tile--now"
                            + " react-calendar__month-view__days__day']");
    private final ElementsCollection calendarWeekendDates =
            $$(
                    "button[class='react-calendar__tile react-calendar__month-view__days__day"
                            + " react-calendar__month-view__days__day--weekend']");
    private final ElementsCollection selectedDates =
            $$(
                    "button[class='react-calendar__tile react-calendar__tile--now"
                            + " react-calendar__tile--hasActive react-calendar__tile--range"
                            + " react-calendar__tile--rangeEnd"
                            + " react-calendar__month-view__days__day']");
    private final SelenideElement labelsSelectAllButton = $("span[data-test='labels-select-all']");
    private final SelenideElement labelsUnselectAllButton =
            $("span[data-test='labels-unselect-all']");
    private final SelenideElement labelsFilterTitle = $("div[data-test='labels-filter-title']");
    private final ElementsCollection labelsCheckboxButton =
            $$("div[data-test='label-filter-item-checkbox-view']");
    private final ElementsCollection labelsTitle = $$("div[data-test='label-filter-item-name']");
}
