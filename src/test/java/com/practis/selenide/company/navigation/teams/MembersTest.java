package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.validator.company.MembersValidator.assertElementsMembersPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTest {

  @TestRailTest(caseId = 15692)
  @DisplayName("Check WEB Elements 'Members' screen")
  void assertElementMembersPage() {
    //Open 'Members' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamPage().getTeamRow().get(0).click();
    teamPage().getTeamRowTitle().get(0).click();
    teamPage().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Assert Training Page
    assertElementsMembersPage();

  }

}
