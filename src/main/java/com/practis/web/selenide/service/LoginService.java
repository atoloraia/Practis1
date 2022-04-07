package com.practis.web.selenide.service;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.page.LoginPage;
import lombok.Getter;

public class LoginService {

  @Getter
  private final LoginPage loginPage = new LoginPage();

  public void login(final String email, final String password) {
    loginPage.fillLoginForm(email, password).login();
  }

  public void login(final String email) {
    loginPage.fillEmail(email).login();
  }

  public void login() {
    loginPage.login();
  }

  public SelenideElement getEmailFieldValidationMessage() {
    return loginPage.getValidationMessageElement(loginPage.getEmailField());
  }

  public SelenideElement getPasswordFieldValidationMessage() {
    return loginPage.getValidationMessageElement(loginPage.getPasswordField());
  }

  public SelenideElement getSnackbar() {
    return loginPage.getSnackbar().getMessage();
  }

}
