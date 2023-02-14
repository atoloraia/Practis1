package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.downloadsFolder;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.nudgePopup;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import java.io.File;

public class NudgeUserValidator {

    /** Assert disabled Apply button. */
    public static void assertEmptyNudgeUserPopUp() {
        nudgePopup().getNudgeTitle().shouldBe(visible);
        nudgePopup().getNudgeTitle().shouldBe(exactText("Nudge"));
        nudgePopup().getNudgeDescription().shouldBe(visible);
        nudgePopup()
                .getNudgeDescription()
                .shouldBe(exactText("Send a direct message to the selected Users"));
        nudgePopup().getFromField().shouldBe(visible);
        nudgePopup().getFromField().shouldHave(value("Automation User"));
        nudgePopup().getMessageField().shouldBe(visible);
        nudgePopup().getMessageFieldText().shouldBe(visible);
        nudgePopup()
                .getMessageFieldText()
                .shouldHave(attribute("placeholder", "Write a message to the selected user(s)"));
        nudgePopup().getApplyButton().shouldBe(visible);
        nudgePopup().getApplyButton().shouldBe(exactText("Send"));
        nudgePopup().getCancelButton().shouldBe(visible);
        nudgePopup().getCancelButton().shouldBe(exactText("Cancel"));
    }

    /** Assert template has been downloaded. */
    public static void assertDownloadedFile(final String filename) {
        awaitSoft(5, () -> new File(downloadsFolder).exists());
    }
}
