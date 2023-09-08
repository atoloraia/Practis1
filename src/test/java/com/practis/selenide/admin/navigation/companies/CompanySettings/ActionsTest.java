package com.practis.selenide.admin.navigation.companies.CompanySettings;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyStatusService;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertRowCompanyAccounts;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertElementsOnActiveActionsPage;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertElementsOnInactiveActionsPage;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertStatusChangesCompanySettings;
import static com.practis.web.selenide.validator.selection.company.ActivateCompanyPopUpValidator.assertActivateCompanyPopUp;
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

public class ActionsTest {

    @PractisAdminTestClass
    @SelenideTestClass
    @TestRailTestClass
    public static class ActionsTestTest {

        @TestRailTest(caseId = 32263)
        @DisplayName("Admin Portal: Companies: Company Settings: Actions: Active: Check Elements")
        @CompanyExtension
        void assertElementsActiveCompanySettings(List<RestCompanyResponse> companies) {
            // open Active Company 'Company Settings' page
            var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            companyGridRow.click();

            companySettingsService().clickOnActions();

            assertElementsOnActiveActionsPage("Active");
        }

        @TestRailTest(caseId = 32264)
        @DisplayName("Admin Portal: Companies: Company Settings: Actions: Inactive: Check Elements")
        @CompanyExtension
        void assertElementsInactiveCompanySettings(List<RestCompanyResponse> companies) {
            // open Deactivated Company 'Company Settings' page
            var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            companyGridRow.click();
            practisApi().deactivateCompany(companies.get(0).getName());
            Selenide.refresh();

            // Assert Activate pop-up
            awaitFullPageLoad(10);

            companySettingsService().clickOnActions();

            assertElementsOnInactiveActionsPage("Inactive");
        }

        @TestRailTest(caseId = 23843)
        @DisplayName("Admin Portal: Companies: Company Settings: Actions: Activate")
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
            await().pollDelay(TWO_SECONDS).until(() -> true);
            assertActivateCompanyPopUp(companies.get(0).getName());
            activateCompanyPopUp().getCrossButton().click();

            // Activate the Company
            companySettingsService().activateCompany(companies.get(0).getName());
            awaitElementNotExists(10, () -> activateCompanyPopUp().getCrossButton());
            snackbar().getMessage().shouldBe(exactText("Company has been activated"));

            // assert changes on Company Settings page
            assertStatusChangesCompanySettings("Active", "Deactivate");
            // assertDeactivatedLogs("Deactivated by Automation User on");

            // view Company on Companies List
            companySettingsPage().getCrossButton().click();
            companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            assertRowCompanyAccounts(companies.get(0), companyGridRow, "Active");
        }

        @TestRailTest(caseId = 23844)
        @DisplayName("Admin Portal: Companies: Company Settings: Actions: Deactivate")
        @CompanyExtension
        void deactivateCompanySettings(List<RestCompanyResponse> companies) {
            // open Active Company 'Company Settings' page
            var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            companyGridRow.click();

            // Assert Deactivate pop-up
            companySettingsService().openDeactivateCompanyPopUp();
            await().pollDelay(TWO_SECONDS).until(() -> true);
            assertDeactivateCompanyPopUp(companies.get(0).getName());
            deactivateCompanyPopUp().getCrossButton().click();

            // Deactivate the Company
            companySettingsService().deactivateCompany(companies.get(0).getName());
            awaitElementNotExists(10, () -> deactivateCompanyPopUp().getCrossButton());
            snackbar().getMessage().shouldBe(exactText("Company has been deactivated"));

            // assert changes on Company Settings page
            assertStatusChangesCompanySettings("Inactive", "Activate");
            // assertActivatedLogs("Deactivated by Automation User on ");

            // view Company on Companies List
            companySettingsPage().getCrossButton().click();
            companyAccountsPage().getFiltersButton().click();
            companyStatusService().selectOnlyInactiveStatusApply();
            companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            assertRowCompanyAccounts(companies.get(0), companyGridRow, "Inactive");
        }
    }
}
