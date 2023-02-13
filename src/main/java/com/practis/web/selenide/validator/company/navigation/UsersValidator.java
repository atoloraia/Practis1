package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersDraftTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPendingTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;
import static com.practis.web.selenide.validator.selection.CreatedBySectionValidation.assertElementsOnCreatedBySection;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;
import static com.practis.web.selenide.validator.selection.InvitedBySectionValidator.assertElementsOnInvitedBySection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertElementsOnRoleModal;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeam;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;

public class UsersValidator {

    /** Assert grid row with input data. */
    public static void assertUserGridRowPending(
            final NewUserInput inputData, final GridRow gridRow) {
        gridRow.get("Users")
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
        gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
    }

    /** Assert Users - Registered list. */
    public static void assertUsersRegisteredPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersRegisteredTab().getUserColumn().shouldBe(visible);
        usersRegisteredTab().getUserColumn().shouldBe(exactText("Users"));
        usersRegisteredTab().getTeamsColumn().shouldBe(visible);
        usersRegisteredTab().getTeamsColumn().shouldBe(exactText("Teams"));
        usersRegisteredTab().getPractisSetColumn().shouldBe(visible);
        usersRegisteredTab().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
        usersRegisteredTab().getRoleColumn().shouldBe(visible);
        usersRegisteredTab().getRoleColumn().shouldBe(exactText("Role"));
        usersRegisteredTab().getRegisteredDateColumn().shouldBe(visible);
        usersRegisteredTab().getRegisteredDateColumn().shouldBe(exactText("Registered on"));
        usersRegisteredTab().getLastLoginColumn().shouldBe(visible);
        usersRegisteredTab().getLastLoginColumn().shouldBe(exactText("Last Login"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);
        usersPage().getListValues().get(5).shouldBe(visible);

        usersPage().getLabelsIcon().get(0).shouldBe(visible);
        usersPage().getThreeDotMenu().get(0).shouldBe(visible);
    }

    /** Assert Users - Empty Pending list. */
    public static void assertUsersPendingPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPendingTab().getUserColumn().shouldBe(visible);
        usersPendingTab().getUserColumn().shouldBe(exactText("Users"));
        usersPendingTab().getEmailColumn().shouldBe(visible);
        usersPendingTab().getEmailColumn().shouldBe(exactText("Email Address"));
        usersPendingTab().getRoleColumn().shouldBe(visible);
        usersPendingTab().getRoleColumn().shouldBe(exactText("Role"));
        usersPendingTab().getInvitedByColumn().shouldBe(visible);
        usersPendingTab().getInvitedByColumn().shouldBe(exactText("Invited by"));
        usersPendingTab().getInvitedOnColumn().shouldBe(visible);
        usersPendingTab().getInvitedOnColumn().shouldBe(exactText("Invited on"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);

        usersPage().getLabelsIcon().get(0).shouldBe(visible);
        usersPage().getThreeDotMenu().get(0).shouldBe(visible);

        usersPage().getNoUsersFoundIcon().shouldBe(hidden);
        usersPage().getNoUsersFoundText().shouldBe(hidden);
    }

    /** Assert Users - Empty Pending list. */
    public static void assertUsersEmptyPendingPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPendingTab().getUserColumn().shouldBe(visible);
        usersPendingTab().getUserColumn().shouldHave(attribute("disabled"));
        usersPendingTab().getUserColumn().shouldBe(exactText("Users"));
        usersPendingTab().getEmailColumn().shouldBe(visible);
        usersPendingTab().getEmailColumn().shouldHave(attribute("disabled"));
        usersPendingTab().getEmailColumn().shouldBe(exactText("Email Address"));
        usersPendingTab().getRoleColumn().shouldBe(visible);
        usersPendingTab().getRoleColumn().shouldHave(attribute("disabled"));
        usersPendingTab().getRoleColumn().shouldBe(exactText("Role"));
        usersPendingTab().getInvitedByColumn().shouldBe(visible);
        usersPendingTab().getInvitedByColumn().shouldHave(attribute("disabled"));
        usersPendingTab().getInvitedByColumn().shouldBe(exactText("Invited by"));
        usersPendingTab().getInvitedOnColumn().shouldBe(visible);
        usersPendingTab().getInvitedOnColumn().shouldHave(attribute("disabled"));
        usersPendingTab().getInvitedOnColumn().shouldBe(exactText("Invited on"));

        usersPendingTab().getNoPendingUserIcon().shouldBe(visible);
        usersPendingTab().getNoPendingUserText().shouldBe(visible);
        usersPendingTab().getNoPendingUserText().shouldBe(exactText("No Pending Users Yet"));
    }

    /** Assert Users - Drafts list. */
    public static void assertUsersDraftsPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

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

    /** Assert Users - Empty Drafts list. */
    public static void assertUsersEmptyDraftsPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

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

        usersDraftTab().getNoDraftYetIcon().shouldBe(visible);
        usersDraftTab().getNoDraftYetText().shouldBe(visible);
        usersDraftTab().getNoDraftYetText().shouldBe(exactText("No Drafts Yet"));
    }

    /** Assert Users - Registered, empty filters modal. */
    public static void assertRegisteredFiltersEmptyState() {
        assertElementsOnRoleModal();
        assertEmptyTeam();
        assertEmptyLabelModel();
        assertFiltersElementsDefaultState();
    }

    /** Assert Users - Registered, empty filters modal. */
    public static void assertElementsOnPendingFilter() {
        assertElementsOnRoleModal();
        assertElementsOnInvitedBySection();
        assertEmptyLabelModel();
        assertFiltersElementsDefaultState();
    }

    /** Assert Users - Drafts, empty filters modal. */
    public static void assertElementsDraftsFilters() {
        assertElementsOnCreatedBySection();
        assertElementsOnCreatedBySection();
        assertFiltersElementsDefaultState();
    }

    /** Assert single action for the Users - Registered. */
    public static void assertSingleActionUsersRegistered() {
        usersPage().getViewProfileAction().shouldBe(visible);
        usersPage().getViewProfileAction().shouldBe(exactText("View Profile"));
        usersPage().getUserSettingsAction().shouldBe(visible);
        usersPage().getUserSettingsAction().shouldBe(exactText("User Settings"));
        usersPage().getAssignPractisSetsAction().shouldBe(visible);
        usersPage().getAssignPractisSetsAction().shouldBe(exactText("Assign Practis Sets"));
        usersPage().getAssignLabelsAction().shouldBe(visible);
        usersPage().getAssignLabelsAction().shouldBe(exactText("Assign Labels"));
        usersPage().getNudgeUsersAction().shouldBe(visible);
        usersPage().getNudgeUsersAction().shouldBe(exactText("Nudge User"));
        usersPage().getExportReportAction().shouldBe(visible);
        usersPage().getExportReportAction().shouldBe(exactText("Export Report"));
        usersPage().getDeleteUserAction().shouldBe(visible);
        usersPage().getDeleteUserAction().shouldBe(exactText("Delete User"));
    }

    /** Assert single action for the Users - Registered. */
    public static void assertSingleActionUsersRegisteredNoLabels() {
        usersPage().getViewProfileAction().shouldBe(visible);
        usersPage().getViewProfileAction().shouldBe(exactText("View Profile"));
        usersPage().getUserSettingsAction().shouldBe(visible);
        usersPage().getUserSettingsAction().shouldBe(exactText("User Settings"));
        usersPage().getAssignPractisSetsAction().shouldBe(visible);
        usersPage().getAssignPractisSetsAction().shouldBe(exactText("Assign Practis Sets"));
        usersPage().getNudgeUsersAction().shouldBe(visible);
        usersPage().getNudgeUsersAction().shouldBe(exactText("Nudge User"));
        usersPage().getExportReportAction().shouldBe(visible);
        usersPage().getExportReportAction().shouldBe(exactText("Export Report"));
        usersPage().getDeleteUserAction().shouldBe(visible);
        usersPage().getDeleteUserAction().shouldBe(exactText("Delete User"));

        usersPage().getAssignedLabelsCounter().get(0).shouldBe(visible);
        usersPage().getAssignedLabelsCounter().get(0).shouldBe(exactText("1"));
    }

    /** Assert assigned label view. */
    public static void assignedAssignedLabelView() {
        assignPractisSetsAndDueDatesModule().getConfirmationSnackbarText().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getConfirmationSnackbarText()
                .shouldBe(exactText("Changes have been saved"));
        usersPage().getAssignedLabelsCounter().get(0).shouldBe(visible);
        usersPage().getAssignedLabelsCounter().get(0).shouldBe(exactText("1"));
    }

    /** Assert Snackbar. */
    public static void assertUserDeletedSnackbar() {
        usersRegisteredTab().getUserDeletedSnackbar().shouldBe(visible);
        usersRegisteredTab()
                .getUserDeletedSnackbar()
                .shouldBe(exactText("1 User has been deleted"));
    }

    /** Assert Snackbar. */
    public static void assertNoSearchResults() {
        usersRegisteredTab().getNoUsersFoundIcon().shouldBe(visible);
        usersRegisteredTab().getNoUsersFoundText().shouldBe(visible);
        usersRegisteredTab().getNoUsersFoundText().shouldBe(exactText("No Users Found"));
    }
}
