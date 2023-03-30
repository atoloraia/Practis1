package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;

public class ActivateCompanyPopUpValidator {

    /** Assert 'Active Company' pop-up. */
    public static void assertActivateCompanyPopUp(String company) {
        activateCompanyPopUp()
                .getActivateCompanyTitle()
                .shouldHave(Condition.text("Activate Company?"));
        final var expectedText =
                "Users and admins will lose access to the productUser data (assignments, progress,"
                        + " etc.) will NOT be deleted.You can activate this company again, and all"
                        + " access will be restoredDeactivation happens silently – we won't send"
                        + " notifications to companies' users or admins.";
        assertEquals(expectedText, deactivateCompanyPopUp().getDescriptionText());
        activateCompanyPopUp().getDescriptionField().shouldBe(matchText(company));
        activateCompanyPopUp().getCompanyNameField().shouldBe(visible);

        activateCompanyPopUp().getCancelButton().shouldBe(visible);
        activateCompanyPopUp().getCancelButton().shouldHave(Condition.text("Cancel"));
        activateCompanyPopUp().getConfirmButton().shouldBe(visible);
        activateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Deactive"));
    }
}
