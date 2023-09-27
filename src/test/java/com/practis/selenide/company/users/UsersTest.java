package com.practis.selenide.company.users;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertLicensedSeatsCompany;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertLimitInfoOnUserPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoLimitInfoOnUserPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertEmptyDraftsPage;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertUsersRegisteredPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.CompanyUserLimitExtension;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersTest {

    /** Users: Check WEB Elements - Empty state. */
    @TestRailTest(caseId = 31753)
    @DisplayName("Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check Elements")
    void checkElementsUsersPage() {

        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();
        assertNoLimitInfoOnUserPage();

        usersPage().getPendingTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyPendingPage();
        assertNoLimitInfoOnUserPage();

        usersPage().getDraftTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyDraftsPage();
        assertNoLimitInfoOnUserPage();
    }

    /** Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check User Limit info. */
    @TestRailTest(caseId = 32191)
    @DisplayName(
            "Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check User Limit"
                    + " info")
    @CompanyUserLimitExtension(2)
    void checkLimitsOnUsersPage() {
        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();
        assertLimitInfoOnUserPage();
        usersService().clickOnLimitSettings();
        assertLicensedSeatsCompany("licensed seats have been used");
        companySettingsCompanyService().closeModal();

        usersPage().getPendingTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyPendingPage();
        assertLimitInfoOnUserPage();
        usersService().clickOnLimitSettings();
        assertLicensedSeatsCompany("licensed seats have been used");
        companySettingsCompanyService().closeModal();

        usersPage().getDraftTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyDraftsPage();
        assertLimitInfoOnUserPage();
        usersService().clickOnLimitSettings();
        assertLicensedSeatsCompany("licensed seats have been used");
        companySettingsCompanyService().closeModal();
    }
}
