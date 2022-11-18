package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertCleanSearchTeamPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertNoTeamSearchResultTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsSearchAfter1CharTeamsPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertCleanSearchMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertMembersFiltersModal;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertNoSearchResultMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchAfter1CharMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchFieldOnMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchResultsOnMembersPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchFieldOnTeamPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchResultsOnTeamsPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestTeamAddMembersRequest;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTabTest {
  @TestRailTest(caseId = 15692)
  @DisplayName("Check WEB Elements 'Members' screen")
  @TeamExtension(count = 1)
  void assertElementMembersPage() {
    //Open 'Members' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamsPage().getTeamRow().get(0).click();
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Assert Training Page
    assertElementsEmptyMembersTab();

  }


  @TestRailTest(caseId = 17126)
  @DisplayName("Check WEB Elements 'Members' Filters modal")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void assertMemberFiltersModal() {
    //Open 'Training' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamPage().getTeamRowTitle().get(0).click();

    //Open Members tab
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Open Filters
    membersTab().getMembersFiltersIcon().click();

    //Assert Filters Modal
    assertMembersFiltersModal();

    //Assert Labels
    assertEmptyLabelModel();
  }
  //private NewTeamInput inputData;

  @TestRailTest(caseId = 18207)
  @DisplayName("Team, Members: Search field on Members Tab")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void searchFieldMembersScreen(final List<NewUserInput> users) {

    //Open 'Members' page
    teamPage().getTeamRowTitle().get(0).click();
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Assert Search Field
    assertSearchFieldOnMembersPage();

    //Assert no Search results
    teamsPageService().searchTeam("no results");
    assertNoSearchResultMembersPage();

    //Assert Search Results
    userService().searchUser(users.get(0).getFirstName());
    assertSearchResultsOnMembersPage();

    //Search should be performed after entering 1 character
    assertSearchAfter1CharMembersPage(users.get(0).getFirstName());


    //Assert Clear Search
    assertCleanSearchMembersPage(2);

  }

  @Test
  @TeamExtensionWithUsersAndPractisSets
  void testTest(final TeamWithChildren input) {
    System.out.println(1);
  }

}
