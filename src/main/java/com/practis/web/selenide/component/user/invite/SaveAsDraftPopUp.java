package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class SaveAsDraftPopUp {

    private final SelenideElement saveAsDraftTitle = $("div[data-test='save-as-draft-title']");
    private final SelenideElement saveAsDraftText = $("div[data-test='save-as-draft-description']");
    private final SelenideElement draftTitleField = $("input[data-test='save-as-draft-input']");
    private final SelenideElement draftTitleErrorText =
            $("div[data-test='save-as-draft-input-error']");
    private final SelenideElement cancelButton = $("button[data-test='save-as-draft-cancel']");
    private final SelenideElement saveButton = $("button[data-test='save-as-draft-save']");
}
