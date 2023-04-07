package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.thisDidNotWorkPage;

public class InvalidInviteValidator {

    /** Assert 'Hmm. This didn't work.' page */
    public static void assertElementsOnThisDidNotWork() {
        thisDidNotWorkPage().getInvalidInviteImg().shouldBe(visible);
        thisDidNotWorkPage().getThisDidNotWorkTitle().shouldBe(visible);
        thisDidNotWorkPage().getThisDidNotWorkTitle().shouldBe(exactText("Hmm. This didn't work."));
        thisDidNotWorkPage().getThisDidNotWorkDescription().shouldBe(visible);
        thisDidNotWorkPage()
                .getThisDidNotWorkDescription()
                .shouldBe(
                        exactText(
                                "Looks like this link has been revoked, is broken, or has already"
                                    + " been used. Either way, life goes on. But ask your manager"
                                    + " or trainer if they were somehow trying to invite you to"
                                    + " training."));
        thisDidNotWorkPage().getThisDidNotWorkDescription().shouldBe(visible);
        thisDidNotWorkPage()
                .getContactUsDescription()
                .shouldBe(
                        exactText(
                                "You can also contact us at Practis and we may be able to help."));
    }
}
