package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PsConfirmationPopUp {

  private final SelenideElement cancelButton =
      $("button[data-test='practis-set-modal-go-back']");
  private final SelenideElement confirmButton = $("button[data-test='practis-set-modal-proceed']");

  public void discardChanges() {
    cancelButton.click();
  }

  public void saveChanges() {
    confirmButton.click();
  }
}
