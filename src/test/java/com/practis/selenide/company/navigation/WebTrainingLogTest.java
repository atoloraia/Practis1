package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.validator.company.navigation.WebTrainingLogValidator.assertWebEmptyTrainingLogScreen;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class WebTrainingLogTest {

    @TestRailTest(caseId = 15689)
    @DisplayName("Company: Navigation: Training Log: Check elements")
    void assertElementsTrainingLogScreen() {
        // Open 'Training Log' page
        navigationCompany().getTrainingLogItem().click();

        // Assert AI Assessment Page
        assertWebEmptyTrainingLogScreen();
    }
}
