package com.practis.selenide.company.profile.configuration;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertLicensedSeatsCompany;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertUsersCounter;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.CompanyUserLimitExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class LicensedSeatsTest {

    @BeforeEach
    void init() {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        companySettingsCompanyService().openLicensedSeatsTab();
    }

    @TestRailTest(caseId = 32178)
    @DisplayName("Company Settings: Licensed Seats: Unlimited: Check Elements")
    void checkElementsLicensedSeatsUnlimited() {
        assertLicensedSeatsCompany("Unlimited licensed seats");
    }

    @TestRailTest(caseId = 32276)
    @CompanyUserLimitExtension(2)
    @DisplayName("Company Settings: Licensed Seats: Limited: Check Elements")
    void checkElementsLicensedSeatsLimited() {
        assertLicensedSeatsCompany("licensed seats have been used");
    }

    @TestRailTest(caseId = 32274)
    @DisplayName("Company Settings: Licensed Seats: Request Limit Change")
    void requestLimitChange() {
        companySettingsCompanyService().clickOnRequestLimitChange();
    }

    @TestRailTest(caseId = 32275)
    @DisplayName("Company Settings: Licensed Seats: Manage Invitations")
    void manageInvitations() {
        companySettingsCompanyService().clickOnManageInvitations();
        assertEmptyPendingPage();
    }

    @TestRailTest(caseId = 32180)
    @PendingUserExtension(limit = 3, company = "CompanyAuto", role = 4)
    @RegisteredUserExtension(limit = 2, company = "CompanyAuto", role = 7)
    @DisplayName("Company Settings: Licensed Seats: Verify Users Counter")
    void checkUsersCounter() {
        assertUsersCounter();
    }
}
