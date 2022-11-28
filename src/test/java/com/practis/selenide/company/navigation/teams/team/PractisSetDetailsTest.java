package com.practis.selenide.company.navigation.teams.team;

import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertCleanSearchMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertNoSearchResultMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchAfter1CharMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchFieldOnMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchResultsOnMembersPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertCleanSearchPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertElementsPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertNoSearchResultPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertSearchAfter1CharPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertSearchFieldOnPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertSearchResultsOnPractisSetDetailsPage;
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
public class PractisSetDetailsTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();

  }

  @TestRailTest(caseId = 19409)
  @DisplayName("Check WEB Elements 'Training' screen")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void assertElementsPractisSetDetailsPage() {
    Selenide.refresh();

    //Open 'Practis Set Details' page
    teamsPage().getTeamsAllMembersRow().click();
    keepTrackPopUp().getGotItButton().click();
    trainingTab().getPractisSetValue().click();

    //Assert Team Practis Set Details Page
    assertElementsPractisSetDetailsPage();
  }

  @TestRailTest(caseId = 19410)
  @DisplayName("Check Search field on 'Practis Set Details' screen")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void searchFieldPractisSetDetailsScreen(final TeamWithChildren teamWithChildren) {

    //Open 'Practis Set Details' page
    teamsPage().getTeamsAllMembersRow().click();
    keepTrackPopUp().getGotItButton().click();
    trainingTab().getPractisSetValue().click();

    //Assert Search Field
    assertSearchFieldOnPractisSetDetailsPage();

    //Assert no Search results
    teamsPageService().searchTeam("no results");
    assertNoSearchResultPractisSetDetailsPage();

    //Assert Search Results
    userService().searchUser(teamWithChildren.getUsers().get(0).getFirstName());
    assertSearchResultsOnPractisSetDetailsPage();

    //Search should be performed after entering 1 character
    assertSearchAfter1CharPractisSetDetailsPage(teamWithChildren.getUsers().get(0).getFirstName());

    //Assert Clear Search
    assertCleanSearchPractisSetDetailsPage(2);
  }

}