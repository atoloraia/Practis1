package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.validator.admin.AiAssessmentValidator.assertElementsOnAiAssessmentPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AiAssessmentTest {

    /** Check Web elements on AI Assessment page. */
    @TestRailTest(caseId = 27)
    @DisplayName("Check Elements on 'AI Assessment' Page")
    void checkElementsOnAiAssessmentPage() {
        navigationAdmin().assessmentNavigationItem.click();

        assertElementsOnAiAssessmentPage();
    }
}
