package com.practis.web.selenide.validator.selection.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deactivateCompanyPopUp;

import com.codeborne.selenide.Condition;

public class DeactivateCompanyPopUpValidator {

    /** Assert 'Deactivate Company' pop-up. */
    public static void assertDeactivateCompanyPopUp(String company) {
        deactivateCompanyPopUp()
                .getDeactivateCompanyTitle()
                .shouldHave(Condition.text("Deactivate Company?"));
        final var descriptionText = deactivateCompanyPopUp().getDescriptionText();
        deactivateCompanyPopUp().getDeactivateCompanyDescription().get(0).shouldBe(visible);
        deactivateCompanyPopUp()
                .getDeactivateCompanyDescription()
                .get(0)
                .shouldBe(exactText("Users and admins will lose access to the product"));
        deactivateCompanyPopUp().getDeactivateCompanyDescription().get(1).shouldBe(visible);
        deactivateCompanyPopUp()
                .getDeactivateCompanyDescription()
                .get(1)
                .shouldBe(
                        exactText("User data (assignments, progress, etc.) will NOT be deleted."));
        deactivateCompanyPopUp().getDeactivateCompanyDescription().get(2).shouldBe(visible);
        deactivateCompanyPopUp()
                .getDeactivateCompanyDescription()
                .get(2)
                .shouldBe(
                        exactText(
                                "You can activate this company again, and all access will be"
                                        + " restored"));
        deactivateCompanyPopUp().getDeactivateCompanyDescription().get(3).shouldBe(visible);
        deactivateCompanyPopUp()
                .getDeactivateCompanyDescription()
                .get(3)
                .shouldBe(
                        exactText(
                                "Deactivation happens silently - we won't send notifications to"
                                        + " companies' users or admins."));

        deactivateCompanyPopUp().getDescriptionField().shouldBe(matchText(company));
        deactivateCompanyPopUp()
                .getDescriptionField()
                .shouldBe(matchText("to confirm deactivation"));
        deactivateCompanyPopUp().getCompanyNameField().shouldBe(visible);
        deactivateCompanyPopUp().getCompanyNameField().shouldBe(enabled);
        deactivateCompanyPopUp().getConfirmButton().shouldBe(visible);
        deactivateCompanyPopUp().getConfirmButton().shouldBe(disabled);
        deactivateCompanyPopUp().getConfirmButton().shouldHave(Condition.text("Deactivate"));
        deactivateCompanyPopUp().getConfirmButton().shouldHave(attribute("type", "submit"));
        deactivateCompanyPopUp().getConfirmButton().shouldHave(attribute("color", "default"));
    }
}
