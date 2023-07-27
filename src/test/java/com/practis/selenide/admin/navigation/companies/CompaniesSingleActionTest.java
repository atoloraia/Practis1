package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertSingleActionCompanies;

import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
public class CompaniesSingleActionTest {

    @Disabled
    // @TestRailTest(caseId = 23819)
    @DisplayName("Companies: Single Action: Check Elements")
    @CompanyExtension
    void checkElementsSingleActionCompanies(List<RestCompanyResponse> company) {
        companyAccoutsService().searchCompany(company.get(0).getName());
        companyAccoutsService().clickSingleAction(company.get(0).getName());

        // asser single action Companies
        assertSingleActionCompanies();
    }
}
