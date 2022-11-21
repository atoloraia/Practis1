package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertCleanSearchMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertNoSearchResultMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchAfter1CharMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchFieldOnMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchResultsOnMembersPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertCleanSearchTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertElementsTrainingTab;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertNoSearchResultTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchAfter1CharTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchFieldOnTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSearchResultsOnTrainingPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertTrainingFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;



@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTabTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();

  }

  @TestRailTest(caseId = 9521)
  @DisplayName("Check WEB Elements 'Training' screen")
  void assertElementsTrainingPage() {
    //Open 'Training' page
    teamsPage().getTeamsAllMembersRow().click();

    //Assert Training Page
    assertElementsTrainingTab();
  }

  @TestRailTest(caseId = 17125)
  @DisplayName("Check WEB Elements 'Training' Filters modal")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void assertTrainingsFiltersModal() {
    Selenide.refresh();

    //Open 'Training' page
    teamsPage().getTeamsAllMembersRow().click();
    keepTrackPopUp().getGotItButton().click();

    //Open Filters
    teamPage().getFiltersButton().click();

    //Assert Filters Modal
    assertTrainingFiltersModal();

    //Assert Labels
    assertEmptyLabelModel();
  }

  @TestRailTest(caseId = 18206)
  @DisplayName("Team, Members: Search field on Training Tab")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void searchFieldTrainingScreen(final TeamWithChildren teamWithChildren) {
    Selenide.refresh();

    //Open 'Training' page
    teamPage().getTeamRowTitle().get(0).click();
    keepTrackPopUp().getGotItButton().click();

    //Assert Search Field
    assertSearchFieldOnTrainingPage();

    //Assert no Search results
    teamsPageService().searchTeam("no results");
    assertNoSearchResultTrainingPage();

    //Assert Search Results
    trainingTabService().searchTraining(teamWithChildren.getPractisSets().get(0).getName());
    assertSearchResultsOnTrainingPage();

    //Search should be performed after entering 1 character
    assertSearchAfter1CharTrainingPage(teamWithChildren.getPractisSets().get(0).getName());


    //Assert Clear Search
    assertCleanSearchTrainingPage(2);

  }

}