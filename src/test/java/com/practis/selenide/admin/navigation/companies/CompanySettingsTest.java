package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsService;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertRowCompanyAccounts;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertElementsOnCompanySettingsPage;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertStatusChangesCompanySettings;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.selection.company.DeactivateCompanyPopUpValidator.assertDeactivateCompanyPopUp;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    @TestRailTest(caseId = 8734)
    @DisplayName("Company Settings: Active: Check Elements")
    @CompanyExtension
    void checkElementsOnCompanySettingsActive(List<RestCompanyResponse> companies) {
        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());
        assertCompanyGridRow(companies.get(0), companyGridRow);

        // assert Active Company page data
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertElementsOnCompanySettingsPage("Deactivate", "Created by Automation User on ");

        // Deactivate Company and check UI
        practisApi().deactivateCompany(companies.get(0).getName());
        Selenide.refresh();
        companySettingsPage().getCompanyDetailsButton().click();
        assertElementsOnCompanySettingsPage("Activate", "Deactivated by Automation User on ");
    }

    @TestRailTest(caseId = 23843)
    @DisplayName("Companies: Company Settings: Activate")
    @CompanyExtension
    void activateCompanySettings(List<RestCompanyResponse> companies) {
        // open Deactivated Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();
        practisApi().deactivateCompany(companies.get(0).getName());
        Selenide.refresh();

        // Assert Activate pop-up
        awaitFullPageLoad(10);
        companySettingsService().openActivateCompanyPopUp();
        // await().pollDelay(TWO_SECONDS).until(() -> true);
        // assertActivateCompanyPopUp(companies.get(0).getName());
        // deactivateCompanyPopUp().getCancelButton().click();

        // assert changes on Company Settings page
        assertStatusChangesCompanySettings(
                "Active", "Deactivate", "Activated by Automation User on ");

        // view Company on Companies List
        companySettingsPage().getBackButton().click();
        companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        assertRowCompanyAccounts(companies.get(0), companyGridRow, "Active");
    }

    @TestRailTest(caseId = 23844)
    @DisplayName("Companies: Company Settings: Deactivate")
    @CompanyExtension
    void deactivateCompanySettings(List<RestCompanyResponse> companies) {
        // open Active Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();

        // Assert Deactivate pop-up
        companySettingsService().openDeactivateCompanyPopUp();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertDeactivateCompanyPopUp(companies.get(0).getName());
        deactivateCompanyPopUp().getCancelButton().click();

        // Deactivate the Company
        companySettingsService().deactivateCompany(companies.get(0).getName());
        awaitElementNotExists(10, () -> deactivateCompanyPopUp().getCancelButton());

        // assert changes on Company Settings page
        assertStatusChangesCompanySettings(
                "Inactive", "Activate", "Deactivated by Automation User on ");

        // view Company on Companies List
        companySettingsPage().getBackButton().click();
        companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        assertRowCompanyAccounts(companies.get(0), companyGridRow, "Active");
    }

    @TestRailTest(caseId = 23846)
    @DisplayName("Companies: Company Settings: View Logs")
    @CompanyExtension
    void viewLogsCompanySettings(List<RestCompanyResponse> companies) {
        var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();
        companySettingsPage().getCompanyActionsButton().click();

        practisApi().deactivateCompany(companies.get(0).getName());
        practisApi().activateCompany(companies.get(0).getId());

        companySettingsPage().getViewLogsButton();
    }
}
