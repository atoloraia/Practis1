package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.page.AddMobileNumberPage;
import com.practis.web.selenide.page.HomePage;
import com.practis.web.selenide.page.LoginPage;
import com.practis.web.selenide.page.VerifyMobileNumberPage;
import com.practis.web.selenide.page.admin.AdminCreatePage;
import com.practis.web.selenide.page.admin.AdminEditPage;
import com.practis.web.selenide.page.admin.CompanyCreatePage;
import com.practis.web.selenide.page.admin.CompanySettingsPage;
import com.practis.web.selenide.page.admin.navigation.AdminPage;
import com.practis.web.selenide.page.admin.navigation.AiAssessmentPage;
import com.practis.web.selenide.page.admin.navigation.CompanyAccountsPage;
import com.practis.web.selenide.page.admin.navigation.LogsPage;
import com.practis.web.selenide.page.admin.navigation.ManageUserSettingsPage;
import com.practis.web.selenide.page.admin.navigation.ManageUsersPage;
import com.practis.web.selenide.page.company.FeedPage;
import com.practis.web.selenide.page.company.LibraryPage;
import com.practis.web.selenide.page.company.WebAiAssessmentPage;
import com.practis.web.selenide.page.company.challenge.ChallengeCreatePage;
import com.practis.web.selenide.page.company.challenge.ChallengeEditPage;
import com.practis.web.selenide.page.company.challenge.ChallengeTab;
import com.practis.web.selenide.page.company.practisset.PractisSetCreatePage;
import com.practis.web.selenide.page.company.practisset.PractisSetEditPage;
import com.practis.web.selenide.page.company.practisset.PractisSetTab;
import com.practis.web.selenide.page.company.reports.PractisSetSummaryReportPage;
import com.practis.web.selenide.page.company.reports.ReportsPage;
import com.practis.web.selenide.page.company.scenario.ScenarioCreatePage;
import com.practis.web.selenide.page.company.scenario.ScenarioEditPage;
import com.practis.web.selenide.page.company.scenario.ScenarioTab;
import com.practis.web.selenide.page.company.team.CreateNewTeamPage;
import com.practis.web.selenide.page.company.team.ManageTeamPage;
import com.practis.web.selenide.page.company.team.MembersTab;
import com.practis.web.selenide.page.company.team.PractisSetDetailsPage;
import com.practis.web.selenide.page.company.team.TeamPage;
import com.practis.web.selenide.page.company.team.TeamsPage;
import com.practis.web.selenide.page.company.team.TrainingTab;
import com.practis.web.selenide.page.company.user.InviteUserPage;
import com.practis.web.selenide.page.company.user.UserProfilePage;
import com.practis.web.selenide.page.company.user.UserSettingsPage;
import com.practis.web.selenide.page.company.users.UsersDraftTab;
import com.practis.web.selenide.page.company.users.UsersPage;
import com.practis.web.selenide.page.company.users.UsersPendingTab;
import com.practis.web.selenide.page.company.users.UsersRegisteredTab;

public class PageObjectFactory {

    private static HomePage HOME_PAGE;

    private static LoginPage LOGIN_PAGE;

    private static AddMobileNumberPage ADD_MOBILE_NUMBER_PAGE;
    private static VerifyMobileNumberPage VERIFY_MOBILE_NUMBER_PAGE;

    private static AdminCreatePage ADMIN_CREATE_PAGE;
    private static AdminPage ADMIN_PAGE;
    private static AdminEditPage ADMIN_EDIT_PAGE;

    private static CompanyCreatePage COMPANY_CREATE_PAGE;
    private static CompanyAccountsPage COMPANY_ACCOUNTS_PAGE;
    private static CompanySettingsPage COMPANY_EDIT_PAGE;

    private static TeamPage TEAM_PAGE;
    private static CreateNewTeamPage TEAM_CREATE_PAGE;
    private static ManageTeamPage MANAGE_TEAM_PAGE;
    private static MembersTab MEMBERS_TAB_PAGE;
    private static TrainingTab TRAINING_TAB_PAGE;
    private static PractisSetDetailsPage PRACTIS_SET_DETAILS_PAGE;

    private static ChallengeCreatePage CHALLENGE_CREATE_PAGE;
    private static ChallengeEditPage CHALLENGE_EDIT_PAGE;

    private static ScenarioCreatePage SCENARIO_CREATE_PAGE;
    private static ScenarioEditPage SCENARIO_EDIT_PAGE;

    private static PractisSetCreatePage PRACTIS_SET_PAGE;
    private static PractisSetEditPage PRACTIS_SET_EDIT_PAGE;
    private static PractisSetTab PRACTIS_SET_TAB;
    private static ScenarioTab SCENARIO_TAB;
    private static ChallengeTab CHALLENGE_TAB;

    private static InviteUserPage INVITE_USERS_PAGE;
    private static UserProfilePage USER_PROFILE_PAGE;
    private static UserSettingsPage USER_SETTINGS_PAGE;

    private static FeedPage FEED_PAGE;
    private static LibraryPage LIBRARY_PAGE;
    private static UsersPage USERS_PAGE;
    private static UsersRegisteredTab USERS_REGISTERED_TAB;
    private static UsersPendingTab USERS_PENDING_TAB;
    private static UsersDraftTab USERS_DRAFT_TAB;
    private static TeamsPage TEAMS_PAGE;

    private static LogsPage LOGS_PAGE;
    private static AiAssessmentPage AIASSESSMENT_PAGE;
    private static WebAiAssessmentPage WEBAIASSESSMENT_PAGE;

    private static ManageUsersPage MANAGE_USERS_PAGE;
    private static ManageUserSettingsPage MANAGE_USER_SETTINGS_PAGE;

    private static ReportsPage REPORTS_PAGE;
    private static PractisSetSummaryReportPage PRACTIS_SET_SUMMARY_REPORT_PAGE;

    /** Create or return existing HomePage. */
    public static HomePage homePage() {
        if (isNull(HOME_PAGE)) {
            HOME_PAGE = new HomePage();
        }
        return HOME_PAGE;
    }

    /** Create or return existing FeedPage. */
    public static FeedPage feedPage() {
        if (isNull(FEED_PAGE)) {
            FEED_PAGE = new FeedPage();
        }
        return FEED_PAGE;
    }

    /** Create or return existing Add Mobile Number page. */
    public static AddMobileNumberPage addMobileNumberPage() {
        if (isNull(ADD_MOBILE_NUMBER_PAGE)) {
            ADD_MOBILE_NUMBER_PAGE = new AddMobileNumberPage();
        }
        return ADD_MOBILE_NUMBER_PAGE;
    }

    /** Create or return existing Verify Mobile Number page. */
    public static VerifyMobileNumberPage verifyMobileNumberPage() {
        if (isNull(VERIFY_MOBILE_NUMBER_PAGE)) {
            VERIFY_MOBILE_NUMBER_PAGE = new VerifyMobileNumberPage();
        }
        return VERIFY_MOBILE_NUMBER_PAGE;
    }

    /** Create or return existing LibraryPage. */
    public static LibraryPage libraryPage() {
        if (isNull(LIBRARY_PAGE)) {
            LIBRARY_PAGE = new LibraryPage();
        }
        return LIBRARY_PAGE;
    }

    /** Create or return existing LoginPage. */
    public static LoginPage loginPage() {
        if (isNull(LOGIN_PAGE)) {
            LOGIN_PAGE = new LoginPage();
        }
        return LOGIN_PAGE;
    }

    /** Create or return existing AdminCreatePage. */
    public static AdminCreatePage adminCreatePage() {
        if (isNull(ADMIN_CREATE_PAGE)) {
            ADMIN_CREATE_PAGE = new AdminCreatePage();
        }
        return ADMIN_CREATE_PAGE;
    }

    /** Create or return existing AdminCreatePage. */
    public static AdminEditPage adminEditPage() {
        if (isNull(ADMIN_EDIT_PAGE)) {
            ADMIN_EDIT_PAGE = new AdminEditPage();
        }
        return ADMIN_EDIT_PAGE;
    }

    /** Create or return existing CompanyCreatePage. */
    public static CompanyCreatePage companyCreatePage() {
        if (isNull(COMPANY_CREATE_PAGE)) {
            COMPANY_CREATE_PAGE = new CompanyCreatePage();
        }
        return COMPANY_CREATE_PAGE;
    }

    /** Create or return existing CompanyPage. */
    public static CompanyAccountsPage companyAccountsPage() {
        if (isNull(COMPANY_ACCOUNTS_PAGE)) {
            COMPANY_ACCOUNTS_PAGE = new CompanyAccountsPage();
        }
        return COMPANY_ACCOUNTS_PAGE;
    }

    /** Create or return existing AdminPage. */
    public static AdminPage adminPage() {
        if (isNull(ADMIN_PAGE)) {
            ADMIN_PAGE = new AdminPage();
        }
        return ADMIN_PAGE;
    }

    /** Create or return existing CompanyEditPage. */
    public static CompanySettingsPage companySettingsPage() {
        if (isNull(COMPANY_EDIT_PAGE)) {
            COMPANY_EDIT_PAGE = new CompanySettingsPage();
        }
        return COMPANY_EDIT_PAGE;
    }

    /** Create or return existing Team Page. */
    public static TeamPage teamPage() {
        if (isNull(TEAM_PAGE)) {
            TEAM_PAGE = new TeamPage();
        }
        return TEAM_PAGE;
    }

    /** Create or return existing TeamCreatePage. */
    public static CreateNewTeamPage teamCreatePage() {
        if (isNull(TEAM_CREATE_PAGE)) {
            TEAM_CREATE_PAGE = new CreateNewTeamPage();
        }
        return TEAM_CREATE_PAGE;
    }

    /** Create or return existing ManageTeamPage. */
    public static ManageTeamPage manageTeamPage() {
        if (isNull(MANAGE_TEAM_PAGE)) {
            MANAGE_TEAM_PAGE = new ManageTeamPage();
        }
        return MANAGE_TEAM_PAGE;
    }

    /** Create or return existing Members Tab. */
    public static MembersTab membersTab() {
        if (isNull(MEMBERS_TAB_PAGE)) {
            MEMBERS_TAB_PAGE = new MembersTab();
        }
        return MEMBERS_TAB_PAGE;
    }

    /** Create or return existing Training Tab. */
    public static TrainingTab trainingTab() {
        if (isNull(TRAINING_TAB_PAGE)) {
            TRAINING_TAB_PAGE = new TrainingTab();
        }
        return TRAINING_TAB_PAGE;
    }

    /** Create or return existing Challenge Create Page. */
    public static ChallengeCreatePage challengeCreatePage() {
        if (isNull(CHALLENGE_CREATE_PAGE)) {
            CHALLENGE_CREATE_PAGE = new ChallengeCreatePage();
        }
        return CHALLENGE_CREATE_PAGE;
    }

    /** Create or return existing Challenge Edit Page. */
    public static ChallengeEditPage challengeEditPage() {
        if (isNull(CHALLENGE_EDIT_PAGE)) {
            CHALLENGE_EDIT_PAGE = new ChallengeEditPage();
        }
        return CHALLENGE_EDIT_PAGE;
    }

    /** Create or return existing Scenario Create Page. */
    public static ScenarioCreatePage scenarioCreatePage() {
        if (isNull(SCENARIO_CREATE_PAGE)) {
            SCENARIO_CREATE_PAGE = new ScenarioCreatePage();
        }
        return SCENARIO_CREATE_PAGE;
    }

    /** Create or return existing Challenge Edit Page. */
    public static ScenarioEditPage scenarioEditPage() {
        if (isNull(SCENARIO_EDIT_PAGE)) {
            SCENARIO_EDIT_PAGE = new ScenarioEditPage();
        }
        return SCENARIO_EDIT_PAGE;
    }

    /** Create or return existing Practis Set Page. */
    public static PractisSetCreatePage practisSetCreatePage() {
        if (isNull(PRACTIS_SET_PAGE)) {
            PRACTIS_SET_PAGE = new PractisSetCreatePage();
        }
        return PRACTIS_SET_PAGE;
    }

    /** Create or return existing Practis Set Edit Page. */
    public static PractisSetEditPage practisSetEditPage() {
        if (isNull(PRACTIS_SET_EDIT_PAGE)) {
            PRACTIS_SET_EDIT_PAGE = new PractisSetEditPage();
        }
        return PRACTIS_SET_EDIT_PAGE;
    }

    /** Create or return existing Practis Set Tab. */
    public static PractisSetTab practisSetTab() {
        if (isNull(PRACTIS_SET_TAB)) {
            PRACTIS_SET_TAB = new PractisSetTab();
        }
        return PRACTIS_SET_TAB;
    }

    /** Create or return existing Scenario Tab. */
    public static ScenarioTab scenarioTab() {
        if (isNull(SCENARIO_TAB)) {
            SCENARIO_TAB = new ScenarioTab();
        }
        return SCENARIO_TAB;
    }

    /** Create or return existing Challenge Tab. */
    public static ChallengeTab challengeTab() {
        if (isNull(CHALLENGE_TAB)) {
            CHALLENGE_TAB = new ChallengeTab();
        }
        return CHALLENGE_TAB;
    }

    /** Create or return existing Invite Users To The App Page. */
    public static InviteUserPage inviteUsersPage() {
        if (isNull(INVITE_USERS_PAGE)) {
            INVITE_USERS_PAGE = new InviteUserPage();
        }
        return INVITE_USERS_PAGE;
    }

    /** Create or return existing User Profile Page. */
    public static UserProfilePage userProfilePage() {
        if (isNull(USER_PROFILE_PAGE)) {
            USER_PROFILE_PAGE = new UserProfilePage();
        }
        return USER_PROFILE_PAGE;
    }

    /** Create or return existing Users Page. */
    public static UsersPage usersPage() {
        if (isNull(USERS_PAGE)) {
            USERS_PAGE = new UsersPage();
        }
        return USERS_PAGE;
    }

    /** Create or return existing Users Page: Registered. */
    public static UsersRegisteredTab usersRegisteredTab() {
        if (isNull(USERS_REGISTERED_TAB)) {
            USERS_REGISTERED_TAB = new UsersRegisteredTab();
        }
        return USERS_REGISTERED_TAB;
    }

    /** Create or return existing Users Page: Pending. */
    public static UsersPendingTab usersPendingTab() {
        if (isNull(USERS_PENDING_TAB)) {
            USERS_PENDING_TAB = new UsersPendingTab();
        }
        return USERS_PENDING_TAB;
    }

    /** Create or return existing Users Page: Draft. */
    public static UsersDraftTab usersDraftTab() {
        if (isNull(USERS_DRAFT_TAB)) {
            USERS_DRAFT_TAB = new UsersDraftTab();
        }
        return USERS_DRAFT_TAB;
    }

    /** Logs Page. */
    public static LogsPage logsPage() {
        if (isNull(LOGS_PAGE)) {
            LOGS_PAGE = new LogsPage();
        }
        return LOGS_PAGE;
    }

    /** AI Assessment Page. */
    public static AiAssessmentPage aiAssessmentPage() {
        if (isNull(AIASSESSMENT_PAGE)) {
            AIASSESSMENT_PAGE = new AiAssessmentPage();
        }
        return AIASSESSMENT_PAGE;
    }

    /** Web AI Assessment Page. */
    public static WebAiAssessmentPage webAiAssessmentPage() {
        if (isNull(WEBAIASSESSMENT_PAGE)) {
            WEBAIASSESSMENT_PAGE = new WebAiAssessmentPage();
        }
        return WEBAIASSESSMENT_PAGE;
    }

    /** Web Teams Page. */
    public static TeamsPage teamsPage() {
        if (isNull(TEAMS_PAGE)) {
            TEAMS_PAGE = new TeamsPage();
        }
        return TEAMS_PAGE;
    }

    /** Web Practis Set Details Page. */
    public static PractisSetDetailsPage practisSetDetailsPage() {
        if (isNull(PRACTIS_SET_DETAILS_PAGE)) {
            PRACTIS_SET_DETAILS_PAGE = new PractisSetDetailsPage();
        }
        return PRACTIS_SET_DETAILS_PAGE;
    }

    /** Create or return existing User Profile Page. */
    public static UserSettingsPage userSettingsPage() {
        if (isNull(USER_SETTINGS_PAGE)) {
            USER_SETTINGS_PAGE = new UserSettingsPage();
        }
        return USER_SETTINGS_PAGE;
    }

    /** Create or return existing Manage Users Page. */
    public static ManageUsersPage manageUsersPage() {
        if (isNull(MANAGE_USERS_PAGE)) {
            MANAGE_USERS_PAGE = new ManageUsersPage();
        }
        return MANAGE_USERS_PAGE;
    }

    /** Create or return existing Manage User Settings Page. */
    public static ManageUserSettingsPage manageUserSettingsPage() {
        if (isNull(MANAGE_USER_SETTINGS_PAGE)) {
            MANAGE_USER_SETTINGS_PAGE = new ManageUserSettingsPage();
        }
        return MANAGE_USER_SETTINGS_PAGE;
    }

    /** Web Reports Page. */
    public static ReportsPage reportsPage() {
        if (isNull(REPORTS_PAGE)) {
            REPORTS_PAGE = new ReportsPage();
        }
        return REPORTS_PAGE;
    }

    /** Web Reports Page. */
    public static PractisSetSummaryReportPage practisSetSummaryReportPage() {
        if (isNull(PRACTIS_SET_SUMMARY_REPORT_PAGE)) {
            PRACTIS_SET_SUMMARY_REPORT_PAGE = new PractisSetSummaryReportPage();
        }
        return PRACTIS_SET_SUMMARY_REPORT_PAGE;
    }
}
