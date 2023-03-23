package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.Condition;

public class DeactivateCompanyPopUpValidator {

    /** Assert 'Deactivate Company' pop-up. */
    public static void assertDeactivateCompanyPopUp() {
        awaitSoft(
                10,
                () ->
                        deactivateCompanyPopUp()
                                .getDeactivateCompanyTitle()
                                .text()
                                .contains("Deactivated Company?"));
        deactivateCompanyPopUp()
                .getDeactivateCompanyTitle()
                .shouldHave(Condition.text("Deactivated Company?"));
        deactivateCompanyPopUp().getDeactivateCompanyDescription().shouldBe(matchText("All data"));
        deactivateCompanyPopUp().getDescriptionField().shouldHave(Condition.text("please type"));
        deactivateCompanyPopUp().getCompanyNameField().shouldBe(visible);

        deactivateCompanyPopUp().getCancelButton().shouldBe(visible);
        deactivateCompanyPopUp().getCancelButton().shouldHave(Condition.text("Cancel"));
        deactivateCompanyPopUp().getConfirmButton().shouldBe(visible);
        deactivateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Deactivate"));
    }
}
