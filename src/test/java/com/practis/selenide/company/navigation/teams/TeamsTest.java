package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsTest {

  @TestRailTest(caseId = 15693)
  @DisplayName("Check WEB Elements 'Teams' screen")
  void assertElementsTeamsPage() {
    //Open 'Teams' page
    navigationCompanies().getTeamsNavigationItem().click();

    //Assert Training Page
    assertElementsTeamsPage();

  }

}