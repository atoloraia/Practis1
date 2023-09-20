package com.practis.selenide.admin.create.company.configuration;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertLicensedSeatsTabConfiguration;
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
public class CompanyConfigSeatsTest {

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

    @TestRailTest(caseId = 32234)
    @DisplayName("Configure Company: Licensed Seats: Check Elements")
    void checkElementsCompanyConfigSeats() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click on "Configure Company": Licensed Seats
        companyCreateService().clickOnConfigureCompany();
        companyConfigurationService().openLicensedSeatsTab();

        // assert elements on Logo tab
        assertLicensedSeatsTabConfiguration();
    }

    @TestRailTest(caseId = 32235)
    @DisplayName("Configure Company: Licensed Seats: Update")
    void companyConfigSeatsUpdate() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click on "Configure Company": Licensed Seats
        companyCreateService().clickOnConfigureCompany();
        companyConfigurationService().openLicensedSeatsTab();

        companyConfigurationService().limitLicensedSeats(2);
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
