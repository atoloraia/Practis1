package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companiesService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsService;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertActiveStatusForCompanyRow;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertInactiveStatusForCompanyRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCompanySettingsPage;
import static com.practis.web.selenide.validator.selection.company.ActivateCompanyPopUpValidator.assertActivateCompanyPopUp;
import static com.practis.web.selenide.validator.selection.company.DeactivateCompanyPopUpValidator.assertDeactivateCompanyPopUp;
import static com.practis.web.util.PractisUtils.clickOutOfTheFormForPopup;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewCompanyInput;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    private List<String> companiesToRemove;
    private NewCompanyInput inputData;

    @BeforeEach
    void beforeEach() {
        // newItemSelector().create("New Company");

        //        inputData = getNewCompanyInput();
        //        inputData.setName(format(inputData.getName(), timestamp()));
        //        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        //
        //        companiesToRemove = new ArrayList<>();
        //        companiesToRemove.add(inputData.getName());
    }

    // TODO should be updated
    @TestRailTest(caseId = 8734)
    @DisplayName("Company Settings: Check Elements")
    @CompanyExtension()
    void checkElementsOnCompanySettings(List<RestCompanyResponse> companies) {
        // companyService().createCompany(inputData);
        // await().pollDelay(TWO_SECONDS).until(() -> true);

        // assert grid row data
        final var companyGridRow = companiesService().searchCompany(companies.get(0).getName());
        assertCompanyGridRow(companies.get(0), companyGridRow);

        // assert edit page data
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertElementsOnCompanySettingsPage();
    }

    // TODO should be updated
    // @TestRailTest(caseId = 23843)
    @DisplayName("Companies: Company Settings: Activate")
    void activateCompanySettings() {
        companyService().createCompany(inputData);
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // open 'Company Settings' page
        final var companyGridRow = companiesService().searchCompany(inputData.getName());
        companyGridRow.click();

        // click 'Activate' and assert pop-up
        companySettingsService().openActivateCompanyPopUp();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertActivateCompanyPopUp();
        clickOutOfTheFormForPopup();

        // activate
        companySettingsService().activateCompany("Company");

        // assert Company has been activated
        assertActiveStatusForCompanyRow();
    }

    // TODO should be updated
    // @TestRailTest(caseId = 23844)
    @DisplayName("Companies: Company Settings: Deactivate")
    void deactivateCompanySettings() {
        companyService().createCompany(inputData);
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // open 'Company Settings' page
        final var companyGridRow = companiesService().searchCompany(inputData.getName());
        companyGridRow.click();

        // click 'Activate' and assert pop-up
        companySettingsService().openDeactivateCompanyPopUp();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertDeactivateCompanyPopUp();
        clickOutOfTheFormForPopup();

        // activate
        companySettingsService().deactivateCompany("Company");

        // assert Company has been activated
        assertInactiveStatusForCompanyRow();
    }

    // TODO should be updated
    // @TestRailTest(caseId = 23846)
    @DisplayName("Companies: Company Settings: View Logs")
    void viewLogsCompanySettings() {
        companyService().createCompany(inputData);
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // open 'Company Settings' page
        final var companyGridRow = companiesService().searchCompany(inputData.getName());
        companyGridRow.click();

        companySettingsPage().getViewLogsButton();
    }
}
