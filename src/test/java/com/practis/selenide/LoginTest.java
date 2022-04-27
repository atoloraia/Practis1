package com.practis.selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.homePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;

import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SelenideTestClass
class LoginTest {

  private final WebCredentialsConfiguration credentials = webCredentialsConfig();

  @BeforeEach
  void beforeEach() {
    open(webApplicationConfig().getUrl());
    homePage().clickLogin();
  }

  @Test
  @TestRailTest(caseId = 25)
  @DisplayName("Success login")
  void loginSuccess_AdminCredentials() {
    loginPage().login(credentials.getLogin(), credentials.getPassword());

    loginPage().getLogo().should(not(exist));
  }

  @Test
  @TestRailTest(caseId = 37)
  @DisplayName("Failed login: Invalid Email")
  void loginFailure_InvalidEmail() {
    loginPage().login("email@tula.co", credentials.getPassword());

    snackbar().getMessage().shouldBe(exactText("This user wasn’t found."));
  }

  @Test
  @TestRailTest(caseId = 38)
  @DisplayName("Failed login: Invalid Password")
  void loginFailure_InvalidPassword() {
    loginPage().login(credentials.getLogin(), "wrongPassword");

    snackbar().getMessage().shouldBe(exactText("Incorrect password."));
  }

  @Test
  @TestRailTest(caseId = 40)
  @DisplayName("Failed login: Empty Credentials")
  void loginFailure_EmptyCredentials() {
    loginPage().login();

    loginPage().getEmailFieldValidationMessage()
        .shouldBe(exactText("We need your email address."));
    loginPage().getPasswordFieldValidationMessage()
        .shouldBe(exactText("We need your password."));
  }

  @Test
  @TestRailTest(caseId = 39)
  @DisplayName("Failed login: Invalid Email Format")
  void loginFailure_InvalidEmailPattern() {
    loginPage().login("invalidEmail");

    loginPage().getEmailFieldValidationMessage()
        .shouldBe(exactText("Enter a valid email address"));
    loginPage().getPasswordFieldValidationMessage()
        .shouldBe(exactText("We need your password."));
  }
}
