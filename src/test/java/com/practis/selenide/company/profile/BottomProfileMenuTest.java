package com.practis.selenide.company.profile;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnBottomProfileDropdownValidator;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnBottomProfileMenuValidator;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class BottomProfileMenuTest {

  /**
   * Check Bottom Profile Menu - Company Portal.
   */
  @Test
  @TestRailTest(caseId = 9522)
  @DisplayName("Check Web Elements on Bottom Profile Menu")
  void checkElementsOnAdminBottomProfileMenu() {
    companySelector().getCompanySelector().click();
    companySelector().getCompaniesUnderSelector().get(0).click();

    await().pollDelay(FIVE_SECONDS).until(() -> true);

    assertElementsOnBottomProfileMenuValidator();
    awaitElementEnabled(20, () -> bottomProfileMenu().getUserName()).click();
    assertElementsOnBottomProfileDropdownValidator();
  }
}
