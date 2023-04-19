package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetSummaryReportPage;

public class PractisSetSummaryReportService {

    /** Click on Search icon on Teams modal. */
    public void clickOnSearchIconTeamModal() {
        practisSetSummaryReportPage().getFilterSearchIcon().get(0).click();
    }

    /** Click on clear search. */
    public void clickOnClearSearchTeamModal() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).click();
    }

    /** Select Team. */
    public void clickOnReadioButtonTeamModal() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).click();
    }
}
