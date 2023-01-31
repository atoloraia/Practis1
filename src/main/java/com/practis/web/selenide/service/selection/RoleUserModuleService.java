package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.roleModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class RoleUserModuleService {

    /** Find Role checkbox. */
    public SelenideElement findUserCheckbox(final String role) {
        final var labelRow = inviteUserRoleModule().getAssignUserRoleRadioButtonInvite().first();
        final var checkbox = labelRow.parent().$("div[data-test='user-role-radio-view']");
        return checkbox;
    }

    /** Find Role checkbox. */
    public SelenideElement findAdminCheckbox(final String role) {
        final var labelRow = inviteUserRoleModule().getAssignAdminRoleRadioButtonInvite().first();
        final var checkbox = labelRow.parent().$("div[data-test='admin-role-radio-view']");
        return checkbox;
    }

    /** Select role. */
    public InviteUserService selectRole(final String role) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        switch (role) {
            case "User":
                roleModuleService().findUserCheckbox(role).click();
                break;
            case "Admin":
                roleModuleService().findAdminCheckbox(role).click();
                break;
        }
        return null;
    }
}
