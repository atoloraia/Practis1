package com.practis.web.selenide.service.admin.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.editPhotoPopUpService;
import static com.practis.web.util.SelenideJsUtils.hackedSetValue;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.util.AwaitUtils;
import java.io.File;
import org.openqa.selenium.Keys;

public class CompanyConfigurationService {

    // Tabs

    /** Open "Licensed Seats" tab. */
    public void openLicensedSeatsTab() {
        companyConfigurationPopUp().getTabs().get(2).click();
    }

    /** Open "Logo" tab. */
    public void openLogoTab() {
        companyConfigurationPopUp().getTabs().get(1).click();
    }

    /** Open "Administrators" tab. */
    public void openAdministratorsTab() {
        companyConfigurationPopUp().getTabs().get(3).click();
    }

    /** Upload Logo file. */
    public void uploadCompanyLogoFile() {
        final var file =
                new File(
                        CompanyConfigurationService.class
                                .getResource("/configuration/web/input/logo.jpg")
                                .getFile());
        companyConfigurationPopUp().getUploadField().uploadFile(file);
    }

    /** Upload invalid Logo file. */
    public void uploadCompanyInvalidLogoFile() {
        final var file =
                new File(
                        CompanyConfigurationService.class
                                .getResource("/configuration/web/input/invalidLogo.png")
                                .getFile());
        companyConfigurationPopUp().getUploadField().uploadFile(file);
    }

    /** Upload Logo. */
    public void uploadCompanyLogo() {
        final var file =
                new File(
                        CompanyConfigurationService.class
                                .getResource("/configuration/web/input/logo.jpg")
                                .getFile());
        companyConfigurationPopUp().getUploadField().uploadFile(file);
        editPhotoPopUpService().clickSaveButton();
    }

    /** Delete Logo. */
    public void deleteCompanyLogo() {
        companyConfigurationPopUp().getDeleteCompanyLogo().click();
    }

    /** Click Guidelines. */
    public void openGuidelines() {
        companyConfigurationPopUp().getGuidelinesLink().click();
    }

    public void limitLicensedSeats(final Integer numberOfSeats) {
        AwaitUtils.awaitSoft(1, () -> false);
        // companyConfigurationPopUp().getLinks().get(1).click();
        companyConfigurationPopUp().getLimitRadiobutton().click();
        hackedSetValue(companyConfigurationPopUp().getLimitField(), numberOfSeats.toString());

        companyConfigurationPopUp().getApplyButton().click();
    }

    /** Change Company Name */
    public void adminUpdateCompanyName(String text) {
        companySettingsPage().getCompanyNameField().append(text);
        companySettingsPage().getApplyButton().click();
    }

    /** Revert Company Name */
    public void revertCompanyName() {
        companySettingsPage().getCompanyNameField().append(String.valueOf(Keys.BACK_SPACE));
        companySettingsPage().getApplyButton().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Open Account Owner */
    public void adminOpenAccountOwner() {
        companySettingsPage().getAccountOwnerClick().click();
    }

    /** Update Account Owner */
    public void adminUpdateAccountOwner() {
        companySettingsPage().getAccountOwnerValue().get(0).click();
        companySettingsPage().getApplyButton().click();
    }

    /** Update Account Owner */
    public void setNoAccountOwner() {
        companySettingsPage().getAccountOwnerClick().click();
        companySettingsPage().getAccountOwnerValue().get(0).click();
        companySettingsPage().getApplyButton().click();
    }
}
