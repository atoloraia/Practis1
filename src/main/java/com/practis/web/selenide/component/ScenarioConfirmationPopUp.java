package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioConfirmationPopUp {

  private final SelenideElement cancelButton =
      $(".sc-jcFkyM.gNfFbi.sc-kiIAaw.guLMhe.inverse");
  private final SelenideElement confirmButton = $(".sc-jcFkyM.gNfFbi.sc-kiIAaw.guLMhe.primary");

  public void discardChanges() {
    cancelButton.click();
  }

  public void saveChanges() {
    confirmButton.click();
  }
}
