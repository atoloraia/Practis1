package com.practis.selenide.admin.create;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertAdminWebPortal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyCreatedModal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyExistsErrorMessage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyWebPortal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCreateCompanyPage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertWorkspaceExistErrorMessage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assetEmptyFields;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Selenide;
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
class NewCompanyTest {

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

    @TestRailTest(caseId = 5243)
    @DisplayName("Company: Create: Check Elements")
    void checkElementsNewCompany() {
        assertElementsOnCreateCompanyPage();
    }

    @TestRailTest(caseId = 45)
    @DisplayName("Company: Create")
    void createCompany() {
        companyService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // assert Company Created modal
        assertCompanyCreatedModal(inputData);

        // Close company created modal
        companyService().clickOnCloseButton();

        // assert company in company selector list
        companySelector().open();
        final var companyInSelector = companySelector().findCompany(inputData.getName());
        assertTrue(companyInSelector.exists());

        // assert grid row data
        final var companyGridRow = companyAccoutsService().searchCompany(inputData.getName());
        assertCompanyGridRow(inputData, companyGridRow);
    }

    @TestRailTest(caseId = 46)
    @DisplayName("Create Company: Validation")
    void validation_UserExists() {
        companyService().fillWorkspaceUrl("t");
        companyService().fillCompanyName("");
        assetEmptyFields();

        companyService().fillCompanyName("tulaco");
        companyService().fillWorkspaceUrl("tulaco");
        companyService().clickOnCreateCompany();
        assertCompanyExistsErrorMessage();

        companyService().fillCompanyName("1");
        companyService().fillWorkspaceUrl("1");
        companyService().clickOnCreateCompany();

        assertWorkspaceExistErrorMessage();
    }

    @TestRailTest(caseId = 32203)
    @DisplayName("Company Created: Configure Company")
    void openCompanySettings() {
        companyService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // Click on Open Company Settings
        companyService().clickOnConfigureCompany();
        assertCompanyData(inputData, companySettingsPage());
    }

    @TestRailTest(caseId = 32204)
    @DisplayName("Company Created: Open Web Portal")
    void openWebPortal() {
        companyService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // Click on Open Web Portal
        companyService().clickOnOpenWebPortalButton();
        switchTo().window(1);
        Selenide.refresh();
        assertCompanyWebPortal(inputData);
        switchTo().window(0);
        Selenide.refresh();
        assertAdminWebPortal();
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
