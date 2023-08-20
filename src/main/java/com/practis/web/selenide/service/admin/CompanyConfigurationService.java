package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;

import java.io.File;

public class CompanyConfigurationService {

    // Tabs
    /** Open "Logo" tab. */
    public void openLogoTab() {
        companyConfigurationPopUp().getTabs().get(0).click();
    }

    /** Open "Administrators" tab. */
    public void openAdministratorsTab() {
        companyConfigurationPopUp().getTabs().get(3).click();
    }

    // "Logo" tab
    /** Upload Logo icon. */
    public void uploadCompanyLogo() {
        // companyConfigurationPopUp().getCompanyLogoCamera().click();
        final var file =
                new File(
                        CompanyConfigurationService.class
                                .getResource("/configuration/web/input/logo.jpg")
                                .getFile());
        companyConfigurationPopUp().getUploadField().uploadFile(file);
    }
}
