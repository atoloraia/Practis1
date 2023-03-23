package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.Condition;

public class ActivateCompanyPopUpValidator {

    /** Assert 'Active Company' pop-up. */
    public static void assertActivateCompanyPopUp() {
        awaitSoft(
                10,
                () ->
                        activateCompanyPopUp()
                                .getActivateCompanyTitle()
                                .text()
                                .contains("Activate Company?"));
        activateCompanyPopUp()
                .getActivateCompanyTitle()
                .shouldHave(Condition.text("Activate Company?"));
        activateCompanyPopUp().getActivateCompanyDescription().shouldBe(matchText("All data"));
        activateCompanyPopUp().getDescriptionField().shouldHave(Condition.text("please type"));
        activateCompanyPopUp().getCompanyNameField().shouldBe(visible);

        activateCompanyPopUp().getCancelButton().shouldBe(visible);
        activateCompanyPopUp().getCancelButton().shouldHave(Condition.text("Cancel"));
        activateCompanyPopUp().getConfirmButton().shouldBe(visible);
        activateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Activate"));
    }
}
