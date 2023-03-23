package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;

public class ActivateCompanyPopUpService {

    /** Activate Company. */
    public void activateCompany(String company) {
        activateCompanyPopUp().getCompanyNameField().append(company);
        activateCompanyPopUp().getConfirmButton().click();
    }
}
