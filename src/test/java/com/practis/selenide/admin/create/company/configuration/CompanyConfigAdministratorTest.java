package com.practis.selenide.admin.create.company.configuration;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertAdministratorsInviteModal;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertAdministratorsTab;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertCompanyOwnerToggle;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertErrorState;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanyConfigAdministratorTest {

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
        companyCreateService().clickOnConfigureCompany();

        // click "Administrators" on Company Configuration
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyConfigurationService().openAdministratorsTab();

        // assert elements on Administrators tab
        assertAdministratorsTab();
    }

    @TestRailTest(caseId = 32208)
    @DisplayName("Configure Company: Administrators: Invite: Check Elements")
    void checkElementsConfigureAdministratorsInvite() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());
        companyCreateService().clickOnConfigureCompany();

        // Open Invite Admins modal
        companyConfigurationService().clickOnInvite();

        // assert Invite Admins modal
        assertAdministratorsInviteModal();
        assertCompanyOwnerToggle();
    }

    @TestRailTest(caseId = 32241)
    @DisplayName("Configure Company: Administrators: Invite: Validation")
    void companyConfigAdministratorsValidation() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());
        companyCreateService().clickOnConfigureCompany();

        // Fill fields on Invite Admins modal
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyConfigurationService().clickOnInvite();
        companyConfigurationService()
                .fillFields("TestFirst Name", "TestLastName", "atoloraia@tula.co");
        companyConfigurationService().clickOnSend();

        // assert error state
        assertErrorState();
        assertCompanyOwnerToggle();
    }

    @TestRailTest(caseId = 32237)
    @DisplayName("Configure Company: Administrators: Invite Admin")
    void companyConfigAdministratorsInviteAdmin() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());
        companyCreateService().clickOnConfigureCompany();

        // Fill fields on Invite Admins modal
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyConfigurationService().clickOnInvite();
        companyConfigurationService()
                .fillFields("TestFirst Name", "TestLastName", "atoloraia@tula.co");
        companyConfigurationService().clickOnSend();

        // assert error state
        assertErrorState();
        assertCompanyOwnerToggle();
    }

    @Disabled
    // @TestRailTest(caseId = 32238)
    @DisplayName("Configure Company: Administrators: Invite Company Owner")
    void companyConfigAdministratorsInviteCompanyowner() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click "Administrators" on Company Configuration
        companyConfigurationService().openAdministratorsTab();

        // assert elements on Administrators tab
        assertAdministratorsTab();
    }

    @Disabled
    // @TestRailTest(caseId = 32239)
    @DisplayName("Configure Company: Administrators: Check Elements")
    void companyConfigAdministratorsCheckElements() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click "Administrators" on Company Configuration
        companyConfigurationService().openAdministratorsTab();

        // assert elements on Administrators tab
        assertAdministratorsTab();
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
