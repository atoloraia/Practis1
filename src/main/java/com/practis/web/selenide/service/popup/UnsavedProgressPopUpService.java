package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.unsavedProgressPopUp;

public class UnsavedProgressPopUpService {

    /** Click "Exit without Saving" button. */
    public void clickExitWithoutSavingButton() {
        unsavedProgressPopUp().getExitButton().click();
    }

    /** Click "Go Back" button. */
    public void clickGoBack() {
        unsavedProgressPopUp().getGoBackButton().click();
    }
}
