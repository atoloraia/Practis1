package com.practis.selenide.admin.navigation;

import static com.practis.web.selenide.validator.admin.SidebarNavigationValidator.assertElementsOnAdminSidebarNavigationMenu;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class AdminSidebarNavigationTest {

    @TestRailTest(caseId = 30106)
    @DisplayName("Admin: Navigation: Check Elements")
    void checkElementsOnSidebarNavigation() {

        assertElementsOnAdminSidebarNavigationMenu();
    }
}
