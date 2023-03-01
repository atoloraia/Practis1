package com.practis.web.selenide.validator.company.users;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.userRoleModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersDraftTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertEmptyPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersPage;
import static com.practis.web.selenide.validator.selection.CreatedBySectionValidation.assertElementsOnCreatedBySection;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAssignEmptyTeam;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class DraftsTabValidator {

    /** Assert Users - Empty Drafts list. */
    public static void assertEmptyDraftsPage() {
        assertEmptyPage();
        usersDraftTab().getDraftColumn().shouldBe(visible);
        usersDraftTab().getDraftColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getDraftColumn().shouldBe(exactText("Drafts"));
        usersDraftTab().getUsersColumn().shouldBe(visible);
        usersDraftTab().getUsersColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getUsersColumn().shouldBe(exactText("Users"));
        usersDraftTab().getCreatedByColumn().shouldBe(visible);
        usersDraftTab().getCreatedByColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getCreatedByColumn().shouldBe(exactText("Created by"));
        usersDraftTab().getCreatedOnColumn().shouldBe(visible);
        usersDraftTab().getCreatedOnColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getCreatedOnColumn().shouldBe(exactText("Created on"));
        usersDraftTab().getEditedByColumn().shouldBe(visible);
        usersDraftTab().getEditedByColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getEditedByColumn().shouldBe(exactText("Edited by"));
        usersDraftTab().getEditedOnColumn().shouldBe(visible);
        usersDraftTab().getEditedOnColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getEditedOnColumn().shouldBe(exactText("Edited on"));
        assertUsersEmptyState("No Drafts Yet");
    }

    /** Assert Users - Drafts list. */
    public static void assertDraftsPage() {
        assertUsersPage();
        usersDraftTab().getDraftColumn().shouldBe(visible);
        usersDraftTab().getDraftColumn().shouldBe(exactText("Drafts"));
        usersDraftTab().getUsersColumn().shouldBe(visible);
        usersDraftTab().getUsersColumn().shouldBe(exactText("Users"));
        usersDraftTab().getCreatedByColumn().shouldBe(visible);
        usersDraftTab().getCreatedByColumn().shouldBe(exactText("Created by"));
        usersDraftTab().getCreatedOnColumn().shouldBe(visible);
        usersDraftTab().getCreatedOnColumn().shouldBe(exactText("Created on"));
        usersDraftTab().getEditedByColumn().shouldBe(visible);
        usersDraftTab().getEditedByColumn().shouldBe(exactText("Edited by"));
        usersDraftTab().getEditedOnColumn().shouldBe(visible);
        usersDraftTab().getEditedOnColumn().shouldBe(exactText("Edited on"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);
        usersPage().getListValues().get(5).shouldBe(visible);
    }

    /** Assert Users - Drafts, empty filters modal. */
    public static void assertElementsDraftsFilters() {
        assertElementsOnCreatedBySection();
        assertElementsOnCreatedBySection();
        assertFiltersElementsDefaultState();
    }

    /** Assert single action for the Users - Drafts. */
    public static void assertSingleActionUsersDrafts() {
        usersDraftTab().getEditAction().shouldBe(visible);
        usersDraftTab().getEditAction().shouldBe(exactText("Edit"));
        usersDraftTab().getDeleteAction().shouldBe(visible);
        usersDraftTab().getDeleteAction().shouldBe(exactText("Delete Draft"));
    }

    /** Assert bulk action for the Users - Drafts. */
    public static void assertBulkActionUsersDrafts() {
        usersDraftTab().getDeleteBulkAction().shouldBe(visible);
        usersDraftTab().getDeleteBulkAction().shouldBe(exactText("Delete Drafts"));
    }

    public static void assertElementsOnEditDraftPage() {
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage()
                .getInviteUsersToTheAppTitle()
                .shouldBe(exactText("Invite Users to the App"));

        inviteUsersPage().getSearchField().shouldBe(visible);
        inviteUsersPage().getSearchField().shouldBe(enabled);
        inviteUsersPage().getFiltersButton().shouldBe(visible);
        inviteUsersPage().getFiltersButton().shouldBe(enabled);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(enabled);
        inviteUsersPage().getUploadTemplateButton().shouldBe(visible);
        inviteUsersPage().getUploadTemplateButton().shouldBe(enabled);

        // Table header
        inviteUsersPage().getFirstNameColumn().shouldBe(visible);
        inviteUsersPage().getFirstNameColumn().shouldBe(exactText("First Name"));
        inviteUsersPage().getLastNameColumn().shouldBe(visible);
        inviteUsersPage().getLastNameColumn().shouldBe(exactText("Last Name"));
        inviteUsersPage().getUserEmailColumn().shouldBe(visible);
        inviteUsersPage().getUserEmailColumn().shouldBe(exactText("User Email"));
        inviteUsersPage().getRoleColumn().shouldBe(visible);
        inviteUsersPage().getRoleColumn().shouldBe(exactText("Role"));
        inviteUsersPage().getTeamsColumn().shouldBe(visible);
        inviteUsersPage().getTeamsColumn().shouldBe(exactText("Teams"));
        inviteUsersPage().getPractisSetColumn().shouldBe(visible);
        inviteUsersPage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
        inviteUsersPage().getLabelsColumn().shouldBe(visible);
        inviteUsersPage().getLabelsColumn().shouldBe(exactText("Labels"));

        // User row
        inviteUsersPage().getFirstNameField().sibling(0).shouldBe(visible);
        inviteUsersPage().getFirstNameField().sibling(0).shouldBe(exactText("First Name*"));
        inviteUsersPage().getLastNameField().sibling(0).shouldBe(visible);
        inviteUsersPage().getLastNameField().sibling(0).shouldBe(exactText("Last Name*"));
        inviteUsersPage().getEmailField().sibling(0).shouldBe(visible);
        inviteUsersPage().getEmailField().sibling(0).shouldBe(exactText("Email*"));
        // Role modal
        inviteUsersPage().getRoleField().shouldBe(visible);
        inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
        inviteUsersPage().getRoleField().click();
        userRoleModule().getUserRoleRadioButtonLabel().shouldBe(visible);
        userRoleModule().getUserRoleRadioButtonLabel().shouldBe(exactText("User"));
        userRoleModule().getAdminRoleRadioButtonLabel().shouldBe(visible);
        userRoleModule().getAdminRoleRadioButtonLabel().shouldBe(exactText("Admin"));
        // Teams Modal
        inviteUsersPage().getTeamsField().shouldBe(visible);
        inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
        inviteUsersPage().getTeamsField().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertAssignEmptyTeam();
        teamModule().getCancelButton().click();

        // Practis Set modal
        inviteUsersPage().getPractisSetsField().shouldBe(visible);
        inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
        inviteUsersPage().getPractisSetsField().click();
        inviteUserPsModule().getNoPractisSetYetText().shouldBe(visible);
        inviteUserPsModule().getNoPractisSetYetText().shouldBe(exactText("No Practis Sets found"));
        inviteUserPsModule().getCancelButton().click();

        // Label Modal
        inviteUsersPage().getLabelsField().shouldBe(visible);
        inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
        jsClick(inviteUsersPage().getLabelsField());
        inviteUsersPage().getLabelsField().click();
        assertNoLabelsYet();

        // Added row
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);

        inviteUsersPage().getUserCounter().shouldBe(visible);
        inviteUsersPage().getUserCounter().shouldBe(exactText("1 item"));

        inviteUsersPage().getSaveAsDraftButton().shouldBe(hidden);
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(matchText("Last Updated"));
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
        inviteUsersPage()
                .getInviteSelectedUsersButton()
                .shouldBe(exactText("Invite Selected Users"));
    }

    /** Assert 'Warning' pop up after clicking on Delete Draft. */
    public static void assertWarningDeleteDraftPopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Warning"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp()
                .getConfirmDescription()
                .shouldBe(
                        matchText(
                                "This action cannot be undone. You will erase this draft and all"
                                        + " the saved information."));
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Go Back"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Proceed"));
    }
}
