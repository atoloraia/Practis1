package com.practis.selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.utils.ConfigurationLoader.loadConfig;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import com.practis.configuration.testrail.TestRailTest;
import com.practis.support.PractisTestClassNew;
import com.practis.web.selenide.configuration.model.Credentials;
import com.practis.web.selenide.page.HomePage;
import com.practis.web.selenide.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@PractisTestClassNew
class LoginTest {

  private static final Credentials CREDENTIALS = loadConfig(
      "/configuration/web/credentials.json", Credentials.class);

  @BeforeAll
  public static void beforeAll() {
    browser = webApplicationConfig().getBrowser();
  }

  private final HomePage homePage = new HomePage();
  private final LoginPage loginPage = new LoginPage();

  @BeforeEach
  void beforeEach() {
    open(webApplicationConfig().getUrl());
    homePage.clickLogin();
  }

  @Test
  @TestRailTest("login_success")
  void loginSuccess_AdminCredentials() {
    loginPage.fillLoginForm(CREDENTIALS.getLogin(), CREDENTIALS.getPassword()).login();

    loginPage.getLogo().should(not(exist));
  }

  @Test
  void loginFailure_InvalidEmail() {
    loginPage.fillLoginForm("email@tula.co", CREDENTIALS.getPassword()).login();

    loginPage.getSnackbar().getMessage().shouldBe(exactText("This user wasn’t found."));
  }

  @Test
  void loginFailure_InvalidPassword() {
    loginPage.fillLoginForm(CREDENTIALS.getLogin(), "wrongPassword").login();

    loginPage.getSnackbar().getMessage().shouldBe(exactText("Incorrect password."));
  }

  @Test
  void loginFailure_EmptyCredentials() {
    loginPage.login();

    loginPage.getValidationMessages().get(0).shouldBe(exactText("We need your email address."));
    loginPage.getValidationMessages().get(1).shouldBe(exactText("We need your password."));
  }

  @Test
  void loginFailure_InvalidEmailPattern() {
    loginPage.fillEmail("invalidEmail").login();

    loginPage.getValidationMessages().get(0).shouldBe(exactText("Enter a valid email address"));
    loginPage.getValidationMessages().get(1).shouldBe(exactText("We need your password."));
  }

  @AfterEach
  void afterEach() {
    closeWebDriver();
  }
}
