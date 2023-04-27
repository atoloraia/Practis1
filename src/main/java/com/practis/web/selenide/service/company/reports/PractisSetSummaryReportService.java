package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetSummaryReportPage;

public class PractisSetSummaryReportService {

    /** Select Team. */
    public void clickOnRadioButtonTeamModal() {
        practisSetSummaryReportPage().getTeamRadioButtonView().get(1).click();
    }

    /** Select PS. */
    public void clickOnRadioButtonPsModal() {
        practisSetSummaryReportPage().getPractisSetRadioButton().get(0).click();
    }
}
