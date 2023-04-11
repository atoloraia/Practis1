package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPendingTab;

public class PendingUsersService {

    /** Click on 3-dot menu for the Users - Pending - Resend Invite. */
    public void clickSingleActionResendInvite() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPendingTab().getResendInviteAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Copy Invite text. */
    public void clickSingleActionCopyInviteText() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPendingTab().getCopyInviteTextAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Revoke. */
    public void clickSingleActionRevoke() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPendingTab().getRevokeAction().click();
    }

    /** Click bulk action - Pending - Resend Invite. */
    public void clickBulkActionResendInvites() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersPendingTab().getResendInviteBulkAction().click();
    }

    /** Click bulk action - Pending - Revoke. */
    public void clickBulkActionRevoke() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersPendingTab().getRevokeBulkAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - View Profile. */
    public void clickSingleActionViewProfile() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPendingTab().getViewProfileAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Assign Labels. */
    public void clickSingleActionAssignLabels() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPendingTab().getAssignLabelsAction().click();
    }

    /** Click bulk action for Users - Pending - Assign PS. */
    public void clickBulkActionAssignLabels() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersPendingTab().getAssignLabelsBulkAction().click();
    }
}
