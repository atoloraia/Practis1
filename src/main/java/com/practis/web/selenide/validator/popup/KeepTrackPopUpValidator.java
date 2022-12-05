package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;

public class KeepTrackPopUpValidator {

    /** Assert Keep Track pop up. */
    public static void assertKeepTrackPopUp(final String team) {
        keepTrackPopUp().getKeepTrackTitle().shouldBe(visible);
        keepTrackPopUp()
                .getKeepTrackTitle()
                .shouldBe(exactText("Keep track of members’ training " + "progress"));
        keepTrackPopUp().getKeepTrackDescription().shouldBe(visible);
        keepTrackPopUp()
                .getKeepTrackDescription()
                .shouldBe(
                        exactText(
                                "See the progress of all Practis "
                                        + "Sets being worked on by "
                                        + team
                                        + " team and access detailed reports for each Practis"
                                        + " Set "));
        keepTrackPopUp().getGotItButton().shouldBe(visible);
        keepTrackPopUp().getGotItButton().shouldBe(exactText("Got it"));
    }
}
