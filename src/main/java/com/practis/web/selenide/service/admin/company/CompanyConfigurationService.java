package com.practis.web.selenide.service.admin.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.editPhotoPopUpService;
import static com.practis.web.util.SelenideJsUtils.hackedSetValue;

import com.practis.web.util.AwaitUtils;
import java.io.File;

public class CompanyConfigurationService {

    // Tabs

    /** Open "Logo" tab. */
    public void openLicensedSeatsTab() {
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

    /** Click Guidlines. */
    public void openGuidlines() {
        companyConfigurationPopUp().getGuidelinesLink().click();
    }

    public void limitLicensedSeats(final Integer numberOfSeats) {
        AwaitUtils.awaitSoft(1, () -> false);
        // companyConfigurationPopUp().getLinks().get(1).click();
        companyConfigurationPopUp().getLimitRadiobutton().click();
        hackedSetValue(companyConfigurationPopUp().getLimitField(), numberOfSeats.toString());

        companyConfigurationPopUp().getApplyButton().click();
    }
}
