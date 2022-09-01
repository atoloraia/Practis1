package com.practis.selenide.login;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.addMobileService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.LoginValidator.assertElementsLoginPage;

import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
class LoginTest {

  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  @BeforeEach
  void beforeEach() {
    open(webApplicationConfig().getUrl());
    localStorage().setItem("appVersion", "1.0.0");
    localStorage().setItem("apiPlatform", "develop");
    open(webApplicationConfig().getUrl());
  }

  /**
   * Login: Check WEB Elements 'Login' page.
   */
  @TestRailTest(caseId = 8623)
  @DisplayName("Check WEB Elements 'Login' page")
  void checkElementsLoginPage() {
    assertElementsLoginPage();
  }

  /**
   * Login: Success login.
   */
  @TestRailTest(caseId = 25)
  @DisplayName("Success login")
  void loginSuccess_AdminCredentials() {
    loginService().fillFormAndLogin(credentials.getLogin(), credentials.getPassword());
    addMobileService().clickAddLater();

    $("div[data-test ='user-profile-area-name']").should(exist);
  }

  /**
   * Login: Failed login: Invalid Email.
   */
  @TestRailTest(caseId = 37)
  @DisplayName("Failed login: Invalid Email")
  void loginFailure_InvalidEmail() {
    loginService().fillFormAndLogin("email@tula.co", credentials.getPassword());

    snackbar().getMessage()
        .shouldBe(exactText("That account doesn't exist. Enter a different email address."));
  }

  /**
   * Failed login: Invalid Password.
   */
  @TestRailTest(caseId = 38)
  @DisplayName("Failed login: Invalid Password")
  void loginFailure_InvalidPassword() {
    loginService().fillFormAndLogin(credentials.getLogin(), "wrongPassword");

    snackbar().getMessage().shouldBe(exactText("Incorrect password."));
  }

  /**
   * Failed login: Empty Credentials.
   */
  @TestRailTest(caseId = 40)
  @DisplayName("Failed login: Empty Credentials")
  void loginFailure_EmptyCredentials() {
    loginService().emptyFormLogin();

    loginPage().getEmailValidationMessage()
        .shouldBe(exactText("The Email Address field is required."));
    loginPage().getPasswordValidationMessage()
        .shouldBe(exactText("The Password field is required."));
  }

  /**
   * Failed login: Invalid Email Format.
   */
  @TestRailTest(caseId = 39)
  @DisplayName("Failed login: Invalid Email Format")
  void loginFailure_InvalidEmailPattern() {
    loginService().fillEmailLogin("invalidEmail");

    loginPage().getEmailValidationMessage()
        .shouldBe(exactText("Enter a valid Email Address."));
  }
}
