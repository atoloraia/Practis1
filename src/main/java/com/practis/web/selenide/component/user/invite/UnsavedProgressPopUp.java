package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UnsavedProgressPopUp {

    private final SelenideElement unsavedProgressTitle =
            $("div[data-test='unsaved-progress-title']");
    private final SelenideElement areYouSureText =
            $("div[data-test='unsaved-progress-description']");
    private final SelenideElement exitButton = $("button[data-test='unsaved-progress-exit']");
    private final SelenideElement goBackButton = $("button[data-test='unsaved-progress-go-back']");
}
