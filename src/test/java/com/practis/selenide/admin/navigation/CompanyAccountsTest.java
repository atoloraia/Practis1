package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCompanyPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class CompanyAccountsTest {

  /**
   * Check Web elements on Companies page.
   */
  @TestRailTest(caseId = 9522)
  @DisplayName("Check Web Elements on Company Accounts list")
  void checkElementsOnCompanyAccountsPage() {
    assertElementsOnCompanyPage();
  }
}
