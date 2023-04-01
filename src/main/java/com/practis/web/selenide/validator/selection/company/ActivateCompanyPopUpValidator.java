package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;

public class ActivateCompanyPopUpValidator {

    /** Assert 'Activate Company' pop-up. */
    public static void assertActivateCompanyPopUp(String company) {
        activateCompanyPopUp()
                .getActivateCompanyTitle()
                .shouldHave(Condition.text("Activate Company?"));
        final var expectedText =
                "Users and admins will regain access to the product"
                        + "All data will be restored"
                        + "Reactivation will happen silently – we won't send notifications"
                        + " to the admins or users of this company";
        assertEquals(expectedText, activateCompanyPopUp().getDescriptionText());
        activateCompanyPopUp().getDescriptionField().shouldBe(matchText(company));
        activateCompanyPopUp().getCompanyNameField().shouldBe(visible);

        activateCompanyPopUp().getCancelButton().shouldBe(visible);
        activateCompanyPopUp().getCancelButton().shouldHave(Condition.text("Cancel"));
        activateCompanyPopUp().getConfirmButton().shouldBe(visible);
        activateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Activate"));
    }
}
