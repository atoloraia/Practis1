package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmationAndWarningPopUp {

    public final SelenideElement confirmTitle = $("div[data-test='confirmation-modal-title']");
    public final SelenideElement confirmDescription =
            $("div[data-test='confirmation-modal-description']");

    private final SelenideElement cancelButton = $("button[data-test='confirmation-modal-cancel']");
    private final SelenideElement confirmButton =
            $("button[data-test='confirmation-modal-confirm']");

    public void discardChanges() {
        cancelButton.click();
    }

    public void saveChanges() {
        confirmButton.click();
    }
}
