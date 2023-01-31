package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;

public class RoleSelectionValidator {

    /** Assert Assign module: Roles section: User. */
    public static void assertUserRadioButton() {
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("User"));
        inviteUserRoleModule()
                .getAssignAdminRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("Admin"));
    }

    /** Assert Assign module: Roles section: Admin. */
    public static void assertAdminRadioButton() {
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("User"));
        inviteUserRoleModule()
                .getAssignAdminRoleRadioButtonInvite()
                .get(0)
                .parent()
                .shouldBe(exactText("Admin"));
    }

    /** Assert Assign module: Roles section: Partially selection. */
    public static void assertPartiallySelection() {
        inviteUserRoleModule()
                .getAssignUserRolePartiallyStateRadioButton()
                .parent()
                .shouldBe(text("User"));
        inviteUserRoleModule()
                .getAssignAdminRolePartiallyStateRadioButton()
                .parent()
                .shouldBe(text("Admin"));
    }
}
