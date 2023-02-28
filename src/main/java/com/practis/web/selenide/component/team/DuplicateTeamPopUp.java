package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class DuplicateTeamPopUp {
    private final SelenideElement duplicatesTitle = $("div[data-test='duplicate-team-title']");
    private final SelenideElement progressTitle = $("div[data-test='duplicate-team-title']");
    private final SelenideElement progressbar = $("div[data-test='progress-container']");
    private final SelenideElement warningMessage = $("div[data-test='duplicate-team-warning']");
    private final SelenideElement stopButton = $("button[data-test='duplicate-team-stop']");
}
