package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.roleModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class RoleUserModuleService {

  /**
   * Find Role checkbox.
   */
  public SelenideElement findRoleCheckbox(final String role) {
    final var labelRow = inviteUserRoleModule().getAssignRoleRadioButton()
        .find(Condition.matchText(role));
    final var checkbox = labelRow.$(".sc-cgLVfi.lcmPNH").sibling(0);
    return checkbox;
  }

  /**
   * Select role.
   */
  public InviteUserService selectRole(final String role) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    roleModuleService().findRoleCheckbox(role).click();
    return null;
  }

}
