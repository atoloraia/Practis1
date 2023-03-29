package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;

public class DeactivateCompanyPopUpValidator {

    /** Assert 'Deactivate Company' pop-up. */
    public static void assertDeactivateCompanyPopUp(String company) {
        deactivateCompanyPopUp()
                .getDeactivateCompanyTitle()
                .shouldHave(Condition.text("Deactivate Company?"));
        final var expectedText =
                "Users and admins will lose access to the productUser data (assignments, progress,"
                        + " etc.) will NOT be deleted.You can activate this company again, and all"
                        + " access will be restoredDeactivation happens silently – we won't send"
                        + " notifications to companies' users or admins.";
        assertEquals(expectedText, deactivateCompanyPopUp().getDescriptionText());
        deactivateCompanyPopUp().getDescriptionField().shouldBe(matchText(company));
        deactivateCompanyPopUp().getCompanyNameField().shouldBe(visible);

        deactivateCompanyPopUp().getCancelButton().shouldBe(visible);
        deactivateCompanyPopUp().getCancelButton().shouldHave(Condition.text("Cancel"));
        deactivateCompanyPopUp().getConfirmButton().shouldBe(visible);
        deactivateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Deactive"));
    }
}
