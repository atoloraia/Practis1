package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;

public class DeactivateCompanyPopUpService {

    /** Deactivate Company. */
    public void deactivateCompany(String company) {
        deactivateCompanyPopUp().getCompanyNameField().append(company);
        deactivateCompanyPopUp().getConfirmButton().click();
    }
}
