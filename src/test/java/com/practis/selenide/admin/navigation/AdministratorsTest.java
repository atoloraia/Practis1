package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.validator.AdminValidator.assertElementsOnAdminPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AdministratorsTest {

  /**
   * Check Web elements on Admins page.
   */
  @TestRailTest(caseId = 11671)
  @DisplayName("Check Web Elements on Administrators list")
  void checkElementsOnAdministratorsPage() {
    navigation().adminNavigationItem.click();

    assertElementsOnAdminPage();
  }
}
