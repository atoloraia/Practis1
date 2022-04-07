package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class HomePage {

  private final SelenideElement loginButton = $("button[title='Log In']");

  public void clickLogin() {
    loginButton.click();
  }

}
