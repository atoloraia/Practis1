package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class EditPhotoPopUp {

    private final SelenideElement editPhotoTitle = $("div[data-test='edit-photo-title']");
    private final SelenideElement zoomText = $("span[data-test='edit-photo-zoom-label']");
    private final SelenideElement zoomValue = $("span[data-test='edit-photo-zoom-value']");
    private final SelenideElement cancelButton = $("button[data-test='edit-photo-cancel-button']");
    private final SelenideElement saveButton = $("button[data-test='edit-photo-save-button']");
}
