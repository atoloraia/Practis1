package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.validator.admin.LogsValidator.assertElementsOnLogsPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class LogsTest {

  /**
   * Check Web elements on Logs page.
   */
  @TestRailTest(caseId = 21)
  @DisplayName("Check Elements on 'Logs' Page")
  void checkElementsOnLogsPage() {
    navigation().logsNavigationItem.click();

    assertElementsOnLogsPage();
  }
}