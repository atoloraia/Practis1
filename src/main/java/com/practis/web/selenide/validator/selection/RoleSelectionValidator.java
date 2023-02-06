package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.userRoleModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class RoleSelectionValidator {

    /** Assert Assign module: Roles section: User. */
    public static void assertUserRadioButton() {
        userRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("User"));
        userRoleModule()
                .getAssignAdminRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("Admin"));
    }

    /** Assert Assign module: Roles section: Admin. */
    public static void assertAdminRadioButton() {
        userRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("User"));
        userRoleModule()
                .getAssignAdminRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("Admin"));
    }

    /** Assert Assign module: Roles section: Partially selection. */
    public static void assertPartiallySelection() {
        userRoleModule()
                .getAssignUserRolePartiallyStateRadioButton()
                .parent()
                .shouldBe(text("User"));
        userRoleModule()
                .getAssignAdminRolePartiallyStateRadioButton()
                .parent()
                .shouldBe(text("Admin"));
    }

    /** Assert elements on Role model. */
    public static void assertElementsOnRoleModal() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        userRoleModule().getUserRoleTitle().shouldBe(visible);
        userRoleModule().getUserRoleTitle().shouldBe(exactText("Role"));
        userRoleModule().getUserRoleCheckbox().shouldBe(enabled);
        userRoleModule().getUserRoleCheckboxLabel().shouldBe(visible);
        userRoleModule().getUserRoleCheckboxLabel().shouldBe(exactText("User"));
        userRoleModule().getAdminRoleCheckbox().shouldBe(enabled);
        userRoleModule().getAdminRoleCheckboxLabel().shouldBe(visible);
        userRoleModule().getAdminRoleCheckboxLabel().shouldBe(exactText("Admin"));
    }
}
