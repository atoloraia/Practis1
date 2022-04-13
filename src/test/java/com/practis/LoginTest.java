package com.practis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.practis.configuration.web.properties.WebApplicationProperties;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.support.TestRailTest;
import com.practis.web.WebApplication;
import com.practis.web.page.LoginPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class LoginTest {

  private final WebApplication webApplication;

  private final LoginPage loginPage;
  private final WebApplicationProperties properties;

  @BeforeEach
  void init() {
    webApplication.initLogin();
  }

  /**
   * Log in with valid email and password. Check a user is logged in.
   */
  @TestRailTest(caseId = 25)
  @PractisTest
  void loginSuccess_AdminCredentials() {
    //when
    loginPage.login(properties.getAdminCredentials());

    //then
    assertFalse(loginPage.isOnPage());
  }

  /**
   * Log in with not existing email and password. Check validation messages.
   */
  @TestRailTest(caseId = 37)
  @PractisTest
  void loginFailure_InvalidEmail() {
    //when
    loginPage.login("email@tula.co", "password");

    //then
    assertEquals("This user wasn’t found.", loginPage.getSnackbar().getText());
  }

  /**
   * Log in with wrong password. Check validation messages.
   */
  @TestRailTest(caseId = 38)
  @PractisTest
  void loginFailure_InvalidPassword() {
    //when
    loginPage.login("automation_practis@tula.co", "wrongPassword");

    //then
    assertEquals("This user wasn’t found.", loginPage.getSnackbar().getText());
  }

  /**
   * Log in with empty email and password. Check validation messages.
   */
  @TestRailTest(caseId = 40)
  @PractisTest
  void loginFailure_EmptyCredentials() {
    //when
    loginPage.login(null, null);

    //then
    assertEquals("We need your email address.", loginPage.getValidationMessage(0));
    assertEquals("We need your password.", loginPage.getValidationMessage(1));
  }

  /**
   * Log in with invalid email and empty password. Check validation messages.
   */
  @TestRailTest(caseId = 39)
  @PractisTest
  void loginFailure_InvalidEmailPattern() {
    //when
    loginPage.login("invalidEmail", null);

    assertEquals("Enter a valid email address", loginPage.getValidationMessage(0));
    assertEquals("We need your password.", loginPage.getValidationMessage(1));
  }

}
