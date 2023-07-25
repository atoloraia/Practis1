package com.practis.selenide.company.navigation.teams.team.tab.training;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertCleanSearchTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertElementsTrainingTab;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertNoSearchResultTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchAfter1CharTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchFieldOnTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchResultsOnTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertTrainingFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTabTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31778)
    @DisplayName("Team: Training Tab: Check Elements")
    void assertElementsTrainingPage() {
        // Open 'Training' page
        teamsPage().getTeamsAllMembersRow().click();

        // Assert Training Page
        assertElementsTrainingTab();
    }

    @TestRailTest(caseId = 31779)
    @DisplayName("Team: Training Screen: Filter: Check Elements")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertTrainingsFiltersModal() {
        Selenide.refresh();

        // Open 'Training' page
        teamsPage().getTeamsAllMembersRow().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        keepTrackPopUp().getGotItButton().click();

        // Open Filters
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        jsClick(teamPage().getFiltersButton());

        // Assert Filters Modal
        assertTrainingFiltersModal();

        // Assert Labels
        assertEmptyLabelModel();
    }

    @TestRailTest(caseId = 31780)
    @DisplayName("Team: Training Screen: Search")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void searchFieldTrainingScreen(final TeamWithChildren teamWithChildren) {
        Selenide.refresh();

        // Open 'Training' page
        teamPage().getTeamRowTitle().get(0).click();
        keepTrackPopUp().getGotItButton().click();

        // Assert Search Field
        assertSearchFieldOnTrainingPage();

        // Assert no Search results
        teamsPageService().searchTeam("no results");
        assertNoSearchResultTrainingPage();

        // Assert Search Results
        trainingTabService().searchTraining(teamWithChildren.getPractisSets().get(0).getName());
        assertSearchResultsOnTrainingPage();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharTrainingPage(teamWithChildren.getPractisSets().get(0).getName());

        // Assert Clear Search
        assertCleanSearchTrainingPage(1);
    }
}
