package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ConfirmationPopUp {

  private final SelenideElement discardButton =
      $(".sc-jcFkyM.gNfFbi.sc-kTLnJg.jVwDIe.inverse");
  private final SelenideElement saveButton = $(".sc-jcFkyM.gNfFbi.sc-kTLnJg.jVwDIe.primary");

  public void discardChanges() {
    discardButton.click();
  }

  public void saveChanges() {
    saveButton.click();
  }
}
