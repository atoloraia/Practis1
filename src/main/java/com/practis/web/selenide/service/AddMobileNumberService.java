package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.PageObjectFactory.addMobileNumberPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class AddMobileNumberService {

    /** Click 'Add Later' button. */
    public void clickAddLater() {
        addMobileNumberPage().getAddLaterButton().click();
    }

    /** Add mobile number. */
    public void addMobileNumberService(String mobileNumber) {
        addMobileNumberPage().getMobileInput().append(mobileNumber);
        addMobileNumberPage().getSendCodeButton().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
    }
}
