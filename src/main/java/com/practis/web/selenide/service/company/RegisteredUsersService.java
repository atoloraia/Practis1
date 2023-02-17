package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;

import com.codeborne.selenide.Condition;

public class RegisteredUsersService {

    /** Click on 3-dot menu for the Users - Registered - View Profile. */
    public void clickSingleActionViewProfile() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getViewProfileAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - User Settings. */
    public void clickSingleActionUserSettings() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getUserSettingsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign PSs. */
    public void clickSingleActionAssignPs() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getAssignPractisSetsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign Labels. */
    public void clickSingleActionAssignLabels() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getAssignLabelsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Nudge User. */
    public void clickSingleActionNudgeUser() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getNudgeUsersAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Export Report. */
    public void clickSingleActionExportReport() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getExportReportAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Delete User. */
    public void clickSingleActionDeleteUser() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getDeleteUserAction().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignPs() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersRegisteredTab().getAssignPsBulkAction().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignLabels() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersRegisteredTab().getAssignLabelsBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Nudge User. */
    public void clickBulkActionNudge() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersRegisteredTab().getNudgeUsersBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Export Report. */
    public void clickBulkActionExportReport() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersRegisteredTab().getExportReportBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Delete Users. */
    public void clickBulkActionDeleteUsers() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
        usersRegisteredTab().getDeleteUsersBulkAction().click();
    }

    /** Click User row */
    public void clickUserRow(final String user) {
        final var userRow = usersRegisteredTab().getUserRow().find(Condition.matchText(user));
        userRow.click();
    }
}
