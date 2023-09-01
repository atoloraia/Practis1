package com.practis.selenide.company.profile;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertAccountOwnerDropdownCompany;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertCompanyElementsOnCompanySettingsPage;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertUpdatedAccountOwnerFieldCompany;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertUpdatedNameCompany;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanySettingsTest {

    @BeforeEach
    void init() {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
    }

    @TestRailTest(caseId = 32273)
    @DisplayName("Company Settings: Check Elements")
    void checkElementsOnCompanySettings() {
        assertCompanyElementsOnCompanySettingsPage();
    }

    @TestRailTest(caseId = 32265)
    @DisplayName("Company Settings: Details: Update Name")
    void checkUpdateCompanyName() {
        companySettingsCompanyService().updateCompanyName("1");
        assertUpdatedNameCompany();
        companySettingsCompanyService().revertCompanyName();
    }

    @TestRailTest(caseId = 32266)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Company Settings: Details: Update Account Owner")
    void checkUpdateAccountOwner(final List<NewUserInput> user) {
        companySettingsCompanyService().openAccountOwner();
        assertAccountOwnerDropdownCompany(user);
        companySettingsCompanyService().updateAccountOwner();
        assertUpdatedAccountOwnerFieldCompany(user);
    }
}
