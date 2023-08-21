package com.practis.selenide.admin.create.company.configuration;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertAdministratorsTab;
import static java.lang.String.format;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanyConfigAdministratorsTest {

    private List<String> companiesToRemove;
    private NewCompanyInput inputData;

    @BeforeEach
    void beforeEach() {
        newItemSelector().create("New Company");

        inputData = getNewCompanyInput();
        inputData.setName(format(inputData.getName(), timestamp()));
        inputData.setSubdomain(format(inputData.getSubdomain(), timestamp()));

        companiesToRemove = new ArrayList<>();
        companiesToRemove.add(inputData.getName());
    }

    @TestRailTest(caseId = 32207)
    @DisplayName("Configure Company: Administrators: Default State: Check Elements")
    void checkElementsCompanyConfigAdministrators() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click "Administrators" on Company Configuration
        companyConfigurationService().openAdministratorsTab();

        // assert elements on Administrators tab
        assertAdministratorsTab();
        // TODO waiting for front end part
    }

    @TestRailTest(caseId = 32208)
    @DisplayName("Configure Company: Administrators: Invite: Check Elements")
    void checkElementsConfigureAdministratorsInvite() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click "Administrators" on Company Configuration
        companyConfigurationService().openAdministratorsTab();

        // assert elements on Administrators tab
        assertAdministratorsTab();
        // TODO waiting for front end part
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
