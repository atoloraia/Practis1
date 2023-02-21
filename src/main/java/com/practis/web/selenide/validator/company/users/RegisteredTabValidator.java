package com.practis.web.selenide.validator.company.users;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersPage;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertElementsOnRoleModal;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeam;

public class RegisteredTabValidator {
    /** Assert Users - Registered. */
    public static void assertUsersRegisteredPage() {
        assertUsersPage();
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
    }

    /** Assert Users - Registered, empty filters modal. */
    public static void assertRegisteredFiltersEmptyState() {
        assertElementsOnRoleModal();
        assertEmptyTeam();
        assertEmptyLabelModel();
        assertFiltersElementsDefaultState();
    }

    /** Assert single action for the Users - Registered. */
    public static void assertSingleActionUsersRegistered() {
        assertSingleActionNoLabels();
        usersRegisteredTab().getAssignLabelsAction().shouldBe(visible);
        usersRegisteredTab().getAssignLabelsAction().shouldBe(exactText("Assign Labels"));
    }

    /** Assert single action for the Users - Registered. */
    public static void assertSingleActionNoLabels() {
        usersRegisteredTab().getViewProfileAction().shouldBe(visible);
        usersRegisteredTab().getViewProfileAction().shouldBe(exactText("View Profile"));
        usersRegisteredTab().getUserSettingsAction().shouldBe(visible);
        usersRegisteredTab().getUserSettingsAction().shouldBe(exactText("User Settings"));
        usersRegisteredTab().getAssignPractisSetsAction().shouldBe(visible);
        usersRegisteredTab()
                .getAssignPractisSetsAction()
                .shouldBe(exactText("Assign Practis Sets"));
        usersRegisteredTab().getNudgeUsersAction().shouldBe(visible);
        usersRegisteredTab().getNudgeUsersAction().shouldBe(exactText("Nudge User"));
        usersRegisteredTab().getExportReportAction().shouldBe(visible);
        usersRegisteredTab().getExportReportAction().shouldBe(exactText("Export Report"));
        usersRegisteredTab().getDeleteUserAction().shouldBe(visible);
        usersRegisteredTab().getDeleteUserAction().shouldBe(exactText("Delete User"));
    }

    /** Assert bulk action for the Users - Registered. */
    public static void assertBulkActionUsersRegistered() {
        usersRegisteredTab().getAssignPsBulkAction().shouldBe(visible);
        usersRegisteredTab().getAssignPsBulkAction().shouldBe(exactText("Assign Practis Sets"));
        usersRegisteredTab().getAssignLabelsBulkAction().shouldBe(visible);
        usersRegisteredTab().getAssignLabelsBulkAction().shouldBe(exactText("Assign Labels"));
        usersRegisteredTab().getNudgeUsersBulkAction().shouldBe(visible);
        usersRegisteredTab().getNudgeUsersBulkAction().shouldBe(exactText("Nudge Users"));
        usersRegisteredTab().getExportReportBulkAction().shouldBe(visible);
        usersRegisteredTab().getExportReportBulkAction().shouldBe(exactText("Export Report"));
        usersRegisteredTab().getDeleteUsersBulkAction().shouldBe(visible);
        usersRegisteredTab().getDeleteUsersBulkAction().shouldBe(exactText("Delete Users"));
    }

    /** Assert no search results. */
    public static void assertNoSearchResults() {
        usersRegisteredTab().getNoUsersFoundIcon().shouldBe(visible);
        usersRegisteredTab().getNoUsersFoundText().shouldBe(visible);
        usersRegisteredTab().getNoUsersFoundText().shouldBe(exactText("No Users Found"));
    }
}
