package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminCreatePageValidator {

    /** Assert elements on New Admin page. */
    public static void assertElementsOnCreateAdminPage() {
        adminCreatePage().getNewPractisAdminTitle().shouldBe(visible);
        adminCreatePage().getNewPractisAdminTitle().shouldBe(exactText("New Practis Admin"));
        adminCreatePage().getCloseButton().shouldBe(visible);

        adminCreatePage().getCreateButton().shouldBe(visible);
        adminCreatePage().getCreateButton().shouldBe(exactText("Create"));
        adminCreatePage().getCreateButton().shouldBe(disabled);
        adminCreatePage().getCreateButton().shouldBe(attribute("color", "default"));
        adminCreatePage().getCreateButton().shouldBe(attribute("type", "submit"));
        adminCreatePage().getCreateButton().shouldBe(attribute("width", "130px"));

        adminCreatePage().getEmailFieldTitle().shouldBe(visible);
        adminCreatePage().getEmailFieldTitle().shouldBe(exactText("Work email"));
        adminCreatePage().getEmailInput().shouldBe(visible);
        adminCreatePage().getEmailInput().shouldBe(enabled);
        adminCreatePage().getEmailInput().shouldBe(empty);

        adminCreatePage().getEmailSuffix().shouldBe(visible);
        adminCreatePage().getEmailSuffix().shouldBe(exactText("@gopractis.com"));

        adminCreatePage().getExplanationText().shouldBe(visible);
        adminCreatePage()
                .getExplanationText()
                .shouldBe(
                        exactText(
                                "Only corporate email addresses are allowed: name@gopractis.com."));

        adminCreatePage().getUserExistsError().shouldBe(hidden);
    }

    /** Assert elements on New Admin page. */
    public static void assertErrorStateOnCreateAdminPage() {
        adminCreatePage().getExplanationText().shouldBe(hidden);
        adminCreatePage().getUserExistsError().shouldBe(visible);
        adminCreatePage()
                .getUserExistsError()
                .shouldBe(exactText("User already exists in our system."));
        adminCreatePage().getCreateButton().shouldBe(disabled);
        adminCreatePage().getCreateButton().shouldBe(attribute("color", "default"));
    }
}
