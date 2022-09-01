package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.page.LoginPage;

public class LoginService {

  /**
   * Get Email Validation Message.
   */
  public void fillFormAndLogin(final String email, final String password) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    fillLoginForm(email, password);
    clickLogin();
    System.out.println(1);
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
    loginPage().getEmailField().sendKeys(email);
  }

  /**
   * Fill login form with password.
   */
  public void fillPassword(final String password) {
    loginPage().getPasswordField().sendKeys(password);
  }

  /**
   * Click 'Login' button.
   */
  public void clickLogin() {
    loginPage().getLoginButton().click();
  }

}
