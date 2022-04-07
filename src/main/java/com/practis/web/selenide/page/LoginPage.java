package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.Snackbar;
import lombok.Getter;

public class LoginPage {

  private final SelenideElement logoElement = $("div.sc-giYgFv.gnbvMI");
  private final SelenideElement emailField = $("input[name='email']");
  private final SelenideElement passwordField = $("input[name='password']");
  private final SelenideElement loginButton = $("button[type='submit']");
  @Getter
  private final ElementsCollection validationMessages = Selenide.$$("div.sc-GamvQ.fFeDVP");

  @Getter
  private final Snackbar snackbar = new Snackbar();

  public SelenideElement getLogo() {
    return logoElement;
  }

  /**
   * Fill login form.
   */
  public LoginPage fillLoginForm(final String email, final String password) {
    fillEmail(email);
    fillPassword(password);
    return this;
  }

  public LoginPage fillEmail(final String email) {
    emailField.sendKeys(email);
    return this;
  }

  public void fillPassword(final String password) {
    passwordField.sendKeys(password);
  }

  public void login() {
    loginButton.click();
  }
}
