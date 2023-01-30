package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

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
        usersPage().getSelectedTab().get(0).shouldBe(visible);
        usersPage().getSelectedTab().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
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

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Teams"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Practis Sets"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Role"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Registered on"));
        usersPage().getListColumns().get(5).shouldBe(visible);
        usersPage().getListColumns().get(5).shouldBe(exactText("Last Login"));

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
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
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

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Email Address"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Role"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Invited by"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Invited on"));

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
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getDisabledFiltersButton().shouldBe(visible);
        usersPage().getDisabledFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getDisabledListColumns().get(0).shouldBe(visible);
        usersPage().getDisabledListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getDisabledListColumns().get(1).shouldBe(visible);
        usersPage().getDisabledListColumns().get(1).shouldBe(exactText("Email Address"));
        usersPage().getDisabledListColumns().get(2).shouldBe(visible);
        usersPage().getDisabledListColumns().get(2).shouldBe(exactText("Role"));
        usersPage().getDisabledListColumns().get(3).shouldBe(visible);
        usersPage().getDisabledListColumns().get(3).shouldBe(exactText("Invited by"));
        usersPage().getDisabledListColumns().get(4).shouldBe(visible);
        usersPage().getDisabledListColumns().get(4).shouldBe(exactText("Invited on"));

        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(exactText("No Pending Users Yet"));
    }

    /** Assert Users - Drafts list. */
    public static void assertUsersDraftsPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
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

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Drafts"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Created by"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Created on"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Edited by"));
        usersPage().getListColumns().get(5).shouldBe(visible);
        usersPage().getListColumns().get(5).shouldBe(exactText("Edited on"));

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
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getDisabledFiltersButton().shouldBe(visible);
        usersPage().getDisabledFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getDisabledListColumns().get(0).shouldBe(visible);
        usersPage().getDisabledListColumns().get(0).shouldBe(exactText("Drafts"));
        usersPage().getDisabledListColumns().get(1).shouldBe(visible);
        usersPage().getDisabledListColumns().get(1).shouldBe(exactText("Users"));
        usersPage().getDisabledListColumns().get(2).shouldBe(visible);
        usersPage().getDisabledListColumns().get(2).shouldBe(exactText("Created by"));
        usersPage().getDisabledListColumns().get(3).shouldBe(visible);
        usersPage().getDisabledListColumns().get(3).shouldBe(exactText("Created on"));
        usersPage().getDisabledListColumns().get(4).shouldBe(visible);
        usersPage().getDisabledListColumns().get(4).shouldBe(exactText("Edited by"));
        usersPage().getDisabledListColumns().get(5).shouldBe(visible);
        usersPage().getDisabledListColumns().get(5).shouldBe(exactText("Edited on"));

        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(exactText("No Drafts Yet"));
    }

    /** Assert Users - Registered, empty filters modal. */
    public static void assertRegisteredFiltersEmptyState() {
        usersPage().getFilterTitles().get(0).shouldBe(visible);
        usersPage().getFilterTitles().get(0).shouldBe(exactText("Role"));
        usersPage().getFilterTitles().get(1).shouldBe(visible);
        usersPage().getFilterTitles().get(1).shouldBe(exactText("Teams"));
        usersPage().getFilterTitles().get(2).shouldBe(visible);
        usersPage().getFilterTitles().get(2).shouldBe(exactText("Labels"));

        usersPage().getRolesCheckboxes().get(0).shouldBe(visible);
        usersPage().getRolesCheckboxes().get(1).shouldBe(visible);
        usersPage().getRolesItems().get(0).shouldBe(visible);
        usersPage().getRolesItems().get(0).shouldBe(exactText("User"));
        usersPage().getRolesItems().get(1).shouldBe(visible);
        usersPage().getRolesItems().get(1).shouldBe(exactText("Admin"));

        usersPage().getTeamsSearchField().shouldBe(visible);
        usersPage().getTeamsSearchField().shouldBe(enabled);
        usersPage().getTeamsSearchField().shouldBe(attribute("font-size", "13px"));
        usersPage().getTeamsSearchFieldIcon().shouldBe(visible);
        usersPage().getTeamsSearchCrossButton().shouldBe(hidden);
        usersPage().getTeamsSelectionText().shouldBe(visible);
        usersPage().getTeamsSelectionText().shouldBe(exactText("No Teams selected"));
        usersPage().getTeamsSelectAllButton().shouldBe(visible);
        usersPage().getTeamsSelectAllButton().shouldBe(exactText("Select All"));
        usersPage().getTeamsItemCheckbox().get(0).shouldBe(visible);
        usersPage().getTeamsItemCheckbox().get(0).shouldBe(attribute("size", "12"));
        usersPage().getTeamsItemTitle().get(0).shouldBe(exactText("All Members"));

        usersPage().getLabelsSearchField().shouldBe(visible);
        usersPage().getLabelsSearchField().shouldBe(disabled);
        usersPage().getLabelsSearchField().shouldBe(attribute("font-size", "13px"));
        usersPage().getLabelsSearchFieldIcon().shouldBe(visible);
        usersPage().getLabelsSearchCrossButton().shouldBe(hidden);
        usersPage().getLabelsEmptyStateIcon().shouldBe(visible);
        usersPage().getLabelsEmptyStateText().shouldBe(visible);
        usersPage().getLabelsEmptyStateText().shouldBe(exactText("No Labels yet"));

        usersPage().getSelectedCountText().shouldBe(visible);
        usersPage().getSelectedCountText().shouldBe(exactText("0 Selected"));
        usersPage().getClearButton().shouldBe(visible);
        usersPage().getClearButton().shouldBe(disabled);
        usersPage().getClearButton().shouldBe(exactText("Clear"));
        usersPage().getClearButton().shouldBe(attribute("Color", "default"));
        usersPage().getClearButton().shouldBe(attribute("type", "submit"));
        usersPage().getApplyButton().shouldBe(visible);
        usersPage().getApplyButton().shouldBe(enabled);
        usersPage().getApplyButton().shouldBe(exactText("Apply Filter"));
        usersPage().getApplyButton().shouldBe(attribute("Color", "default"));
        usersPage().getApplyButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert Users - Registered, empty filters modal. */
    public static void assertPendingFiltersEmptyState() {
        usersPage().getFilterTitles().get(0).shouldBe(visible);
        usersPage().getFilterTitles().get(0).shouldBe(exactText("Role"));
        usersPage().getFilterTitles().get(1).shouldBe(visible);
        usersPage().getFilterTitles().get(1).shouldBe(exactText("Invited by"));
        usersPage().getFilterTitles().get(2).shouldBe(visible);
        usersPage().getFilterTitles().get(2).shouldBe(exactText("Labels"));

        usersPage().getRolesCheckboxes().get(0).shouldBe(visible);
        usersPage().getRolesCheckboxes().get(1).shouldBe(visible);
        usersPage().getRolesItems().get(0).shouldBe(visible);
        usersPage().getRolesItems().get(0).shouldBe(exactText("User"));
        usersPage().getRolesItems().get(1).shouldBe(visible);
        usersPage().getRolesItems().get(1).shouldBe(exactText("Admin"));

        usersPage().getInvitedBySearchField().shouldBe(visible);
        usersPage().getInvitedBySearchField().shouldBe(enabled);
        usersPage().getInvitedBySearchField().shouldBe(attribute("font-size", "13px"));
        usersPage().getInvitedBySearchFieldIcon().shouldBe(visible);
        usersPage().getInvitedBySearchCrossButton().shouldBe(hidden);
        usersPage().getInviteSelectionText().shouldBe(visible);
        usersPage().getInviteSelectionText().shouldBe(exactText("No Inviters selected"));
        usersPage().getInviteSelectAllButton().shouldBe(visible);
        usersPage().getInviteSelectAllButton().shouldBe(enabled);
        usersPage().getInviteSelectAllButton().shouldBe(exactText("Select All"));
        usersPage().getInviteItem().get(0).shouldBe(visible);
        usersPage().getInviteItem().get(0).shouldBe(matchText("(You)"));

        usersPage().getLabelsSearchField().shouldBe(visible);
        usersPage().getLabelsSearchField().shouldBe(disabled);
        usersPage().getLabelsSearchField().shouldBe(attribute("font-size", "13px"));
        usersPage().getLabelsSearchFieldIcon().shouldBe(visible);
        usersPage().getLabelsSearchCrossButton().shouldBe(hidden);
        usersPage().getLabelsEmptyStateIcon().shouldBe(visible);
        usersPage().getLabelsEmptyStateText().shouldBe(visible);
        usersPage().getLabelsEmptyStateText().shouldBe(exactText("No Labels yet"));

        usersPage().getPendingSelectedCountText().shouldBe(visible);
        usersPage().getPendingSelectedCountText().shouldBe(exactText("0 Selected"));
        usersPage().getPendingClearButton().shouldBe(visible);
        usersPage().getPendingClearButton().shouldBe(disabled);
        usersPage().getPendingClearButton().shouldBe(exactText("Clear"));
        usersPage().getPendingClearButton().shouldBe(attribute("Color", "default"));
        usersPage().getPendingClearButton().shouldBe(attribute("type", "submit"));
        usersPage().getApplyButton().shouldBe(visible);
        usersPage().getApplyButton().shouldBe(enabled);
        usersPage().getApplyButton().shouldBe(exactText("Apply Filter"));
        usersPage().getApplyButton().shouldBe(attribute("Color", "default"));
        usersPage().getApplyButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert Users - Drafts, empty filters modal. */
    public static void assertDraftsFiltersEmptyState() {
        usersPage().getFilterTitles().get(0).shouldBe(visible);
        usersPage().getFilterTitles().get(0).shouldBe(exactText("Created by"));
        usersPage().getFilterTitles().get(1).shouldBe(visible);
        usersPage().getFilterTitles().get(1).shouldBe(exactText("Edited by"));

        usersPage().getInvitedBySearchField().shouldBe(visible);
        usersPage().getInvitedBySearchField().shouldBe(enabled);
        usersPage().getInvitedBySearchField().shouldBe(attribute("font-size", "13px"));
        usersPage().getInvitedBySearchFieldIcon().shouldBe(visible);
        usersPage().getInvitedBySearchCrossButton().shouldBe(hidden);
        usersPage().getDraftsNoUsersSelectedText().shouldBe(visible);
        usersPage().getDraftsNoUsersSelectedText().shouldBe(exactText("No Users selected"));
        usersPage().getCreatedBySelectAllButton().shouldBe(visible);
        usersPage().getCreatedBySelectAllButton().shouldBe(enabled);
        usersPage().getCreatedBySelectAllButton().shouldBe(exactText("Select All"));
        usersPage().getCreatedByItem().get(0).shouldBe(visible);
        usersPage().getCreatedByItem().get(0).shouldBe(matchText("(you)"));

        usersPage().getPendingSelectedCountText().shouldBe(visible);
        usersPage().getPendingSelectedCountText().shouldBe(exactText("0 Selected"));
        usersPage().getPendingClearButton().shouldBe(visible);
        usersPage().getPendingClearButton().shouldBe(disabled);
        usersPage().getPendingClearButton().shouldBe(exactText("Clear"));
        usersPage().getPendingClearButton().shouldBe(attribute("Color", "default"));
        usersPage().getPendingClearButton().shouldBe(attribute("type", "submit"));
        usersPage().getApplyButton().shouldBe(visible);
        usersPage().getApplyButton().shouldBe(enabled);
        usersPage().getApplyButton().shouldBe(exactText("Apply Filter"));
        usersPage().getApplyButton().shouldBe(attribute("Color", "default"));
        usersPage().getApplyButton().shouldBe(attribute("type", "submit"));
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
    }
}
