package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.PageObjectFactory.addMobileNumberPage;

public class AddMobileNumberService {

    /** Click 'Add Later' button. */
    public void clickAddLater() {
        addMobileNumberPage().getAddLaterButton().click();
    }

    /** Add mobile number. */
    public void addMobileNumberService(String mobileNumber) {
        addMobileNumberPage().getMobileInput().append(mobileNumber);
        addMobileNumberPage().getSendCodeButton().click();
    }
}
