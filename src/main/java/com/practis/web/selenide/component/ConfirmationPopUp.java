package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ConfirmationPopUp {

  private final SelenideElement discardButton = $("button.sc-jgrIVw.hIVLHB.inverse");
  private final SelenideElement saveButton = $("button.sc-jgrIVw.hIVLHB.primary");

  public void discardChanges() {
    discardButton.click();
  }

  public void saveChanges() {
    saveButton.click();
  }
}
