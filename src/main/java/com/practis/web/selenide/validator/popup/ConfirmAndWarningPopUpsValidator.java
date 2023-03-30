package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;

public class ConfirmAndWarningPopUpsValidator {

    /** Assert 'Warning/Confirm' pop up. */
    public static void assertConfirmationModal(
            String text, String description, String button, String close) {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText(text));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(exactText(description));
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText(close));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText(button));
    }
}
