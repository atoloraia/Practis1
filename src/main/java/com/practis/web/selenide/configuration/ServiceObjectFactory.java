package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.selection.AssignPractisSetsAndDueDatesModule;
import com.practis.web.selenide.service.AddMobileNumberService;
import com.practis.web.selenide.service.CreateAnAccountService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.SearchService;
import com.practis.web.selenide.service.admin.AdminService;
import com.practis.web.selenide.service.admin.AdministratorsService;
import com.practis.web.selenide.service.admin.ManageUserSettingsService;
import com.practis.web.selenide.service.admin.ManageUsersService;
import com.practis.web.selenide.service.admin.company.CompanyAccoutsService;
import com.practis.web.selenide.service.admin.company.CompanyConfigurationService;
import com.practis.web.selenide.service.admin.company.CompanySettingsService;
import com.practis.web.selenide.service.admin.company.CreateCompanyService;
import com.practis.web.selenide.service.company.BottomMenuService;
import com.practis.web.selenide.service.company.CompanySettingsCompanyService;
import com.practis.web.selenide.service.company.DraftUsersService;
import com.practis.web.selenide.service.company.InviteUserService;
import com.practis.web.selenide.service.company.LabelPanelService;
import com.practis.web.selenide.service.company.LibraryService;
import com.practis.web.selenide.service.company.NavigationCompanyService;
import com.practis.web.selenide.service.company.NudgeUserService;
import com.practis.web.selenide.service.company.PendingUsersService;
import com.practis.web.selenide.service.company.PractisSetReportService;
import com.practis.web.selenide.service.company.RegisteredUsersService;
import com.practis.web.selenide.service.company.UsersService;
import com.practis.web.selenide.service.company.challenge.ChallengeSettingsService;
import com.practis.web.selenide.service.company.challenge.ChallengeTabService;
import com.practis.web.selenide.service.company.challenge.CreateChallengeService;
import com.practis.web.selenide.service.company.practisset.CreatePractisSetService;
import com.practis.web.selenide.service.company.practisset.PractisSetTabService;
import com.practis.web.selenide.service.company.reports.BillingReportService;
import com.practis.web.selenide.service.company.reports.PractisSetSummaryReportService;
import com.practis.web.selenide.service.company.reports.ReportsService;
import com.practis.web.selenide.service.company.reports.TeamLeaderEngagementReportService;
import com.practis.web.selenide.service.company.reports.UserActivityReportService;
import com.practis.web.selenide.service.company.scenario.CreateScenarioService;
import com.practis.web.selenide.service.company.scenario.ScenarioTabService;
import com.practis.web.selenide.service.company.team.CreateTeamsService;
import com.practis.web.selenide.service.company.team.ManageTeamService;
import com.practis.web.selenide.service.company.team.MembersTabService;
import com.practis.web.selenide.service.company.team.OverdueTabService;
import com.practis.web.selenide.service.company.team.PractisSetDetailsService;
import com.practis.web.selenide.service.company.team.TeamsPageService;
import com.practis.web.selenide.service.company.team.TrainingTabService;
import com.practis.web.selenide.service.popup.ActivateCompanyPopUpService;
import com.practis.web.selenide.service.popup.DeactivateCompanyPopUpService;
import com.practis.web.selenide.service.popup.EditPhotoPopUpService;
import com.practis.web.selenide.service.popup.SaveAsDraftPopUpService;
import com.practis.web.selenide.service.popup.UnsavedProgressPopUpService;
import com.practis.web.selenide.service.popup.YouNeedMoreSeatsPopUpService;
import com.practis.web.selenide.service.selection.AssignModuleService;
import com.practis.web.selenide.service.selection.AssignPsAndDueDateService;
import com.practis.web.selenide.service.selection.CompaniesStatusService;
import com.practis.web.selenide.service.selection.LabelSelectionService;
import com.practis.web.selenide.service.selection.PractisSetSelectionService;
import com.practis.web.selenide.service.selection.RoleUserModuleService;
import com.practis.web.selenide.service.selection.StatusSelectionService;
import com.practis.web.selenide.service.selection.TeamSelectionService;

public class ServiceObjectFactory {

    private static NavigationCompanyService NAVIGATION_COMPANY_SERVICE;
    private static AdminService ADMIN_SERVICE;
    private static AdministratorsService ADMINISTRATORS_SERVICE;

    private static CreateCompanyService COMPANY_SERVICE;
    private static CompanySettingsService COMPANY_SETTINGS_SERVICE;
    private static CompanyAccoutsService COMPANY_ACCOUNT_SERVICE;
    private static CompanyConfigurationService COMPANY_CONFIGURATION_SERVICE;

    private static CreateTeamsService TEAMS_SERVICE;
    private static ManageTeamService MANAGE_TEAM_SERVICE;
    private static MembersTabService MEMBERS_TAB_SERVICE;
    private static TrainingTabService TRAINING_TAB_SERVICE;
    private static TeamsPageService TEAM_PAGE_SERVICE;
    private static OverdueTabService OVERDUE_TAB_SERVICE;
    private static LibraryService LIBRARY_SERVICE;
    private static CreateChallengeService CHALLENGE_SERVICE;
    private static CreateScenarioService SCENARIO_SERVICE;
    private static CreatePractisSetService CREATE_PRACTIS_SET_SERVICE;
    private static PractisSetTabService PRACTIS_SET_TAB_SERVICE;
    private static ScenarioTabService SCENARIO_TAB_SERVICE;
    private static ChallengeTabService CHALLENGE_TAB_SERVICE;
    private static LabelPanelService LABEL_SERVICE;
    private static LoginService LOGIN_SERVICE;
    private static SearchService SEARCH_SERVICE;
    private static CreateAnAccountService CREATE_AN_ACCOUNT_SERVICE;
    private static AddMobileNumberService ADD_MOBILE_SERVICE;
    private static InviteUserService INVITE_USER_SERVICE;
    private static TeamSelectionService INVITE_USER_TEAM_SERVICE;
    private static PractisSetSelectionService INVITE_USER_PRACTIS_SET_SERVICE;
    private static LabelSelectionService INVITE_USER_LABEL_SERVICE;
    private static RoleUserModuleService INVITE_USER_ROLE_SERVICE;
    private static AssignModuleService ASSIGN_USER_MODULE_SERVICE;
    private static StatusSelectionService STATUS_SELECTION_SERVICE;
    private static CompaniesStatusService COMPANIES_STATUS_SERVICE;
    private static SaveAsDraftPopUpService SAVE_AS_DRAFT_SERVICE;
    private static ActivateCompanyPopUpService ACTIVATE_COMPANY_SERVICE;
    private static DeactivateCompanyPopUpService DEACTIVATE_COMPANY_SERVICE;
    private static UnsavedProgressPopUpService UNSAVED_PROGRESS_SERVICE;
    private static AssignPractisSetsAndDueDatesModule ASSIGN_PRACTIS_SET_SERVICE;
    private static UsersService USERS_SERVICE;
    private static RegisteredUsersService REGISTERED_USERS_SERVICE;
    private static PendingUsersService PENDING_USERS_SERVICE;
    private static DraftUsersService DRAFT_USERS_SERVICE;
    private static AssignPsAndDueDateService ASSIGN_PS_AND_DUE_DATE_SERVICE;
    private static NudgeUserService NUDGE_USER_SERVICE;
    private static ManageUsersService MANAGE_USERS_SERVICE;
    private static ManageUserSettingsService MANAGE_USER_SETTINGS_SERVICE;
    private static PractisSetDetailsService PRACTIS_SET_DETAILS_SERVICE;
    private static ReportsService REPORTS_SERVICE;
    private static PractisSetSummaryReportService PRACTIS_SET_SUMMARY_REPORT_SERVICE;
    private static UserActivityReportService USER_ACTIVITY_REPORT_SERVICE;
    private static TeamLeaderEngagementReportService TEAM_LEADER_ENGAGEMENT_REPORT_SERVICE;
    private static BillingReportService BILLING_REPORT_SERVICE;
    private static PractisSetReportService PRACTIS_SET_REPORT_SERVICE;
    private static BottomMenuService BOTTOM_MENU_SERVICE;
    private static YouNeedMoreSeatsPopUpService YOU_NEED_MORE_SEATS_POP_UP_SERVICE;
    private static EditPhotoPopUpService EDIT_PHOTO_POP_UP_SERVICE;
    private static CompanySettingsCompanyService COMPANY_SETTINGS_COMPANY_SERVICE;
    private static ChallengeSettingsService CHALLENGE_SETTINGS_SERVICE;

    // Teams
    /** Create or return existing Create Teams Service. */
    public static NavigationCompanyService navigationCompanyService() {
        if (isNull(NAVIGATION_COMPANY_SERVICE)) {
            NAVIGATION_COMPANY_SERVICE = new NavigationCompanyService();
        }
        return NAVIGATION_COMPANY_SERVICE;
    }

    /** Create or return existing Create Teams Service. */
    public static CreateTeamsService createTeamsService() {
        if (isNull(TEAMS_SERVICE)) {
            TEAMS_SERVICE = new CreateTeamsService();
        }
        return TEAMS_SERVICE;
    }

    /** Create or return existing ManageTeamService. */
    public static ManageTeamService manageTeamService() {
        if (isNull(MANAGE_TEAM_SERVICE)) {
            MANAGE_TEAM_SERVICE = new ManageTeamService();
        }
        return MANAGE_TEAM_SERVICE;
    }

    /** Create or return existing ManageTeamService. */
    public static MembersTabService membersTabService() {
        if (isNull(MEMBERS_TAB_SERVICE)) {
            MEMBERS_TAB_SERVICE = new MembersTabService();
        }
        return MEMBERS_TAB_SERVICE;
    }

    /** Create or return existing ManageTeamService. */
    public static TrainingTabService trainingTabService() {
        if (isNull(TRAINING_TAB_SERVICE)) {
            TRAINING_TAB_SERVICE = new TrainingTabService();
        }
        return TRAINING_TAB_SERVICE;
    }

    /** Create or return existing TeamsPageService. */
    public static TeamsPageService teamsPageService() {
        if (isNull(TEAM_PAGE_SERVICE)) {
            TEAM_PAGE_SERVICE = new TeamsPageService();
        }
        return TEAM_PAGE_SERVICE;
    }

    /** Create or return existing OverdueTabService. */
    public static OverdueTabService overdueTabService() {
        if (isNull(OVERDUE_TAB_SERVICE)) {
            OVERDUE_TAB_SERVICE = new OverdueTabService();
        }
        return OVERDUE_TAB_SERVICE;
    }

    /** Create or return existing TeamSelectionService. */
    public static TeamSelectionService teamModuleService() {
        if (isNull(INVITE_USER_TEAM_SERVICE)) {
            INVITE_USER_TEAM_SERVICE = new TeamSelectionService();
        }
        return INVITE_USER_TEAM_SERVICE;
    }

    /** Create or return existing AdminService. */
    public static AdminService adminService() {
        if (isNull(ADMIN_SERVICE)) {
            ADMIN_SERVICE = new AdminService();
        }
        return ADMIN_SERVICE;
    }

    /** Create or return existing Administrators Service. */
    public static AdministratorsService administratorsService() {
        if (isNull(ADMINISTRATORS_SERVICE)) {
            ADMINISTRATORS_SERVICE = new AdministratorsService();
        }
        return ADMINISTRATORS_SERVICE;
    }

    /** Create or return existing CompanyService. */
    public static CreateCompanyService companyCreateService() {
        if (isNull(COMPANY_SERVICE)) {
            COMPANY_SERVICE = new CreateCompanyService();
        }
        return COMPANY_SERVICE;
    }

    /** Create or return existing Company Settings Service. */
    public static CompanySettingsService companySettingsService() {
        if (isNull(COMPANY_SETTINGS_SERVICE)) {
            COMPANY_SETTINGS_SERVICE = new CompanySettingsService();
        }
        return COMPANY_SETTINGS_SERVICE;
    }

    /** Create or return existing Company Accounts Service. */
    public static CompanyAccoutsService companyAccoutsService() {
        if (isNull(COMPANY_ACCOUNT_SERVICE)) {
            COMPANY_ACCOUNT_SERVICE = new CompanyAccoutsService();
        }
        return COMPANY_ACCOUNT_SERVICE;
    }

    /** Create or return existing Company Configuration Service. */
    public static CompanyConfigurationService companyConfigurationService() {
        if (isNull(COMPANY_CONFIGURATION_SERVICE)) {
            COMPANY_CONFIGURATION_SERVICE = new CompanyConfigurationService();
        }
        return COMPANY_CONFIGURATION_SERVICE;
    }

    /** Create or return existing CreateChallengeService. */
    public static CreateChallengeService createChallengeService() {
        if (isNull(CHALLENGE_SERVICE)) {
            CHALLENGE_SERVICE = new CreateChallengeService();
        }
        return CHALLENGE_SERVICE;
    }

    /** Create or return existing Library Service. */
    public static LibraryService libraryService() {
        if (isNull(LIBRARY_SERVICE)) {
            LIBRARY_SERVICE = new LibraryService();
        }
        return LIBRARY_SERVICE;
    }

    /** Create or return existing ScenarioService. */
    public static CreateScenarioService createScenarioService() {
        if (isNull(SCENARIO_SERVICE)) {
            SCENARIO_SERVICE = new CreateScenarioService();
        }
        return SCENARIO_SERVICE;
    }

    /** Create or return existing Create Practis Set page. */
    public static CreatePractisSetService createPractisSetService() {
        if (isNull(CREATE_PRACTIS_SET_SERVICE)) {
            CREATE_PRACTIS_SET_SERVICE = new CreatePractisSetService();
        }
        return CREATE_PRACTIS_SET_SERVICE;
    }

    /** Create or return existing Practis Set tab. */
    public static PractisSetTabService practisSetTabService() {
        if (isNull(PRACTIS_SET_TAB_SERVICE)) {
            PRACTIS_SET_TAB_SERVICE = new PractisSetTabService();
        }
        return PRACTIS_SET_TAB_SERVICE;
    }

    /** Create or return existing Scenario tab. */
    public static ScenarioTabService scenarioTabService() {
        if (isNull(SCENARIO_TAB_SERVICE)) {
            SCENARIO_TAB_SERVICE = new ScenarioTabService();
        }
        return SCENARIO_TAB_SERVICE;
    }

    /** Create or return existing Challenge tab. */
    public static ChallengeTabService challengeTabService() {
        if (isNull(CHALLENGE_TAB_SERVICE)) {
            CHALLENGE_TAB_SERVICE = new ChallengeTabService();
        }
        return CHALLENGE_TAB_SERVICE;
    }

    /** Create or return existing Labels Service. */
    public static LabelPanelService labelPanelService() {
        if (isNull(LABEL_SERVICE)) {
            LABEL_SERVICE = new LabelPanelService();
        }
        return LABEL_SERVICE;
    }

    /** Create or return existing Login Service. */
    public static LoginService loginService() {
        if (isNull(LOGIN_SERVICE)) {
            LOGIN_SERVICE = new LoginService();
        }
        return LOGIN_SERVICE;
    }

    /** Create or return existing Search Service. */
    public static SearchService searchService() {
        if (isNull(SEARCH_SERVICE)) {
            SEARCH_SERVICE = new SearchService();
        }
        return SEARCH_SERVICE;
    }

    /** Create or return existing Login Service. */
    public static CreateAnAccountService createAnAccountService() {
        if (isNull(CREATE_AN_ACCOUNT_SERVICE)) {
            CREATE_AN_ACCOUNT_SERVICE = new CreateAnAccountService();
        }
        return CREATE_AN_ACCOUNT_SERVICE;
    }

    /** Create or return existing Add Mobile Number Service. */
    public static AddMobileNumberService addMobileService() {
        if (isNull(ADD_MOBILE_SERVICE)) {
            ADD_MOBILE_SERVICE = new AddMobileNumberService();
        }
        return ADD_MOBILE_SERVICE;
    }

    /** Create or return existing Invite Service. */
    public static InviteUserService userService() {
        if (isNull(INVITE_USER_SERVICE)) {
            INVITE_USER_SERVICE = new InviteUserService();
        }
        return INVITE_USER_SERVICE;
    }

    /** Create or return existing Invite: Practis Set Module Service. */
    public static PractisSetSelectionService psModuleService() {
        if (isNull(INVITE_USER_PRACTIS_SET_SERVICE)) {
            INVITE_USER_PRACTIS_SET_SERVICE = new PractisSetSelectionService();
        }
        return INVITE_USER_PRACTIS_SET_SERVICE;
    }

    /** Create or return existing Invite:Label Module Service. */
    public static LabelSelectionService labelModuleService() {
        if (isNull(INVITE_USER_LABEL_SERVICE)) {
            INVITE_USER_LABEL_SERVICE = new LabelSelectionService();
        }
        return INVITE_USER_LABEL_SERVICE;
    }

    /** Create or return existing Invite:Assign: Role Service. */
    public static RoleUserModuleService roleModuleService() {
        if (isNull(INVITE_USER_ROLE_SERVICE)) {
            INVITE_USER_ROLE_SERVICE = new RoleUserModuleService();
        }
        return INVITE_USER_ROLE_SERVICE;
    }

    /** Create or return existing Invite:Assign Module Service. */
    public static AssignModuleService assignUserModuleService() {
        if (isNull(ASSIGN_USER_MODULE_SERVICE)) {
            ASSIGN_USER_MODULE_SERVICE = new AssignModuleService();
        }
        return ASSIGN_USER_MODULE_SERVICE;
    }

    /** Create or return existing status section. */
    public static StatusSelectionService statusModuleService() {
        if (isNull(STATUS_SELECTION_SERVICE)) {
            STATUS_SELECTION_SERVICE = new StatusSelectionService();
        }
        return STATUS_SELECTION_SERVICE;
    }

    /** Create or return existing status section. */
    public static CompaniesStatusService companyStatusService() {
        if (isNull(COMPANIES_STATUS_SERVICE)) {
            COMPANIES_STATUS_SERVICE = new CompaniesStatusService();
        }
        return COMPANIES_STATUS_SERVICE;
    }

    /** Create or return existing Save as Draft pop-up. */
    public static SaveAsDraftPopUpService saveAsDraftService() {
        if (isNull(SAVE_AS_DRAFT_SERVICE)) {
            SAVE_AS_DRAFT_SERVICE = new SaveAsDraftPopUpService();
        }
        return SAVE_AS_DRAFT_SERVICE;
    }

    /** Create or return existing Deactivate Company pop-up. */
    public static DeactivateCompanyPopUpService deactivateCompanyPopUpService() {
        if (isNull(DEACTIVATE_COMPANY_SERVICE)) {
            DEACTIVATE_COMPANY_SERVICE = new DeactivateCompanyPopUpService();
        }
        return DEACTIVATE_COMPANY_SERVICE;
    }

    /** Create or return existing Activate Company pop-up. */
    public static ActivateCompanyPopUpService activateCompanyPopUpService() {
        if (isNull(ACTIVATE_COMPANY_SERVICE)) {
            ACTIVATE_COMPANY_SERVICE = new ActivateCompanyPopUpService();
        }
        return ACTIVATE_COMPANY_SERVICE;
    }

    /** Create or return existing Unsaved Progress pop-up. */
    public static UnsavedProgressPopUpService unsavedProgressPopUpService() {
        if (isNull(UNSAVED_PROGRESS_SERVICE)) {
            UNSAVED_PROGRESS_SERVICE = new UnsavedProgressPopUpService();
        }
        return UNSAVED_PROGRESS_SERVICE;
    }

    /** Create or return existing ASSIGN PRACTIS SET pop-up. */
    public static AssignPractisSetsAndDueDatesModule AssignPractisSetsAndDueDatesModule() {
        if (isNull(ASSIGN_PRACTIS_SET_SERVICE)) {
            ASSIGN_PRACTIS_SET_SERVICE = new AssignPractisSetsAndDueDatesModule();
        }
        return ASSIGN_PRACTIS_SET_SERVICE;
    }

    /** Create or return existing Registered Users Service. */
    public static RegisteredUsersService registeredUsersService() {
        if (isNull(REGISTERED_USERS_SERVICE)) {
            REGISTERED_USERS_SERVICE = new RegisteredUsersService();
        }
        return REGISTERED_USERS_SERVICE;
    }

    /** Assign Practis Sets and Due Date Service */
    public static AssignPsAndDueDateService assignPsAndDueDateService() {
        if (isNull(ASSIGN_PS_AND_DUE_DATE_SERVICE)) {
            ASSIGN_PS_AND_DUE_DATE_SERVICE = new AssignPsAndDueDateService();
        }
        return ASSIGN_PS_AND_DUE_DATE_SERVICE;
    }

    /** Nudge User pop-up. */
    public static NudgeUserService nudgeUserService() {
        if (isNull(NUDGE_USER_SERVICE)) {
            NUDGE_USER_SERVICE = new NudgeUserService();
        }
        return NUDGE_USER_SERVICE;
    }

    /** Create or return existing Users Service. */
    public static UsersService usersService() {
        if (isNull(USERS_SERVICE)) {
            USERS_SERVICE = new UsersService();
        }
        return USERS_SERVICE;
    }

    /** Create or return existing Pending Users Service. */
    public static PendingUsersService pendingUsersService() {
        if (isNull(PENDING_USERS_SERVICE)) {
            PENDING_USERS_SERVICE = new PendingUsersService();
        }
        return PENDING_USERS_SERVICE;
    }

    /** Create or return existing Draft Users Service. */
    public static DraftUsersService draftUsersService() {
        if (isNull(DRAFT_USERS_SERVICE)) {
            DRAFT_USERS_SERVICE = new DraftUsersService();
        }
        return DRAFT_USERS_SERVICE;
    }

    /** Create or return existing Manage Users Service. */
    public static ManageUsersService manageUsersService() {
        if (isNull(MANAGE_USERS_SERVICE)) {
            MANAGE_USERS_SERVICE = new ManageUsersService();
        }
        return MANAGE_USERS_SERVICE;
    }

    /** Create or return existing Manage Users Service. */
    public static ManageUserSettingsService manageUserSettingsService() {
        if (isNull(MANAGE_USER_SETTINGS_SERVICE)) {
            MANAGE_USER_SETTINGS_SERVICE = new ManageUserSettingsService();
        }
        return MANAGE_USER_SETTINGS_SERVICE;
    }

    /** Create or return existing Practis Set Details Service. */
    public static PractisSetDetailsService practisSetDetailsService() {
        if (isNull(PRACTIS_SET_DETAILS_SERVICE)) {
            PRACTIS_SET_DETAILS_SERVICE = new PractisSetDetailsService();
        }
        return PRACTIS_SET_DETAILS_SERVICE;
    }

    /** Reports Service. */
    public static ReportsService reportsService() {
        if (isNull(REPORTS_SERVICE)) {
            REPORTS_SERVICE = new ReportsService();
        }
        return REPORTS_SERVICE;
    }

    /** Practis Set Summary Report Service. */
    public static PractisSetSummaryReportService practisSetSummaryReportService() {
        if (isNull(PRACTIS_SET_SUMMARY_REPORT_SERVICE)) {
            PRACTIS_SET_SUMMARY_REPORT_SERVICE = new PractisSetSummaryReportService();
        }
        return PRACTIS_SET_SUMMARY_REPORT_SERVICE;
    }

    /** User Activity Report Service. */
    public static UserActivityReportService userActivityReportService() {
        if (isNull(USER_ACTIVITY_REPORT_SERVICE)) {
            USER_ACTIVITY_REPORT_SERVICE = new UserActivityReportService();
        }
        return USER_ACTIVITY_REPORT_SERVICE;
    }

    /** Team Leader Engagement Report Service. */
    public static TeamLeaderEngagementReportService teamLeaderEngagementReportService() {
        if (isNull(TEAM_LEADER_ENGAGEMENT_REPORT_SERVICE)) {
            TEAM_LEADER_ENGAGEMENT_REPORT_SERVICE = new TeamLeaderEngagementReportService();
        }
        return TEAM_LEADER_ENGAGEMENT_REPORT_SERVICE;
    }

    /** Billing Report Service. */
    public static BillingReportService billingReportService() {
        if (isNull(BILLING_REPORT_SERVICE)) {
            BILLING_REPORT_SERVICE = new BillingReportService();
        }
        return BILLING_REPORT_SERVICE;
    }

    /** Practis Set Report Service. */
    public static PractisSetReportService practisSetReportService() {
        if (isNull(PRACTIS_SET_REPORT_SERVICE)) {
            PRACTIS_SET_REPORT_SERVICE = new PractisSetReportService();
        }
        return PRACTIS_SET_REPORT_SERVICE;
    }

    /** Bottom Menu Service. */
    public static BottomMenuService bottomMenuService() {
        if (isNull(BOTTOM_MENU_SERVICE)) {
            BOTTOM_MENU_SERVICE = new BottomMenuService();
        }
        return BOTTOM_MENU_SERVICE;
    }

    /** You Need More Setas Pop up Service. */
    public static YouNeedMoreSeatsPopUpService youNeedMoreSeatsPopUpService() {
        if (isNull(YOU_NEED_MORE_SEATS_POP_UP_SERVICE)) {
            YOU_NEED_MORE_SEATS_POP_UP_SERVICE = new YouNeedMoreSeatsPopUpService();
        }
        return YOU_NEED_MORE_SEATS_POP_UP_SERVICE;
    }

    /** Edit Photo Service. */
    public static EditPhotoPopUpService editPhotoPopUpService() {
        if (isNull(EDIT_PHOTO_POP_UP_SERVICE)) {
            EDIT_PHOTO_POP_UP_SERVICE = new EditPhotoPopUpService();
        }
        return EDIT_PHOTO_POP_UP_SERVICE;
    }

    /** Edit Photo Service. */
    public static CompanySettingsCompanyService companySettingsCompanyService() {
        if (isNull(COMPANY_SETTINGS_COMPANY_SERVICE)) {
            COMPANY_SETTINGS_COMPANY_SERVICE = new CompanySettingsCompanyService();
        }
        return COMPANY_SETTINGS_COMPANY_SERVICE;
    }

    /** Challenge Settings Service. */
    public static ChallengeSettingsService challengeSettingsService() {
        if (isNull(CHALLENGE_SETTINGS_SERVICE)) {
            CHALLENGE_SETTINGS_SERVICE = new ChallengeSettingsService();
        }
        return CHALLENGE_SETTINGS_SERVICE;
    }
}
