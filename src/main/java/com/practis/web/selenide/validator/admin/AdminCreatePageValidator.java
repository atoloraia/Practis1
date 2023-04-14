package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminCreatePageValidator {

    /** Assert elements on New Admin page. */
    public static void assertElementsOnCreateCompanyPage() {
        adminCreatePage().getNewPractisAdminTitle().shouldBe(exactText("New Practis Admin"));
        adminCreatePage().getCreateButton().shouldBe(visible);
        adminCreatePage().getCreateButton().shouldBe(exactText("Create"));
        adminCreatePage().getCreateButton().shouldBe(disabled);
        adminCreatePage().getCreateButton().shouldBe(attribute("color", "default"));
        adminCreatePage().getCreateButton().shouldBe(attribute("width", "128px"));

        adminCreatePage().getEmailFieldElements().shouldBe(size(1));
        adminCreatePage()
                .getEmailFieldElements()
                .get(0)
                .sibling(0)
                .shouldBe(exactText("Email Address"));
        adminCreatePage().getEmailFieldElements().get(0).shouldBe(visible);

        adminCreatePage().getFirstNameField().shouldBe(size(1));
        adminCreatePage().getFirstNameField().get(0).sibling(0).shouldBe(exactText("First Name"));
        adminCreatePage().getFirstNameField().get(0).shouldBe(visible);

        adminCreatePage().getLastNameField().shouldBe(size(1));
        adminCreatePage().getLastNameField().get(0).sibling(0).shouldBe(exactText("Last Name"));
        adminCreatePage().getLastNameField().get(0).shouldBe(visible);

        adminCreatePage().getPasswordField().shouldBe(size(1));
        adminCreatePage().getPasswordField().get(0).sibling(0).shouldBe(exactText("Password"));
        adminCreatePage().getPasswordField().get(0).shouldBe(visible);

        adminCreatePage().getShowPassword().shouldBe(size(1));
        adminCreatePage().getShowPassword().get(0).shouldBe(exactText("Show"));
        adminCreatePage().getShowPassword().get(0).shouldBe(visible);

        adminCreatePage().getShowPassword().get(0).click();
        adminCreatePage().getHidePassword().shouldBe(size(1));
        adminCreatePage().getHidePassword().get(0).shouldBe(exactText("Hide"));
        adminCreatePage().getHidePassword().get(0).shouldBe(visible);

        adminCreatePage().getDeleteRowButton().shouldBe(size(1));
        adminCreatePage().getDeleteRowButton().get(0).shouldBe(visible);

        adminCreatePage().getAddRowLink().shouldBe(visible);
        adminCreatePage().getAddRowLink().shouldBe(attribute("color", "#4aa9e2"));
        adminCreatePage().getAddRowLink().shouldBe(attribute("font-size", "13"));

        adminCreatePage().getDeleteRowButton().get(0).click();
    }
}
