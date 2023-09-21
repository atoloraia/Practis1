package com.practis.selenide.admin.create.company;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertAdminWebPortal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyCreateClosed;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyCreatedClosed;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyCreatedModal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyExistsErrorMessage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyWebPortal;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCreateCompanyPage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertWorkspaceExistErrorMessage;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assetEmptyFields;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        companyCreateService().closeCompanyCreateModal();
        assertCompanyCreateClosed();
    }

    @TestRailTest(caseId = 45)
    @DisplayName("Company: Create")
    void createCompany() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // assert Company Created modal
        assertCompanyCreatedModal(inputData);

        // Close company created modal
        companyCreateService().closeCompanyCreatedModal();
        assertCompanyCreatedClosed();

        // assert company in company selector list
        await().pollDelay(FIVE_SECONDS).until(() -> true);
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
        companyCreateService().fillWorkspaceUrl("t");
        companyCreateService().fillCompanyName("");
        assetEmptyFields();

        companyCreateService().fillCompanyName("tulaco");
        companyCreateService().fillWorkspaceUrl("tulaco");
        companyCreateService().clickOnCreateCompany();
        assertCompanyExistsErrorMessage();

        companyCreateService().fillCompanyName("1");
        companyCreateService().fillWorkspaceUrl("1");
        companyCreateService().clickOnCreateCompany();

        assertWorkspaceExistErrorMessage();
    }

    @TestRailTest(caseId = 32203)
    @DisplayName("Company Created: Configure Company")
    void openCompanySettings() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // Click on Open Company Settings
        companyCreateService().clickOnConfigureCompany();
        assertCompanyData(inputData);
    }

    @TestRailTest(caseId = 32204)
    @DisplayName("Company Created: Open Web Portal")
    void openWebPortal() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // Click on Open Web Portal
        companyCreateService().clickOnOpenWebPortalButton();
        switchTo().window(1);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertCompanyWebPortal(inputData);
        switchTo().window(0);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertAdminWebPortal();
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
