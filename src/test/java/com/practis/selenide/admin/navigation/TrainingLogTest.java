package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.validator.admin.TrainingLogValidator.assertElementsOnTrainingLogPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class TrainingLogTest {

    /** Check Web elements on Training Log page. */
    @TestRailTest(caseId = 27)
    @DisplayName("Check Elements on 'Training Log' Page")
    void checkElementsOnTrainingLogPage() {
        navigationAdmin().trainingLogNavigationItem.click();

        assertElementsOnTrainingLogPage();
    }
}
