package com.practis.selenide.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnAdminBottomProfileDropdownValidator;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnAdminBottomProfileMenuValidator;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static org.awaitility.Awaitility.await;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AdminBottomProfileMenuTest {

  /**
   * Check Bottom Profile Menu - Admin Portal.
   */
  @Test
  @TestRailTest(caseId = 9522)
  @DisplayName("Check Web Elements on Admin Bottom Profile Menu")
  void checkElementsOnAdminBottomProfileMenu() {
    assertElementsOnAdminBottomProfileDropdownValidator();

    awaitElementEnabled(20, () -> bottomProfileMenu().getUserName()).click();
    assertElementsOnAdminBottomProfileMenuValidator();
  }
}
