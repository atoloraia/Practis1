package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;

import com.codeborne.selenide.CollectionCondition;

public class RoleSelectionValidator {

    /** Assert Assign module: Roles section: User. */
    public static void assertUserRadioButton() {
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .shouldBe(CollectionCondition.size(2));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .shouldBe(exactText("User"));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(1)
                .shouldBe(exactText("Admin"));
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(0).$(".sc-cgLVfi.kNYzXM");
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(1).$(".sc-cgLVfi.RYYau");
    }

    /** Assert Assign module: Roles section: Admin. */
    public static void assertAdminRadioButton() {
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .shouldBe(CollectionCondition.size(2));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .shouldBe(exactText("User"));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(1)
                .shouldBe(exactText("Admin"));
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(1).$(".sc-cgLVfi.kNYzXM");
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(0).$(".sc-cgLVfi.RYYau");
    }

    /** Assert Assign module: Roles section: Partially selection. */
    public static void assertPartiallySelection() {
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .shouldBe(CollectionCondition.size(2));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(0)
                .shouldBe(exactText("User"));
        inviteUserRoleModule()
                .getAssignUserRoleRadioButtonInvite()
                .get(1)
                .shouldBe(exactText("Admin"));
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(1).$(".sc-cgLVfi.mUKoz");
        inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().get(0).$(".sc-cgLVfi.mUKoz");
    }
}
