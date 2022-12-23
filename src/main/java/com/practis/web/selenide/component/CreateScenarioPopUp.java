package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CreateScenarioPopUp {

    private final SelenideElement createScenarioTitle = $(".sc-jHWFkW.cFVWnv");
    private final SelenideElement progressTitle = $(".progress-title");
    private final SelenideElement progressbar = $(".progress.progress-striped.active");
    private final SelenideElement warningMessage = $(".sc-hUQsjW.dqsDtc");
    private final SelenideElement stopButton = $(".sc-iAKVOt.hRvGoE.primary");
}
