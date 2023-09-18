package com.practis.selenide.admin.navigation.companies.CompanySettings;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.editPhotoPopUpService;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertEditLogoTabDefault;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertEditLogoTabWithLogo;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertEditLogoTabWithoutLogo;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertGuidelines;
import static com.practis.web.selenide.validator.popup.EditPopUpValidator.assertEditPhotoPopUp;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class UploadLogoTest {

    @TestRailTest(caseId = 32258)
    @DisplayName("Admin Portal: Company Settings: Logo: Check Elements")
    void checkElementsCompanyConfigLogo() {
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Logo
        companyConfigurationService().openLogoTab();

        // assert elements on Logo tab
        assertEditLogoTabDefault();
    }

    @TestRailTest(caseId = 32259)
    @DisplayName("Admin Portal: Company Settings: Logo: Upload")
    @CompanyExtension
    void companyLogoUpload(List<RestCompanyResponse> companies) {
        // assert grid row data
        final var companyGridRow =
                companyAccoutsService().searchCompany(companies.get(0).getName());
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Logo
        companyConfigurationService().openLogoTab();

        // upload company Logo
        companyConfigurationService().uploadCompanyLogoFile();

        // assert "Edit Photo" pop up and click "Save"
        assertEditPhotoPopUp();
        editPhotoPopUpService().clickSaveButton();

        // click on Camera icon
        assertEditLogoTabWithLogo();

        // deactivate company
        practisApi().deactivateCompany(companies.get(0).getName());
    }

    @TestRailTest(caseId = 32260)
    @DisplayName("Admin Portal: Company Settings: Logo: Upload Failed")
    void companyLogoUploadFailed() {
        // open Active Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Logo
        companyConfigurationService().openLogoTab();

        // upload company Logo
        companyConfigurationService().uploadCompanyInvalidLogoFile();

        // check snackbar
        snackbar().getMessage().shouldBe(exactText("The image file size must be less than 2 MB"));

        // assert no Logo
        assertEditLogoTabWithoutLogo();
    }

    @TestRailTest(caseId = 32261)
    @DisplayName("Admin Portal: Company Settings: Logo: Delete")
    void companyLogoDelete() {
        // open Active Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Logo
        companyConfigurationService().openLogoTab();

        // upload company Logo
        companyConfigurationService().uploadCompanyLogo();
        awaitElementExists(
                30,
                () -> companyConfigurationPopUp().getCompanyLogoImage().shouldBe(attribute("src")));

        // delete company Logo
        companyConfigurationService().deleteCompanyLogo();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Camera icon
        assertEditLogoTabWithoutLogo();
    }

    @Disabled
    // @TestRailTest(caseId = 32262)
    @DisplayName("Admin Portal: Company Settings: Logo: Guidelines")
    void companyLogoGuidelines() {
        // open Active Company 'Company Settings' page
        var companyGridRow = companyAccoutsService().searchCompany("CompanyAuto");
        companyGridRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // click on Logo
        companyConfigurationService().openLogoTab();

        // click on "Configure Company"
        companyCreateService().clickOnConfigureCompany();

        // open Guidelines
        companyConfigurationService().openGuidelines();

        // assert Guidelines
        switchTo().window(1);
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertGuidelines();
    }
}
