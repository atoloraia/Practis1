package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.page.HomePage;
import com.practis.web.selenide.page.LoginPage;
import com.practis.web.selenide.page.admin.AdminCreatePage;
import com.practis.web.selenide.page.admin.AdminEditPage;
import com.practis.web.selenide.page.admin.CompanyCreatePage;
import com.practis.web.selenide.page.admin.CompanyEditPage;
import com.practis.web.selenide.page.company.ChallengeCreatePage;
import com.practis.web.selenide.page.company.ChallengeEditPage;
import com.practis.web.selenide.page.company.TeamCreatePage;

public class PageObjectFactory {

  private static HomePage HOME_PAGE;

  private static LoginPage LOGIN_PAGE;

  private static AdminCreatePage ADMIN_CREATE_PAGE;
  private static AdminEditPage ADMIN_EDIT_PAGE;

  private static CompanyCreatePage COMPANY_CREATE_PAGE;
  private static CompanyEditPage COMPANY_EDIT_PAGE;

  private static TeamCreatePage TEAM_CREATE_PAGE;

  private static ChallengeCreatePage CHALLENGE_CREATE_PAGE;
  private static ChallengeEditPage CHALLENGE_EDIT_PAGE;


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
  public static TeamCreatePage teamCreatePage() {
    if (isNull(TEAM_CREATE_PAGE)) {
      TEAM_CREATE_PAGE = new TeamCreatePage();
    }
    return TEAM_CREATE_PAGE;
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

}
