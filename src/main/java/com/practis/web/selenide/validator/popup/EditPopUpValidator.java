package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.editPhotoPopUp;

public class EditPopUpValidator {

    /** Assert 'Edit Photo' pop up'. */
    public static void assertEditPhotoPopUp() {
        editPhotoPopUp().getEditPhotoTitle().shouldBe(visible);
        editPhotoPopUp().getEditPhotoTitle().shouldBe(exactText("Edit Photo"));
        editPhotoPopUp().getZoomText().shouldBe(visible);
        editPhotoPopUp().getZoomText().shouldBe(exactText("Zoom"));
        editPhotoPopUp().getZoomBar().shouldBe(visible);
        editPhotoPopUp().getZoomBar().shouldBe(enabled);

        editPhotoPopUp().getCancelButton().shouldBe(visible);
        editPhotoPopUp().getCancelButton().shouldBe(matchText("Cancel"));

        editPhotoPopUp().getSaveButton().shouldBe(visible);
        editPhotoPopUp().getSaveButton().shouldBe(matchText("Save"));
    }
}
