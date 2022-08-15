package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.saveAsDraftPopUp;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;

import com.practis.dto.NewUserInput;


public class SaveAsDraftPopUpValidator {

  /**
   * Assert Label model.
   */
  public static void assertSaveAsDraftPopUp() {
    saveAsDraftPopUp().getSaveAsDraftTitle().shouldBe(visible);
    saveAsDraftPopUp().getSaveAsDraftTitle().shouldBe(exactText("Save as Draft"));
    saveAsDraftPopUp().getSaveAsDraftText().shouldBe(visible);
    saveAsDraftPopUp().getSaveAsDraftText().shouldBe(
        exactText("You can save your progress as draft until it’s ready to be sent out."));
    saveAsDraftPopUp().getDraftTitleField().shouldBe(visible);
    saveAsDraftPopUp().getDraftTitleField().shouldBe(attribute("placeholder","Draft Title"));
    saveAsDraftPopUp().getDraftTitleField().shouldBe(attribute("type","text"));
    saveAsDraftPopUp().getDraftTitleField().shouldBe(attribute("maxlength","100"));
    saveAsDraftPopUp().getCancelButton().shouldBe(visible);
    saveAsDraftPopUp().getCancelButton().shouldBe(enabled);
    saveAsDraftPopUp().getCancelButton().shouldBe(exactText("Cancel"));
    saveAsDraftPopUp().getCancelButton().shouldBe(attribute("color", "default"));
    saveAsDraftPopUp().getCancelButton().shouldBe(attribute("width", "128px"));
    saveAsDraftPopUp().getSaveButton().shouldBe(visible);
    saveAsDraftPopUp().getSaveButton().shouldBe(disabled);
    saveAsDraftPopUp().getSaveButton().shouldBe(exactText("Save"));
    saveAsDraftPopUp().getSaveButton().shouldBe(attribute("color", "default"));
    saveAsDraftPopUp().getSaveButton().shouldBe(attribute("width", "128px"));
  }

  /**
   * Assert Label model - error state.
   */
  public static void assertSaveAsDraftErrorPopUp() {
    saveAsDraftPopUp().getSaveAsDraftTitle().shouldBe(visible);
    saveAsDraftPopUp().getSaveAsDraftTitle().shouldBe(exactText("Save as Draft"));
    saveAsDraftPopUp().getSaveAsDraftText().shouldBe(visible);
    saveAsDraftPopUp().getSaveAsDraftText().shouldBe(
        exactText("You can save your progress as draft until it’s ready to be sent out."));
    saveAsDraftPopUp().getDraftTitleField().shouldBe(visible);
    saveAsDraftPopUp().getDraftTitleErrorField().shouldBe(attribute("height","40px"));
    saveAsDraftPopUp().getDraftTitleErrorText().shouldBe(visible);
    saveAsDraftPopUp().getDraftTitleErrorText()
        .shouldBe(exactText("Entered title already exists"));
    saveAsDraftPopUp().getCancelButton().shouldBe(visible);
    saveAsDraftPopUp().getCancelButton().shouldBe(enabled);
    saveAsDraftPopUp().getCancelButton().shouldBe(exactText("Cancel"));
    saveAsDraftPopUp().getCancelButton().shouldBe(attribute("color", "default"));
    saveAsDraftPopUp().getCancelButton().shouldBe(attribute("width", "128px"));
    saveAsDraftPopUp().getSaveButton().shouldBe(visible);
    saveAsDraftPopUp().getSaveButton().shouldBe(enabled);
    saveAsDraftPopUp().getSaveButton().shouldBe(exactText("Save"));
    saveAsDraftPopUp().getSaveButton().shouldBe(attribute("color", "default"));
    saveAsDraftPopUp().getSaveButton().shouldBe(attribute("width", "128px"));
  }


}
