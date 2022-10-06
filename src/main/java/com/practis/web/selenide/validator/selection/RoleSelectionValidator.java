package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;

import com.codeborne.selenide.CollectionCondition;

public class RoleSelectionValidator {

  /**
   * Assert Assign module: Roles section: User.
   */
  public static void assertUserRadioButton() {
    inviteUserRoleModule().getAssignRoleRadioButton().shouldBe(CollectionCondition.size(2));
    inviteUserRoleModule().getAssignRoleRadioButton().get(0).shouldBe(exactText("User"));
    inviteUserRoleModule().getAssignRoleRadioButton().get(1).shouldBe(exactText("Admin"));
    inviteUserRoleModule().getAssignRoleRadioButton().get(0).$(".sc-cgLVfi.kNYzXM");
    inviteUserRoleModule().getAssignRoleRadioButton().get(1).$(".sc-cgLVfi.RYYau");
  }

  /**
   * Assert Assign module: Roles section: Admin.
   */
  public static void assertAdminRadioButton() {
    inviteUserRoleModule().getAssignRoleRadioButton().shouldBe(CollectionCondition.size(2));
    inviteUserRoleModule().getAssignRoleRadioButton().get(0).shouldBe(exactText("User"));
    inviteUserRoleModule().getAssignRoleRadioButton().get(1).shouldBe(exactText("Admin"));
    inviteUserRoleModule().getAssignRoleRadioButton().get(1).$(".sc-cgLVfi.kNYzXM");
    inviteUserRoleModule().getAssignRoleRadioButton().get(0).$(".sc-cgLVfi.RYYau");
  }

}
