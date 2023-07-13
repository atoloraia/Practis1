package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.userSettingsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.registeredUsersService;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.util.AwaitUtils;

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
        jsClick(usersRegisteredTab().getAssignPractisSetsAction());
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

    /** Click on 3-dot menu for the Users - Registered - Deactivate User. */
    public void clickSingleActionDeactivateUser() {
        usersPage().getThreeDotMenu().get(0).click();
        usersRegisteredTab().getDeactivateUserAction().click();
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignPs() {
        AwaitUtils.awaitSoft(60, () -> false);
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersRegisteredTab().getAssignPsBulkAction().click();
    }

    /** Find team labels. */
    public SelenideElement findPSCounterUserRow(final String user) {
        final var userRow = usersPage().getUserRow().find(Condition.matchText(user));
        return userRow.$("[data-test='practis-sets-count']");
    }

    /** Click bulk action for Users - Registered - Assign PS. */
    public void clickBulkActionAssignLabels() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersRegisteredTab().getAssignLabelsBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Nudge User. */
    public void clickBulkActionNudge() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersRegisteredTab().getNudgeUsersBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Export Report. */
    public void clickBulkActionExportReport() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersRegisteredTab().getExportReportBulkAction().click();
    }

    /** Click bulk action for the Users - Registered - Deactivate Users. */
    public void clickBulkActionDeactivateUsers() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersRegisteredTab().getDeactivateUsersBulkAction().click();
    }

    /** Click User row */
    public void clickUserRow(final String user) {
        final var userRow = usersRegisteredTab().getUserRow().find(Condition.matchText(user));
        userRow.click();
    }

    /** Find Practis Set counter on User row. */
    public SelenideElement findPsCounter(final String user) {
        final var userRow = usersRegisteredTab().getUserRow().find(Condition.matchText(user));
        return userRow.$("[data-test='practis-sets-count']");
    }

    /** Assert Practis Set counter. */
    public static void assertPsCountOnUsersPage(final String user, final String count) {
        registeredUsersService().findPsCounter(user).shouldBe(visible);
        registeredUsersService().findPsCounter(user).shouldBe(exactText(count));
    }

    /** Edit First anLast Name for User. */
    public void editRegisteredUser() {
        userSettingsPage().getUserFirstNameField().append("_edit");
        userSettingsPage().getUserLastNameField().append("_edit");
        userSettingsPage().getUpdateButton().click();
    }
}
