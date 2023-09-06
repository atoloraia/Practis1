package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.activateCompanyPopUp;

import com.codeborne.selenide.Condition;

public class ActivateCompanyPopUpValidator {

    /** Assert 'Activate Company' pop-up. */
    public static void assertActivateCompanyPopUp(String company) {
        activateCompanyPopUp()
                .getActivateCompanyTitle()
                .shouldHave(Condition.text("Activate Company?"));
        activateCompanyPopUp().getActivateCompanyDescription().get(0).shouldBe(visible);
        activateCompanyPopUp()
                .getActivateCompanyDescription()
                .get(0)
                .shouldBe(exactText("Users and admins will regain access to the product"));
        activateCompanyPopUp().getActivateCompanyDescription().get(1).shouldBe(visible);
        activateCompanyPopUp()
                .getActivateCompanyDescription()
                .get(1)
                .shouldBe(exactText("All data will be restored"));
        activateCompanyPopUp().getActivateCompanyDescription().get(2).shouldBe(visible);
        activateCompanyPopUp()
                .getActivateCompanyDescription()
                .get(2)
                .shouldBe(
                        exactText(
                                "Reactivation will happen silently - we won't send notifications"
                                        + " to the admins or users of this company"));
        activateCompanyPopUp().getDescriptionField().shouldBe(matchText(company));
        activateCompanyPopUp().getDescriptionField().shouldBe(matchText("to confirm reactivation"));
        activateCompanyPopUp().getCompanyNameField().shouldBe(visible);
        activateCompanyPopUp().getCompanyNameField().shouldBe(enabled);

        activateCompanyPopUp().getCrossButton().shouldBe(visible);
        activateCompanyPopUp().getConfirmButton().shouldBe(visible);
        activateCompanyPopUp().getConfirmButton().shouldBe(disabled);
        activateCompanyPopUp().getConfirmButton().shouldBe(attribute("color", "default"));
        activateCompanyPopUp().getConfirmButton().shouldBe(attribute("type", "submit"));
        activateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Activate"));
    }
}
