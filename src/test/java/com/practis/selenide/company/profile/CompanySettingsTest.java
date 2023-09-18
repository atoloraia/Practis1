package com.practis.selenide.company.profile;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertAccountOwnerDropdownCompany;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertCompanyElementsOnCompanySettingsPage;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertUpdatedAccountOwnerFieldCompany;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertUpdatedNameCompany;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    @TestRailTest(caseId = 32273)
    @DisplayName("Company Settings: Check Elements")
    void checkElementsOnCompanySettings() {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        assertCompanyElementsOnCompanySettingsPage();
    }

    @TestRailTest(caseId = 32265)
    @DisplayName("Company Settings: Details: Update Name")
    void checkUpdateCompanyName() {
        companySelector().getCompanySelector().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        companySelector().getCompaniesUnderSelector().get(0).click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        companySettingsCompanyService().updateCompanyName("1");
        assertUpdatedNameCompany();
        companySettingsCompanyService().revertCompanyName();
    }

    @TestRailTest(caseId = 32266)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Company Settings: Details: Update Account Owner")
    void checkUpdateAccountOwner(final List<NewUserInput> user) {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        companySettingsCompanyService().openAccountOwner();
        assertAccountOwnerDropdownCompany(user);
        companySettingsCompanyService().updateAccountOwner();
        assertUpdatedAccountOwnerFieldCompany(user);
    }
}
