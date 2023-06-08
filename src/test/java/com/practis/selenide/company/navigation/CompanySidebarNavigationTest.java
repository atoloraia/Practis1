package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.validator.company.CompanySidebarNavigationValidator.assertElementsOnCompanySidebarNavigationMenu;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class CompanySidebarNavigationTest {

    @TestRailTest(caseId = 31811)
    @DisplayName("Company: Navigation: Check Elements")
    void checkElementsOnSidebarNavigation() {

        assertElementsOnCompanySidebarNavigationMenu();
    }
}
