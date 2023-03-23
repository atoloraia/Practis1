package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.companiesService;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertSingleActionCompanies;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
public class CompaniesSingleActionTest {

    @TestRailTest(caseId = 23819)
    @DisplayName("Companies: Single Action: Check Elements")
    void checkElementsSingleActionCompanies() {
        companiesService().clickSingleAction("Company");

        // asser single action Companies
        assertSingleActionCompanies();
    }
}
