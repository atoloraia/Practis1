package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.editPhotoPopUp;

public class EditPhotoPopUpService {

    /** Click "Cancel" button. */
    public void clickCancelButton() {
        editPhotoPopUp().getCancelButton().click();
    }

    /** Click "Save" button. */
    public void clickSaveButton() {
        editPhotoPopUp().getSaveButton().click();
    }
}
