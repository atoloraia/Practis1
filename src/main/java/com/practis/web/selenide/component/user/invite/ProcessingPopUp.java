package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProcessingPopUp {
    private final SelenideElement processingTitle = $("div[data-test*='processing']");
    private final SelenideElement progressTitle = $(".progress-title");
    private final SelenideElement progressbar = $(".progress.progress-striped.active");
    private final SelenideElement warningMessage = $("div[data-test*='warning']");
    private final SelenideElement stopButton = $("button[data-test*='processing-labels-stop']");
}
