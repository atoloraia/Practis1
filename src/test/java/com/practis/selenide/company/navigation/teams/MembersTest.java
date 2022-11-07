package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTest {

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

}
