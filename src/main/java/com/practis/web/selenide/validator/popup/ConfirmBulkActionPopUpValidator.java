package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmBulkActionPopUp;

public class ConfirmBulkActionPopUpValidator {

    /** Assert 'Confirm Bulk Action' pop up. */
    public static void assertConfirmBulkActionPopUp() {
        confirmBulkActionPopUp().getConfirmBulkActionTitle().shouldBe(visible);
        confirmBulkActionPopUp()
                .getConfirmBulkActionTitle()
                .shouldBe(matchText("Confirm bulk action"));
        confirmBulkActionPopUp().getDescriptionAreYouSure().shouldBe(visible);
        confirmBulkActionPopUp().getCancelButton().shouldBe(visible);
        confirmBulkActionPopUp().getCancelButton().shouldBe(matchText("Cancel"));
        confirmBulkActionPopUp().getProceedButton().shouldBe(visible);
        confirmBulkActionPopUp().getProceedButton().shouldBe(matchText("Proceed"));
    }
}
