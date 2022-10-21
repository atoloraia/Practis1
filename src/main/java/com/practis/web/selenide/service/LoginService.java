package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

public class LoginService {

  /**
   * Get Email Validation Message.
   */
  public void fillFormAndLogin(final String email, final String password) {
    fillLoginForm(email, password);
    await().pollDelay(3, SECONDS).until(() -> true);
    clickLogin();
  }

  /**
   * Fill email and click 'Login'.
   */
  public void fillEmailLogin(final String email) {
    fillEmail(email);
    clickLogin();
  }

  /**
   * Click 'Login' on empty form.
   */
  public void emptyFormLogin() {
    clickLogin();
  }

  /**
   * Fill login form with email and password.
   */
  public void fillLoginForm(final String email, final String password) {
    fillEmail(email);
    fillPassword(password);
  }

  /**
   * Fill login form with email.
   */
  public void fillEmail(final String email) {
    loginPage().getEmailField().setValue(email.substring(0, email.length() - 1));
    loginPage().getEmailField().append(email.substring(email.length() - 1));
  }

  /**
   * Fill login form with password.
   */
  public void fillPassword(final String password) {
    loginPage().getPasswordField().setValue(password.substring(0, password.length() - 1));
    loginPage().getPasswordField().append(password.substring(password.length() - 1));
  }

  /**
   * Click 'Login' button.
   */
  public void clickLogin() {
    loginPage().getLoginButton().click();
  }

}
