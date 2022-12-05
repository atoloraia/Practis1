package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.validator.company.navigation.WebAiAssessmentValidator.assertWebEmptyAiAssessmentScreen;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class WebAiAssessmentTest {

    @TestRailTest(caseId = 15689)
    @DisplayName("Check WEB Elements 'AI Assessment' screen")
    void assertElementsAiAssessmentScreen() {
        // Open 'AI Assessment' page
        navigationCompany().getAiAssessmentItem().click();

        // Assert AI Assessment Page
        assertWebEmptyAiAssessmentScreen();
    }
}
