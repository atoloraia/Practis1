package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;

public class ConfirmBulkActionPopUpValidator {

    /** Assert 'Confirm Bulk Action' pop up. */
    public static void assertConfirmBulkActionPopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Confirm bulk action"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Cancel"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Proceed"));
    }
}
