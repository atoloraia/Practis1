package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
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

    /** Check Web elements on Logs page. */
    @TestRailTest(caseId = 21)
    @DisplayName("Logs: Check Elements")
    void checkElementsOnLogsPage() {
        navigationAdmin().logsNavigationItem.click();

        assertElementsOnLogsPage();
    }
}
