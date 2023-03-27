package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.companiesService;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertSingleActionCompanies;

import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
public class CompaniesSingleActionTest {

    @TestRailTest(caseId = 23819)
    @DisplayName("Companies: Single Action: Check Elements")
    @CompanyExtension
    void checkElementsSingleActionCompanies(List<RestCompanyResponse> company) {
        companiesService().searchCompany(company.get(0).getName());
        companiesService().clickSingleAction(company.get(0).getName());

        // asser single action Companies
        assertSingleActionCompanies();
    }
}
