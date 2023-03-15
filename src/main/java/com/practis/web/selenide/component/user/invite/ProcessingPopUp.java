package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProcessingPopUp {
    private final SelenideElement processTitle = $("div[data-test='progress-modal-title']");
    private final SelenideElement progressBar = $("div[data-test='progress-container']");
    private final SelenideElement warningMessage = $("div[data-test*='warning']");
    private final SelenideElement stopButton = $("button[data-test*='progress-modal-stop']");
}
