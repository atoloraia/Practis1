package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.page.HomePage;
import com.practis.web.selenide.page.LoginPage;
import com.practis.web.selenide.page.admin.AdminCreatePage;
import com.practis.web.selenide.page.admin.AdminEditPage;

public class PageObjectFactory {

  private static HomePage HOME_PAGE;

  private static LoginPage LOGIN_PAGE;

  private static AdminCreatePage ADMIN_CREATE_PAGE;
  private static AdminEditPage ADMIN_EDIT_PAGE;

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
}
