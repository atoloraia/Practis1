package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

public class UsersService {

    /** Click 3-dot menu for the Users - Registered. */
    public void clickSingleAction() {
        usersPage().getThreeDotMenu().get(0).click();
    }

    /** Click on 3-dot menu for the Users - Registered - View Profile. */
    public void clickSingleActionViewProfile(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getViewProfileAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - User Settings. */
    public void clickSingleActionUserSettings(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getUserSettingsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign PSs. */
    public void clickSingleActionAssignPs() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignPractisSetsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign Labels. */
    public void clickSingleActionAssignLabels(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignLabelsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Nudge User. */
    public void clickSingleActionNudgeUser(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getNudgeUsersAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Export Report. */
    public void clickSingleActionExportReport(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getExportReportAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Delete User. */
    public void clickSingleActionDeleteUser(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getDeleteUserAction().click();
    }

    /** Click bulk action for Users - Registered. */
    public void clickBulkAction() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignPs() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getAssignPsBulkAction().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignLabels() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getAssignLabelsBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Nudge User. */
    public void clickBulkActionNudge() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getNudgeUsersBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Export Report. */
    public void clickBulkActionExportReport() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getExportReportBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Delete Users. */
    public void clickBulkActionDeleteUsers() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getDeleteUsersBulkAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Resend Invite. */
    public void clickSingleActionResendInvite() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getResendInviteAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Copy Invite text. */
    public void clickSingleActionCopyInviteText() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getCopyInviteTextAction().click();
    }

    /** Click on 3-dot menu for the Users - Pending - Revoke. */
    public void clickSingleActionRevoke(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getRevokeAction().click();
    }

    /** Click bulk action - Pending - Resend Invite. */
    public void clickBulkActionResendInvite() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getResendInviteBulkAction().click();
    }

    /** Click bulk action - Pending - Revoke. */
    public void clickBulkActionRevoke() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getRevokeBulkAction().click();
    }
}
