package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCompanyPage;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class CompanyAccountsTest {

    /** Check Web elements on Companies page. */
    @TestRailTest(caseId = 9522)
    @DisplayName("Companies: Check Elements")
    void checkElementsOnCompanyAccountsPage() {
        assertElementsOnCompanyPage();
    }
}
