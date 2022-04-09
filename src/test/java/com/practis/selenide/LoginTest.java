package com.practis.selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;

import com.practis.configuration.testrail.TestRailTest;
import com.practis.dto.login.LoginCredentials;
import com.practis.support.PractisTestClassNew;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import com.practis.web.selenide.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@PractisTestClassNew
class LoginTest {

  private final LoginService loginService = new LoginService();
  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  @BeforeEach
  void beforeEach() {
    loginService.initLogin();
  }


  @TestRailTest(caseId = 25, inputDataClass = LoginCredentials.class)
  void loginSuccess_AdminCredentials(final LoginCredentials input) {
    loginService.login(input.getUsername(), input.getPassword());

    loginService.getLoginPage().getLogo().should(not(exist));
  }

  @Test
  void loginFailure_InvalidEmail() {
    loginService.login("email@tula.co", credentials.getPassword());

    loginService.getSnackbar().shouldBe(exactText("This user wasn’t found."));
  }

  @Test
  void loginFailure_InvalidPassword() {
    loginService.login(credentials.getLogin(), "wrongPassword");

    loginService.getSnackbar().shouldBe(exactText("Incorrect password."));
  }

  @Test
  void loginFailure_EmptyCredentials() {
    loginService.login();

    loginService.getEmailFieldValidationMessage()
        .shouldBe(exactText("We need your email address."));
    loginService.getPasswordFieldValidationMessage()
        .shouldBe(exactText("We need your password."));
  }

  @Test
  void loginFailure_InvalidEmailPattern() {
    loginService.login("invalidEmail");

    loginService.getEmailFieldValidationMessage()
        .shouldBe(exactText("Enter a valid email address"));
    loginService.getPasswordFieldValidationMessage()
        .shouldBe(exactText("We need your password."));
  }
}
