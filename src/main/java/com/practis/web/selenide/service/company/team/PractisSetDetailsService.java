package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetDetailsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;

public class PractisSetDetailsService {

    /** Open Team: Trainin Tab: Practis Set Details page. */
    public void openPractiSetDetailsPage() {
        teamsPage().getTeamsAllMembersRow().click();
        keepTrackPopUp().getGotItButton().click();
        trainingTab().getPractisSetNameColumn().get(0).click();
    }

    /** Open Team: Trainin Tab: Practis Set Details page. */
    public void clickOnFilters() {
        practisSetDetailsPage().getFiltersButton().click();
    }
}
