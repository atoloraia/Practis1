package com.practis.web.selenide.validator.company.users;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPendingTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertEmptyPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersPage;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;
import static com.practis.web.selenide.validator.selection.InvitedBySectionValidator.assertElementsOnInvitedBySection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertElementsOnRoleModal;

public class PendingTabValidator {

    /** Assert Users - Pending. */
    public static void assertPendingPage() {
        assertUsersPage();
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
    public static void assertEmptyPendingPage() {
        assertEmptyPage();
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

    /** Assert Users - Pending, empty filters modal. */
    public static void assertElementsOnPendingFilter() {
        assertElementsOnRoleModal();
        assertElementsOnInvitedBySection();
        assertEmptyLabelModel();
        assertFiltersElementsDefaultState();
    }

    /** Assert single action for the Users - Pending. */
    public static void assertSingleActionUsersPendingNoLabels() {
        usersRegisteredTab().getViewProfileAction().shouldBe(visible);
        usersRegisteredTab().getViewProfileAction().shouldBe(exactText("View Profile"));
        usersPendingTab().getResendInviteAction().shouldBe(visible);
        usersPendingTab().getResendInviteAction().shouldBe(exactText("Resend Invite"));
        usersPendingTab().getCopyInviteTextAction().shouldBe(visible);
        usersPendingTab().getCopyInviteTextAction().shouldBe(exactText("Copy Invite Text"));
        usersPendingTab().getRevokeAction().shouldBe(visible);
        usersPendingTab().getRevokeAction().shouldBe(exactText("Revoke"));
    }

    /** Assert single action for the Users - Pending. */
    public static void assertSingleActionUsersPending() {
        usersRegisteredTab().getViewProfileAction().shouldBe(visible);
        usersRegisteredTab().getViewProfileAction().shouldBe(exactText("View Profile"));
        usersPendingTab().getResendInviteAction().shouldBe(visible);
        usersPendingTab().getResendInviteAction().shouldBe(exactText("Resend Invite"));
        usersRegisteredTab().getAssignLabelsAction().shouldBe(visible);
        usersRegisteredTab().getAssignLabelsAction().shouldBe(exactText("Assign Labels"));
        usersPendingTab().getCopyInviteTextAction().shouldBe(visible);
        usersPendingTab().getCopyInviteTextAction().shouldBe(exactText("Copy Invite Text"));
        usersPendingTab().getRevokeAction().shouldBe(visible);
        usersPendingTab().getRevokeAction().shouldBe(exactText("Revoke"));
    }

    /** Assert bulk action for the Users - Pending. */
    public static void assertBulkActionUsersPending() {
        usersRegisteredTab().getAssignLabelsBulkAction().shouldBe(visible);
        usersRegisteredTab().getAssignLabelsBulkAction().shouldBe(exactText("Assign Labels"));
        usersPendingTab().getResendInviteBulkAction().shouldBe(visible);
        usersPendingTab().getResendInviteBulkAction().shouldBe(exactText("Resend Invites"));
        usersPendingTab().getRevokeBulkAction().shouldBe(visible);
        usersPendingTab().getRevokeBulkAction().shouldBe(exactText("Revoke"));
    }

    /** Assert Empty state - Pending Users list. */
    public static void assertEmptyStatePendingUsers() {
        usersRegisteredTab().getNoUsersIcon().shouldBe(visible);
        usersRegisteredTab().getNoUsersText().shouldBe(visible);
        usersRegisteredTab().getNoUsersText().shouldBe(exactText("No Pending Users Yet"));
    }
}
