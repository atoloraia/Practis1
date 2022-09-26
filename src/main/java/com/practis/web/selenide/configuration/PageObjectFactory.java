package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.page.AddMobileNumberPage;
import com.practis.web.selenide.page.HomePage;
import com.practis.web.selenide.page.LoginPage;
import com.practis.web.selenide.page.admin.AdminCreatePage;
import com.practis.web.selenide.page.admin.AdminEditPage;
import com.practis.web.selenide.page.admin.CompanyCreatePage;
import com.practis.web.selenide.page.admin.CompanyEditPage;
import com.practis.web.selenide.page.admin.navigation.AdminPage;
import com.practis.web.selenide.page.admin.navigation.CompanyPage;
import com.practis.web.selenide.page.company.FeedPage;
import com.practis.web.selenide.page.company.LibraryPage;
import com.practis.web.selenide.page.company.UsersPage;
import com.practis.web.selenide.page.company.challenge.ChallengeCreatePage;
import com.practis.web.selenide.page.company.challenge.ChallengeEditPage;
import com.practis.web.selenide.page.company.practisset.PractisSetCreatePage;
import com.practis.web.selenide.page.company.practisset.PractisSetEditPage;
import com.practis.web.selenide.page.company.scenario.ScenarioCreatePage;
import com.practis.web.selenide.page.company.scenario.ScenarioEditPage;
import com.practis.web.selenide.page.company.team.CreateNewTeamPage;
import com.practis.web.selenide.page.company.team.ManageTeamPage;
import com.practis.web.selenide.page.company.user.InviteUserPage;
import com.practis.web.selenide.page.company.user.UserProfilePage;

public class PageObjectFactory {

  private static HomePage HOME_PAGE;

  private static LoginPage LOGIN_PAGE;

  private static AddMobileNumberPage ADD_MOBILE_NUMBER_PAGE;

  private static AdminCreatePage ADMIN_CREATE_PAGE;
  private static AdminPage ADMIN_PAGE;
  private static AdminEditPage ADMIN_EDIT_PAGE;

  private static CompanyCreatePage COMPANY_CREATE_PAGE;
  private static CompanyPage COMPANY_PAGE;
  private static CompanyEditPage COMPANY_EDIT_PAGE;

  private static CreateNewTeamPage TEAM_CREATE_PAGE;
  private static ManageTeamPage MANAGE_TEAM_PAGE;

  private static ChallengeCreatePage CHALLENGE_CREATE_PAGE;
  private static ChallengeEditPage CHALLENGE_EDIT_PAGE;

  private static ScenarioCreatePage SCENARIO_CREATE_PAGE;
  private static ScenarioEditPage SCENARIO_EDIT_PAGE;

  private static PractisSetCreatePage PRACTIS_SET_PAGE;
  private static PractisSetEditPage PRACTIS_SET_EDIT_PAGE;

  private static InviteUserPage INVITE_USERS_PAGE;
  private static UserProfilePage USER_PROFILE_PAGE;

  private static FeedPage FEED_PAGE;
  private static LibraryPage LIBRARY_PAGE;
  private static UsersPage USERS_PAGE;

  /**
   * Create or return existing HomePage.
   */
  public static HomePage homePage() {
    if (isNull(HOME_PAGE)) {
      HOME_PAGE = new HomePage();
    }
    return HOME_PAGE;
  }

  /**
   * Create or return existing FeedPage.
   */
  public static FeedPage feedPage() {
    if (isNull(FEED_PAGE)) {
      FEED_PAGE = new FeedPage();
    }
    return FEED_PAGE;
  }

  /**
   * Create or return existing Add Mobile Number page.
   */
  public static AddMobileNumberPage addMobileNumberPage() {
    if (isNull(ADD_MOBILE_NUMBER_PAGE)) {
      ADD_MOBILE_NUMBER_PAGE = new AddMobileNumberPage();
    }
    return ADD_MOBILE_NUMBER_PAGE;
  }

  /**
   * Create or return existing LibraryPage.
   */
  public static LibraryPage libraryPage() {
    if (isNull(LIBRARY_PAGE)) {
      LIBRARY_PAGE = new LibraryPage();
    }
    return LIBRARY_PAGE;
  }

  /**
   * Create or return existing LoginPage.
   */
  public static LoginPage loginPage() {
    if (isNull(LOGIN_PAGE)) {
      LOGIN_PAGE = new LoginPage();
    }
    return LOGIN_PAGE;
  }

  /**
   * Create or return existing AdminCreatePage.
   */
  public static AdminCreatePage adminCreatePage() {
    if (isNull(ADMIN_CREATE_PAGE)) {
      ADMIN_CREATE_PAGE = new AdminCreatePage();
    }
    return ADMIN_CREATE_PAGE;
  }

  /**
   * Create or return existing AdminCreatePage.
   */
  public static AdminEditPage adminEditPage() {
    if (isNull(ADMIN_EDIT_PAGE)) {
      ADMIN_EDIT_PAGE = new AdminEditPage();
    }
    return ADMIN_EDIT_PAGE;
  }

  /**
   * Create or return existing CompanyCreatePage.
   */
  public static CompanyCreatePage companyCreatePage() {
    if (isNull(COMPANY_CREATE_PAGE)) {
      COMPANY_CREATE_PAGE = new CompanyCreatePage();
    }
    return COMPANY_CREATE_PAGE;
  }

  /**
   * Create or return existing CompanyPage.
   */
  public static CompanyPage companyPage() {
    if (isNull(COMPANY_PAGE)) {
      COMPANY_PAGE = new CompanyPage();
    }
    return COMPANY_PAGE;
  }

  /**
   * Create or return existing AdminPage.
   */
  public static AdminPage adminPage() {
    if (isNull(ADMIN_PAGE)) {
      ADMIN_PAGE = new AdminPage();
    }
    return ADMIN_PAGE;
  }

  /**
   * Create or return existing CompanyEditPage.
   */
  public static CompanyEditPage companyEditPage() {
    if (isNull(COMPANY_EDIT_PAGE)) {
      COMPANY_EDIT_PAGE = new CompanyEditPage();
    }
    return COMPANY_EDIT_PAGE;
  }

  /**
   * Create or return existing TeamCreatePage.
   */
  public static CreateNewTeamPage teamCreatePage() {
    if (isNull(TEAM_CREATE_PAGE)) {
      TEAM_CREATE_PAGE = new CreateNewTeamPage();
    }
    return TEAM_CREATE_PAGE;
  }

  /**
   * Create or return existing ManageTeamPage.
   */
  public static ManageTeamPage manageTeamPage() {
    if (isNull(MANAGE_TEAM_PAGE)) {
      MANAGE_TEAM_PAGE = new ManageTeamPage();
    }
    return MANAGE_TEAM_PAGE;
  }

  /**
   * Create or return existing Challenge Create Page.
   */
  public static ChallengeCreatePage challengeCreatePage() {
    if (isNull(CHALLENGE_CREATE_PAGE)) {
      CHALLENGE_CREATE_PAGE = new ChallengeCreatePage();
    }
    return CHALLENGE_CREATE_PAGE;
  }

  /**
   * Create or return existing Challenge Edit Page.
   */
  public static ChallengeEditPage challengeEditPage() {
    if (isNull(CHALLENGE_EDIT_PAGE)) {
      CHALLENGE_EDIT_PAGE = new ChallengeEditPage();
    }
    return CHALLENGE_EDIT_PAGE;
  }

  /**
   * Create or return existing Scenario Create Page.
   */
  public static ScenarioCreatePage scenarioCreatePage() {
    if (isNull(SCENARIO_CREATE_PAGE)) {
      SCENARIO_CREATE_PAGE = new ScenarioCreatePage();
    }
    return SCENARIO_CREATE_PAGE;
  }

  /**
   * Create or return existing Challenge Edit Page.
   */
  public static ScenarioEditPage scenarioEditPage() {
    if (isNull(SCENARIO_EDIT_PAGE)) {
      SCENARIO_EDIT_PAGE = new ScenarioEditPage();
    }
    return SCENARIO_EDIT_PAGE;
  }

  /**
   * Create or return existing Practis Set Page.
   */
  public static PractisSetCreatePage practisSetCreatePage() {
    if (isNull(PRACTIS_SET_PAGE)) {
      PRACTIS_SET_PAGE = new PractisSetCreatePage();
    }
    return PRACTIS_SET_PAGE;
  }

  /**
   * Create or return existing Practis Set Edit Page.
   */
  public static PractisSetEditPage practisSetEditPage() {
    if (isNull(PRACTIS_SET_EDIT_PAGE)) {
      PRACTIS_SET_EDIT_PAGE = new PractisSetEditPage();
    }
    return PRACTIS_SET_EDIT_PAGE;
  }

  /**
   * Create or return existing Invite Users To The App Page.
   */
  public static InviteUserPage inviteUsersPage() {
    if (isNull(INVITE_USERS_PAGE)) {
      INVITE_USERS_PAGE = new InviteUserPage();
    }
    return INVITE_USERS_PAGE;
  }

  /**
   * Create or return existing User Profile Page.
   */
  public static UserProfilePage userProfilePage() {
    if (isNull(USER_PROFILE_PAGE)) {
      USER_PROFILE_PAGE = new UserProfilePage();
    }
    return USER_PROFILE_PAGE;
  }

  /**
   * Create or return existing Users Page.
   */
  public static UsersPage usersPage() {
    if (isNull(USERS_PAGE)) {
      USERS_PAGE = new UsersPage();
    }
    return USERS_PAGE;
  }

}
