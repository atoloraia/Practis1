package com.practis.selenide.login;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;
import static com.practis.web.selenide.validator.LoginValidator.assertElementsLoginPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class LogOutTest {


  /**
   * Logout: Check WEB Elements .
   */
  @TestRailTest(caseId = 32188)
  @DisplayName("Logout: Check WEB Elements.")
  void checkElementsLogOut() {
    bottomProfileMenu().getUserName().click();
    bottomProfileMenu().getUserSelector().get(0).click();
    assertElementsLoginPage();
  }

}
