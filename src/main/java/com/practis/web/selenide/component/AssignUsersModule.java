package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class AssignUsersModule {

  private final SelenideElement cancelButton = $("button[title='Cancel']");

  public void cancel() {
    cancelButton.click();
  }

}
