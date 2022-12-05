package com.practis.selenide.admin.profile;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnAdminBottomProfileDropdown;
import static com.practis.web.selenide.validator.BottomProfileMenuValidator.assertElementsOnAdminBottomProfileMenu;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AdminBottomProfileMenuTest {

    /** Check Bottom Profile Menu - Admin Portal. */
    @TestRailTest(caseId = 9522)
    @DisplayName("Check Web Elements on Admin Bottom Profile Menu")
    void checkElementsOnAdminBottomProfileMenu() {
        assertElementsOnAdminBottomProfileMenu();

        awaitElementEnabled(20, () -> bottomProfileMenu().getUserName()).click();
        assertElementsOnAdminBottomProfileDropdown();
    }
}
