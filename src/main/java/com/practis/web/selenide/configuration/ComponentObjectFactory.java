package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.AreYouSurePopUp;
import com.practis.web.selenide.component.AssignUsersAndDueDatesModule;
import com.practis.web.selenide.component.AssignUsersModule;
import com.practis.web.selenide.component.BottomProfileMenuAdmin;
import com.practis.web.selenide.component.CompanySelector;
import com.practis.web.selenide.component.ConfirmBulkActionPopUp;
import com.practis.web.selenide.component.CreateScenarioPopUp;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.KeepTrackPopUp;
import com.practis.web.selenide.component.LabelPanel;
import com.practis.web.selenide.component.LibraryTabs;
import com.practis.web.selenide.component.NavigationAdmin;
import com.practis.web.selenide.component.NavigationCompany;
import com.practis.web.selenide.component.NewItemSelector;
import com.practis.web.selenide.component.NudgePopUp;
import com.practis.web.selenide.component.PacingDropdown;
import com.practis.web.selenide.component.PublishPractisSetPopUp;
import com.practis.web.selenide.component.ScenarioConfirmationPopUp;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.component.Snackbar;
import com.practis.web.selenide.component.selection.AssignPractisSetsAndDueDatesModule;
import com.practis.web.selenide.component.selection.LabelModule;
import com.practis.web.selenide.component.selection.PsModule;
import com.practis.web.selenide.component.selection.RoleModule;
import com.practis.web.selenide.component.selection.ScenarioModule;
import com.practis.web.selenide.component.selection.TeamModule;
import com.practis.web.selenide.component.selection.status.FeedStatusModule;
import com.practis.web.selenide.component.selection.status.LibraryStatusModule;
import com.practis.web.selenide.component.selection.status.OverdueModule;
import com.practis.web.selenide.component.selection.status.RegistrationStatus;
import com.practis.web.selenide.component.selection.status.TeamMemberStatus;
import com.practis.web.selenide.component.team.DuplicateTeamPopUp;
import com.practis.web.selenide.component.team.WarningDeletePopUp;
import com.practis.web.selenide.component.team.WarningRemoveFromTeamPopUp;
import com.practis.web.selenide.component.user.invite.FailedInvitingUsersPopUp;
import com.practis.web.selenide.component.user.invite.InvitingUsersPopUp;
import com.practis.web.selenide.component.user.invite.SaveAsDraftPopUp;
import com.practis.web.selenide.component.user.invite.UnsavedProgressPopUp;

public class ComponentObjectFactory {

    private static CompanySelector COMPANY_SELECTOR;
    private static NewItemSelector NEW_ITEM_SELECTOR;
    private static Snackbar SNACKBAR;
    private static NavigationAdmin NAVIGATION;
    private static NavigationCompany NAVIGATION_COMPANIES;
    private static Search SEARCH;
    private static Grid GRID;
    private static LabelPanel LABEL;
    private static LibraryTabs LIBRARY_CHALLENGE;
    private static ScenarioConfirmationPopUp SCENARIO_CONFIRMATION_POPUP;
    private static PublishPractisSetPopUp PUBLISH_PS_POPUP;
    private static AssignUsersModule ASSIGN_USERS_MODULE;
    private static AssignUsersAndDueDatesModule ASSIGN_USERS_AND_DUE_DATE_MODULE;
    private static NavigationAdmin NAVIGATION_ADMIN;
    private static TeamModule INVITE_USER_TEAM_MODULE;
    private static RoleModule INVITE_USER_ROLE_MODULE;
    private static PsModule INVITE_USER_PS_MODULE;
    private static LabelModule INVITE_USER_LABEL_MODULE;
    private static LibraryStatusModule LIBRARY_STATUS_MODULE;
    private static FeedStatusModule FEED_STATUS_MODULE;
    private static ScenarioModule FEED_SCENARIO_MODULE;
    private static AreYouSurePopUp ARE_YOU_SURE_POPUP;
    private static SaveAsDraftPopUp SAVE_AS_DRAFT_POPUP;
    private static KeepTrackPopUp KEEP_TRACK_POPUP;
    private static DuplicateTeamPopUp DUPLICATE_TEAM_POPUP;
    private static WarningDeletePopUp WARNING_DELETE_POPUP;
    private static ConfirmBulkActionPopUp CONFIRM_BULK_ACTION_POPUP;
    private static InvitingUsersPopUp INVITING_USERS_POPUP;
    private static CreateScenarioPopUp CREATE_SCENARIO_POPUP;
    private static FailedInvitingUsersPopUp FAILED_INVITING_USERS_POPUP;
    private static UnsavedProgressPopUp UNSAVED_PROGRESS_POPUP;
    private static PacingDropdown PACING_DROPDOWN;
    private static BottomProfileMenuAdmin CURRENT_USER_VIEW;
    private static OverdueModule OVERDUE_MODULE;
    private static RegistrationStatus REGISTRATION_STATUS;
    private static TeamMemberStatus TEAM_MEMBER_STATUS;
    private static NudgePopUp NUDGE_POPUP;
    private static WarningRemoveFromTeamPopUp REMOVE_FROM_TEAM_POPUP;
    private static AssignPractisSetsAndDueDatesModule ASSIGN_PRACTIS_SETS_AND_DUE_DATES_MODULE;

    /** Create or return existing CompanySelector. */
    public static CompanySelector companySelector() {
        if (isNull(COMPANY_SELECTOR)) {
            COMPANY_SELECTOR = new CompanySelector();
        }
        return COMPANY_SELECTOR;
    }

    /** Create or return existing NewItemSelector. */
    public static NewItemSelector newItemSelector() {
        if (isNull(NEW_ITEM_SELECTOR)) {
            NEW_ITEM_SELECTOR = new NewItemSelector();
        }
        return NEW_ITEM_SELECTOR;
    }

    /** Create or return existing Snackbar. */
    public static Snackbar snackbar() {
        if (isNull(SNACKBAR)) {
            SNACKBAR = new Snackbar();
        }
        return SNACKBAR;
    }

    /** Create or return existing Navigation Admin. */
    public static NavigationAdmin navigationAdmin() {
        if (isNull(NAVIGATION)) {
            NAVIGATION = new NavigationAdmin();
        }
        return NAVIGATION;
    }

    /** Create or return existing Navigation Companies. */
    public static NavigationCompany navigationCompany() {
        if (isNull(NAVIGATION_COMPANIES)) {
            NAVIGATION_COMPANIES = new NavigationCompany();
        }
        return NAVIGATION_COMPANIES;
    }

    /** Create or return existing Search. */
    public static Search search() {
        if (isNull(SEARCH)) {
            SEARCH = new Search();
        }
        return SEARCH;
    }

    /** Create or return existing Pacing. */
    public static PacingDropdown pacingDropdown() {
        if (isNull(PACING_DROPDOWN)) {
            PACING_DROPDOWN = new PacingDropdown();
        }
        return PACING_DROPDOWN;
    }

    /** Create or return existing Grid. */
    public static Grid grid() {
        if (isNull(GRID)) {
            GRID = new Grid();
        }
        return GRID;
    }

    /** Create or return existing Label. */
    public static LabelPanel labelPanel() {
        if (isNull(LABEL)) {
            LABEL = new LabelPanel();
        }
        return LABEL;
    }

    /** Create or return existing library tabs. */
    public static LibraryTabs libraryTabs() {
        if (isNull(LIBRARY_CHALLENGE)) {
            LIBRARY_CHALLENGE = new LibraryTabs();
        }
        return LIBRARY_CHALLENGE;
    }

    /** Create or return existing Scenario Discard model. */
    public static ScenarioConfirmationPopUp scenarioConfirmationPopUp() {
        if (isNull(SCENARIO_CONFIRMATION_POPUP)) {
            SCENARIO_CONFIRMATION_POPUP = new ScenarioConfirmationPopUp();
        }
        return SCENARIO_CONFIRMATION_POPUP;
    }

    /** Create or return existing Publish PS Pop up model. */
    public static PublishPractisSetPopUp publishPractisSetPopUp() {
        if (isNull(PUBLISH_PS_POPUP)) {
            PUBLISH_PS_POPUP = new PublishPractisSetPopUp();
        }
        return PUBLISH_PS_POPUP;
    }

    /** Create or return existing 'Assign users to PS' Module. */
    public static AssignUsersModule assignUsersModule() {
        if (isNull(ASSIGN_USERS_MODULE)) {
            ASSIGN_USERS_MODULE = new AssignUsersModule();
        }
        return ASSIGN_USERS_MODULE;
    }

    /** Create or return existing 'Assign users to PS' Module. */
    public static AssignUsersAndDueDatesModule assignUsersAndDueDatesModule() {
        if (isNull(ASSIGN_USERS_AND_DUE_DATE_MODULE)) {
            ASSIGN_USERS_AND_DUE_DATE_MODULE = new AssignUsersAndDueDatesModule();
        }
        return ASSIGN_USERS_AND_DUE_DATE_MODULE;
    }

    /** Create or return existing 'Navigation Admin Panel. */
    public static NavigationAdmin navigationAdminSideBar() {
        if (isNull(NAVIGATION_ADMIN)) {
            NAVIGATION_ADMIN = new NavigationAdmin();
        }
        return NAVIGATION_ADMIN;
    }

    /** Create or return existing 'CurrentUser View - Asmin'. */
    public static BottomProfileMenuAdmin bottomProfileMenu() {
        if (isNull(CURRENT_USER_VIEW)) {
            CURRENT_USER_VIEW = new BottomProfileMenuAdmin();
        }
        return CURRENT_USER_VIEW;
    }

    /** Create or return existing Invite User: Team Module. */
    public static TeamModule teamModule() {
        if (isNull(INVITE_USER_TEAM_MODULE)) {
            INVITE_USER_TEAM_MODULE = new TeamModule();
        }
        return INVITE_USER_TEAM_MODULE;
    }

    /** Create or return existing Invite User: Role Module. */
    public static RoleModule inviteUserRoleModule() {
        if (isNull(INVITE_USER_ROLE_MODULE)) {
            INVITE_USER_ROLE_MODULE = new RoleModule();
        }
        return INVITE_USER_ROLE_MODULE;
    }

    /** Create or return existing Invite User: PS Module. */
    public static PsModule inviteUserPsModule() {
        if (isNull(INVITE_USER_PS_MODULE)) {
            INVITE_USER_PS_MODULE = new PsModule();
        }
        return INVITE_USER_PS_MODULE;
    }

    /** Create or return existing Invite User: Label Module. */
    public static LabelModule labelModule() {
        if (isNull(INVITE_USER_LABEL_MODULE)) {
            INVITE_USER_LABEL_MODULE = new LabelModule();
        }
        return INVITE_USER_LABEL_MODULE;
    }

    /** Return existing Feed: Scenario Module. */
    public static ScenarioModule scenarioModule() {
        if (isNull(FEED_SCENARIO_MODULE)) {
            FEED_SCENARIO_MODULE = new ScenarioModule();
        }
        return FEED_SCENARIO_MODULE;
    }

    /** Return existing Library: Status Module. */
    public static LibraryStatusModule libraryStatusModule() {
        if (isNull(LIBRARY_STATUS_MODULE)) {
            LIBRARY_STATUS_MODULE = new LibraryStatusModule();
        }
        return LIBRARY_STATUS_MODULE;
    }

    /** Return existing Feed: Status Module. */
    public static FeedStatusModule feedStatusModule() {
        if (isNull(FEED_STATUS_MODULE)) {
            FEED_STATUS_MODULE = new FeedStatusModule();
        }
        return FEED_STATUS_MODULE;
    }

    /** Create or return existing Are You Sure popup. */
    public static AreYouSurePopUp areYouSurePopUp() {
        if (isNull(ARE_YOU_SURE_POPUP)) {
            ARE_YOU_SURE_POPUP = new AreYouSurePopUp();
        }
        return ARE_YOU_SURE_POPUP;
    }

    /** Create or return existing Are You Sure popup. */
    public static SaveAsDraftPopUp saveAsDraftPopUp() {
        if (isNull(SAVE_AS_DRAFT_POPUP)) {
            SAVE_AS_DRAFT_POPUP = new SaveAsDraftPopUp();
        }
        return SAVE_AS_DRAFT_POPUP;
    }

    /** Create or return existing Keep Track popup. */
    public static KeepTrackPopUp keepTrackPopUp() {
        if (isNull(KEEP_TRACK_POPUP)) {
            KEEP_TRACK_POPUP = new KeepTrackPopUp();
        }
        return KEEP_TRACK_POPUP;
    }

    /** Create or return existing Duplicate popup. */
    public static DuplicateTeamPopUp duplicatePopUp() {
        if (isNull(DUPLICATE_TEAM_POPUP)) {
            DUPLICATE_TEAM_POPUP = new DuplicateTeamPopUp();
        }
        return DUPLICATE_TEAM_POPUP;
    }

    /** Create or return existing Delete popup. */
    public static WarningDeletePopUp deletePopUp() {
        if (isNull(WARNING_DELETE_POPUP)) {
            WARNING_DELETE_POPUP = new WarningDeletePopUp();
        }
        return WARNING_DELETE_POPUP;
    }

    /** Create or return existing Confirm Bulk Action popup. */
    public static ConfirmBulkActionPopUp confirmBulkActionPopUp() {
        if (isNull(CONFIRM_BULK_ACTION_POPUP)) {
            CONFIRM_BULK_ACTION_POPUP = new ConfirmBulkActionPopUp();
        }
        return CONFIRM_BULK_ACTION_POPUP;
    }

    /** Create or return existing Inviting Users popup. */
    public static InvitingUsersPopUp invitingUsersPopUpPopUp() {
        if (isNull(INVITING_USERS_POPUP)) {
            INVITING_USERS_POPUP = new InvitingUsersPopUp();
        }
        return INVITING_USERS_POPUP;
    }

    /** Create or return existing Inviting Users popup. */
    public static CreateScenarioPopUp createScenarioPopUp() {
        if (isNull(CREATE_SCENARIO_POPUP)) {
            CREATE_SCENARIO_POPUP = new CreateScenarioPopUp();
        }
        return CREATE_SCENARIO_POPUP;
    }

    /** Create or return existing Failed: Inviting Users popup. */
    public static FailedInvitingUsersPopUp failedInvitingUsersPopUp() {
        if (isNull(FAILED_INVITING_USERS_POPUP)) {
            FAILED_INVITING_USERS_POPUP = new FailedInvitingUsersPopUp();
        }
        return FAILED_INVITING_USERS_POPUP;
    }

    /** Create or return existing Unsaved Progress popup. */
    public static UnsavedProgressPopUp unsavedProgressPopUp() {
        if (isNull(UNSAVED_PROGRESS_POPUP)) {
            UNSAVED_PROGRESS_POPUP = new UnsavedProgressPopUp();
        }
        return UNSAVED_PROGRESS_POPUP;
    }

    /** Return existing Overdue: Members/Training Tab. */
    public static OverdueModule overdueModule() {
        if (isNull(OVERDUE_MODULE)) {
            OVERDUE_MODULE = new OverdueModule();
        }
        return OVERDUE_MODULE;
    }

    /** Return existing Registration Status: Members/Training Tab. */
    public static RegistrationStatus registrationStatus() {
        if (isNull(REGISTRATION_STATUS)) {
            REGISTRATION_STATUS = new RegistrationStatus();
        }
        return REGISTRATION_STATUS;
    }

    /** Return existing Member/Pr Set Status: Members/Training Tab. */
    public static TeamMemberStatus teamMemberStatus() {
        if (isNull(TEAM_MEMBER_STATUS)) {
            TEAM_MEMBER_STATUS = new TeamMemberStatus();
        }
        return TEAM_MEMBER_STATUS;
    }

    /** Return existing NudgePopUp. */
    public static NudgePopUp nudgePopup() {
        if (isNull(NUDGE_POPUP)) {
            NUDGE_POPUP = new NudgePopUp();
        }
        return NUDGE_POPUP;
    }

    /** Return existing WarningRemoveFromTeamPopUp. */
    public static WarningRemoveFromTeamPopUp removeFromTeamPopup() {
        if (isNull(REMOVE_FROM_TEAM_POPUP)) {
            REMOVE_FROM_TEAM_POPUP = new WarningRemoveFromTeamPopUp();
        }
        return REMOVE_FROM_TEAM_POPUP;
    }

    /** Return existing AssignPractisSetsAndDueDatesModule. */
    public static AssignPractisSetsAndDueDatesModule assignPractisSetsAndDueDatesModule() {
        if (isNull(ASSIGN_PRACTIS_SETS_AND_DUE_DATES_MODULE)) {
            ASSIGN_PRACTIS_SETS_AND_DUE_DATES_MODULE = new AssignPractisSetsAndDueDatesModule();
        }
        return ASSIGN_PRACTIS_SETS_AND_DUE_DATES_MODULE;
    }
}
