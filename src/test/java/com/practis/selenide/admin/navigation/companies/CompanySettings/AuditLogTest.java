package com.practis.selenide.admin.navigation.companies.CompanySettings;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsService;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAuditLogActivate;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAuditLogDeactivate;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAuditLogDefaultState;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAuditLogLimitUpdatedLimited;
import static com.practis.web.selenide.validator.admin.CompanySettingsValidator.assertAuditLogLimitUpdatedUnlimited;
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

public class AuditLogTest {

    @PractisAdminTestClass
    @SelenideTestClass
    @TestRailTestClass
    public static class ActionsTestTest {

        @TestRailTest(caseId = 23846)
        @DisplayName("Admin Portal: Companies: Company Settings: Audit Log: Check Elements")
        @CompanyExtension
        void assertElementsActiveCompanySettings(List<RestCompanyResponse> companies) {
            // open Active Company 'Company Settings' page
            var companyGridRow = companyAccoutsService().searchCompany(companies.get(0).getName());
            companyGridRow.click();
            await().pollDelay(TWO_SECONDS).until(() -> true);

            // Open Audit Log and assert default state
            companySettingsService().clickOnAuditLog();
            assertAuditLogDefaultState();

            // Set limit limited and assert Audit Log
            companySettingsService().updateUserLimitLimited("10");
            companySettingsService().clickOnAuditLog();
            assertAuditLogLimitUpdatedLimited();

            // Set limit unlimited and assert Audit Log
            companySettingsService().updateUserLimitUnlimited();
            companySettingsService().clickOnAuditLog();
            assertAuditLogLimitUpdatedUnlimited();

            // Deactivate company and assert audit log
            companySettingsService().deactivateCompany(companies.get(0).getName());
            companySettingsService().clickOnAuditLog();
            assertAuditLogDeactivate();

            // Activate company and assert audit log
            companySettingsService().activateCompany(companies.get(0).getName());
            companySettingsService().clickOnAuditLog();
            assertAuditLogActivate();
        }
    }
}
