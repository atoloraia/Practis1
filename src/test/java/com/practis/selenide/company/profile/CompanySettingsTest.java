package com.practis.selenide.company.profile;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertCompanyElementsOnCompanySettingsPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    @TestRailTest(caseId = 32178)
    @DisplayName("Company Settings: Check Elements")
    void checkElementsOnCompanySettings() {

        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        assertCompanyElementsOnCompanySettingsPage();
    }
}
