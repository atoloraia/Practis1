package com.practis.web.selenide.service.component;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.saveAsDraftPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;

import com.codeborne.selenide.SelenideElement;

public class SaveAsDraftPopUpService {

  /**
   * Click "Cancel" button.
   */
  public void clickCancel() {
    saveAsDraftPopUp().getCancelButton().click();
  }

  /**
   * Click "Save" button.
   */
  public void clickSave() {
    saveAsDraftPopUp().getSaveButton().click();
  }

  /**
   * Click fill Title.
   */
  public void fillTitle(String name) {
    saveAsDraftPopUp().getDraftTitleField().append(name);
  }

  /**
   * Click Save as Draft.
   */
  public void saveAsDraft(String name) {
    fillTitle(name);
    clickSave();
  }





}
