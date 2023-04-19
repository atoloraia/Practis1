package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetSummaryReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;

public class PractisSetSummaryReportService {

    /** Click on Search icon. */
    public void clickOnSearchIconTeamModal() {
        practisSetSummaryReportPage().getFilterSearchIcon().get(0).click();
    }

    /** Click on clear search. */
    public void clickOnClearSearchTeamModal() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).click();
    }

    /** Select Team. */
    public void clickOnRadioButtonTeamModal() {
        practisSetSummaryReportPage().getTeamRadioButtonView().get(1).click();
    }

    /** Click on Search icon on PS modal. */
    public void clickOnSearchIconPsModal() {
        practisSetSummaryReportPage().getFilterSearchIcon().get(1).click();
    }

    /** Select PS. */
    public void clickOnRadioButtonPsModal() {
        practisSetSummaryReportPage().getPractisSetRadioButton().get(0).click();
    }

    /** Click on Clear button. */
    public void clickOnClearButton() {
        reportsPage().getClearReportButton().click();
    }

    /** Click on Generate button. */
    public void clickOnGenerateButton() {
        reportsPage().getGenerateReportButton().click();
    }
}
