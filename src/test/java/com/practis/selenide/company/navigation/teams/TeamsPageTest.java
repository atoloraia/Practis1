package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.service.company.team.TeamsPageService.assertCleanSearchTeamPage;
import static com.practis.web.selenide.service.company.team.TeamsPageService.assertNoTeamSearchResultTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchFieldOnTeamPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchResultsOnTeamsPage;

import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();

  }

  @TestRailTest(caseId = 1321)
  @DisplayName("Check WEB Elements 'Teams' screen")
  void checkElementsTeamsPage() {
    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

  @TestRailTest(caseId = 1837)
  @DisplayName("Teams: All Members: Check elements on single action menu for All Members team")
  void viewSingleActionMenuAllMembers() {

    teamsPageService().clickSingleActionAllMembers();

    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

  @TestRailTest(caseId = 1752)
  @DisplayName("Teams: Search field on Teams Screen")
  @TeamExtension(count = 1)
  void searchFieldTeamsScreen(final List<RestTeamResponse> team) {
    Selenide.refresh();

    //Assert Search Field
    assertSearchFieldOnTeamPage();

    //Assert no Search results
    teamsPageService().searchTeam("no results");
    assertNoTeamSearchResultTeamsPage();

    //Assert Search Results
    teamsPageService().searchTeam("All Members");
    assertSearchResultsOnTeamsPage();

    //Assert Clear Search
    assertCleanSearchTeamPage(2, "check clean icon");

    //Search should be performed after entering 1 character
    //assertTeamsSearchAfter1CharTeamsPage(team.get(0).getName());

  }

}