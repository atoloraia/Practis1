package com.practis.selenide.company.profile.configuration;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.editPhotoPopUpService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertLogoTab;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertLogoWithLogo;
import static com.practis.web.selenide.validator.popup.EditPopUpValidator.assertEditPhotoPopUp;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UploadLogoTest {

    @BeforeEach
    void init() {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        companySettingsCompanyService().clickOnLogo();
    }

    @TestRailTest(caseId = 32267)
    @DisplayName("Company Settings: Logo: Check Elements")
    void checkElementsCompanyConfigLogo() {

        // assert elements on Logo tab
        assertLogoTab();
    }

    @TestRailTest(caseId = 32268)
    @DisplayName("Company Settings: Logo: Upload")
    void companyConfigLogoUpload() {

        // upload company Logo
        companyConfigurationService().uploadCompanyLogoFile();

        // assert "Edit Photo" pop up and click "Save"
        assertEditPhotoPopUp();
        editPhotoPopUpService().clickSaveButton();

        // click on Camera icon
        assertLogoWithLogo();

        // delete company Logo
        companyConfigurationService().deleteCompanyLogo();
    }

    @TestRailTest(caseId = 32269)
    @DisplayName("Company Settings: Logo: Upload Failed")
    void companyConfigLogoUploadFailed() {

        // upload company Logo
        companyConfigurationService().uploadCompanyInvalidLogoFile();

        // check snackbar
        snackbar().getMessage().shouldBe(exactText("The image file size must be less than 2 MB"));

        // assert no Logo
        assertLogoTab();
    }

    @TestRailTest(caseId = 32270)
    @DisplayName("Company Settings: Logo: Delete")
    void companyConfigLogoDelete() {

        // upload company Logo
        companyConfigurationService().uploadCompanyLogo();

        // delete company Logo
        companyConfigurationService().deleteCompanyLogo();

        // click on Camera icon
        assertLogoTab();
    }
}
