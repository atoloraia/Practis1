package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ConfirmationPopUp {

  private final SelenideElement discardButton = $("button.sc-lbhJmS.fiPnyQ.inverse");
  private final SelenideElement saveButton = $("button.sc-lbhJmS.fiPnyQ.primary");

  public void discardChanges() {
    discardButton.click();
  }

  public void saveChanges() {
    saveButton.click();
  }
}
