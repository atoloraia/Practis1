package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertCleanSearchTeamPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertNoTeamSearchResultTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsSearchAfter1CharTeamsPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchFieldOnTeamPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertSearchResultsOnTeamsPage;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
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
    navigationCompany().getTeamsNavigationItem().click();

  }

  @TestRailTest(caseId = 1321)
  @DisplayName("Check WEB Elements 'Teams' screen")
  void checkElementsTeamsPage() {
    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

  @TestRailTest(caseId = 1837)
  @DisplayName("Teams: All Members: Single action: Check elements on single action")
  void viewSingleActionMenuAllMembers() {

    teamsPageService().clickSingleActionAllMembers();

    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

  @TestRailTest(caseId = 1752)
  @DisplayName("Teams: Search")
  @TeamExtension(count = 1)
  void searchFieldTeamsScreen(final List<NewTeamInput> team) {
    Selenide.refresh();

    //Assert Search Field
    assertSearchFieldOnTeamPage();

    //Assert no Search results
    teamsPageService().searchTeam("no results");
    assertNoTeamSearchResultTeamsPage();

    //Assert Search Results
    teamsPageService().searchTeam("All Members");
    assertSearchResultsOnTeamsPage();

    //Search should be performed after entering 1 character
    assertTeamsSearchAfter1CharTeamsPage(team.get(0).getName());

    //Assert Clear Search
    assertCleanSearchTeamPage(1);

  }

}