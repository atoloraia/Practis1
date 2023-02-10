package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.warningDeleteUserPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

public class UsersService {

    /** Click 3-dot menu for the Users - Registered. */
    public void clickSingleActionUsersRegistered() {
        usersPage().getThreeDotMenu().get(0).click();
    }

    /** Click on 3-dot menu for the Users - Registered - View Profile. */
    public void clickUsersRegisteredSingleActionViewProfile(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getViewProfileAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - User Settings. */
    public void clickUsersRegisteredSingleActionUserSettings(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getUserSettingsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign PSs. */
    public void clickUsersRegisteredSingleActionAssignPs() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignPractisSetsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign Labels. */
    public void clickUsersRegisteredSingleActionAssignLabels(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignLabelsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Nudge User. */
    public void clickUsersRegisteredSingleActionNudgeUser(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getNudgeUsersAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Export Report. */
    public void clickUsersRegisteredSingleActionExportReport(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getExportReportAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Delete User. */
    public void clickUsersRegisteredSingleActionDeleteUser(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getDeleteUserAction().click();
    }

    /** Click on Proceed button. */
    public void clickUsersRegisteredSingleActionDeleteUserProceed() {
        warningDeleteUserPopUp().getProceedButton().click();
    }

    /** Click bulk action for Users - Registered. */
    public void clickBulkActionUsersRegistered() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignPsUsersRegistered() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getAssignPsBulkAction().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignLabelsUsersRegistered() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getAssignLabelsBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Nudge User. */
    public void clickUsersRegisteredBulkActionNudgeUser() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getNudgeUsersBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Export Report. */
    public void clickUsersRegisteredBulkActionExportReport() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getExportReportBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Delete Users. */
    public void clickUsersRegisteredBulkActionDeleteUsers() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersPage().getDeleteUsersBulkAction().click();
    }
}
