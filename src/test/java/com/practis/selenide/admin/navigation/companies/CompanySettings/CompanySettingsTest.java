package com.practis.selenide.admin.navigation.companies.CompanySettings;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAccountOwnerDropdown;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertActiveBadge;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertElementsOnCompanySettingsPage;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertInactiveBadge;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertNoAccountOwner;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertUpdatedAccountOwnerField;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    @TestRailTest(caseId = 8734)
    @DisplayName("Admin Portal: Company Settings: Check Elements")
    @CompanyExtension
    void checkElementsOnCompanySettingsActive(List<RestCompanyResponse> companies) {
        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());
        assertCompanyGridRow(companies.get(0), companyGridRow);

        // assert Active Company page data
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertElementsOnCompanySettingsPage();
        assertActiveBadge();

        // Deactivate Company and check UI
        practisApi().deactivateCompany(companies.get(0).getName());
        Selenide.refresh();
        assertElementsOnCompanySettingsPage();
        assertInactiveBadge();
    }

    @TestRailTest(caseId = 32256)
    @DisplayName("Admin Portal: Company Settings: Details: Update Name")
    @CompanyExtension
    void adminPortalUpdateCompanyName(List<RestCompanyResponse> companies) {
        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());

        // Open Company Settings
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyConfigurationService().adminUpdateCompanyName("1");

        // assert updated name
        companySettingsPage()
                .getCompanyNameField()
                .shouldNotBe(exactText(companies.get(0).getName()));

        // Deactivate Company
        practisApi().deactivateCompany(companies.get(0).getName());
    }

    @TestRailTest(caseId = 32257)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Admin Portal: Company Settings: Details: Update Account Owner")
    @CompanyExtension
    void adminPortalUpdateCompanyOwner(final List<NewUserInput> user) {
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // Assert Account Owner dropdown
        companyConfigurationService().adminOpenAccountOwner();
        assertAccountOwnerDropdown(user);

        // Set Account Owner
        companyConfigurationService().adminUpdateAccountOwner();
        assertUpdatedAccountOwnerField(user);

        // Set No Account Owner
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyConfigurationService().setNoAccountOwner();
        assertNoAccountOwner();
    }

    @Disabled
    // @TestRailTest(caseId = 23846)
    @DisplayName("Companies: Company Settings: View Logs")
    @CompanyExtension
    void viewLogsCompanySettings(List<RestCompanyResponse> companies) {
        var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();

        practisApi().deactivateCompany(companies.get(0).getName());
        practisApi().activateCompany(companies.get(0).getId());
        practisApi().deactivateCompany(companies.get(0).getName());
        practisApi().activateCompany(companies.get(0).getId());
        Selenide.refresh();

        companySettingsPage().getActionsLogs().shouldHave(CollectionCondition.size(3));
        companySettingsPage().getMoreButton().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        companySettingsPage().getActionsLogs().shouldHave(CollectionCondition.size(4));
        companySettingsPage().getLessButton().click();
    }
}
