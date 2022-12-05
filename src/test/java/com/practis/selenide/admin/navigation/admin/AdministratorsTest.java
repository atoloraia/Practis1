package com.practis.selenide.admin.navigation.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.validator.admin.AdminValidator.assertElementsOnAdminPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AdministratorsTest {

    /** Check Web elements on Admins page. */
    @TestRailTest(caseId = 11671)
    @DisplayName("Check Web Elements on Administrators list")
    void checkElementsOnAdministratorsPage() {
        navigationAdmin().adminNavigationItem.click();

        assertElementsOnAdminPage();
    }
}
