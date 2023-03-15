package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.processingPopUp;

public class ProcessingPopUpValidator {

    /** Assert 'Inviting Users' pop up'. */
    public static void asserProcessingPopUp(String title) {
        processingPopUp().getProcessTitle().shouldBe(visible);
        processingPopUp().getProcessTitle().shouldBe(exactText(title));
        processingPopUp().getProgressBar().isDisplayed();
        processingPopUp().getWarningMessage().shouldBe(visible);
        processingPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        processingPopUp().getStopButton().shouldBe(visible);
        processingPopUp().getStopButton().shouldBe(exactText("Stop"));
    }
}
