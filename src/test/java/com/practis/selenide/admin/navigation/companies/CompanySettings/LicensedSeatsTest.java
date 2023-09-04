package com.practis.selenide.admin.navigation.companies.CompanySettings;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsService;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertLicensedSeatsTabDefault;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertLimitedUsersElement;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertLimitedUsersErrorText;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertUsersCounterUsersLimit;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

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
public class LicensedSeatsTest {

    @TestRailTest(caseId = 32170)
    @DisplayName("Admin Portal: Company Settings: Licensed Seats: Check Elements")
    @CompanyExtension
    void assertElementsLicensedSeatsTab(List<RestCompanyResponse> companies) {

        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();

        // click on "Configure Company": Licensed Seats
        companyConfigurationService().openLicensedSeatsTab();

        // assert elements on Logo tab
        assertLicensedSeatsTabDefault();

        // deactivate company
        practisApi().deactivateCompany(companies.get(0).getName());
    }

    @TestRailTest(caseId = 32171)
    @DisplayName("Companies: Company Settings: Users Limit: Update")
    @CompanyExtension
    void updateLicensedSeatsCompanySetting(List<RestCompanyResponse> companies) {
        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Licensed Seats
        companyConfigurationService().openLicensedSeatsTab();

        // change User Limit to Limited
        companySettingsService().changeUserLimitLimited();
        companySettingsService().fillLimitNumber("5");
        companySettingsService().clickOnApplyButton();

        // assert limited User Limit view
        assertLimitedUsersElement();

        // Change limit
        companySettingsService().fillLimitNumber("7");
        companySettingsService().clickOnApplyButton();
        companySettingsPage()
                .getLimitUsersTitle()
                .shouldBe(exactText("0 of 57 licensed seats have been used"));

        // deactivate company
        practisApi().deactivateCompany(companies.get(0).getName());
    }

    @TestRailTest(caseId = 32172)
    @DisplayName("Admin Portal: Company Settings: Licensed Seats: Verify Users Counter")
    void usersCounterCompanySetting() {
        // open Active Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Licensed Seats
        companyConfigurationService().openLicensedSeatsTab();

        // assert limited User Limit view
        assertUsersCounterUsersLimit();

        // Check that error text is hidden
        companySettingsService().changeUserLimitLimited();
        companySettingsService().fillLimitNumber("5");
        assertLimitedUsersErrorText();
    }
}
