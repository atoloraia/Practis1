package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NudgePopUp {

    private final SelenideElement nudgeTitle = $("div[data-test='nudge-title']");
    private final SelenideElement nudgeDescription = $("div[data-test='nudge-description']");
    private final SelenideElement fromField = $("input[data-test='nudge-from']");
    private final SelenideElement messageField = $("textarea[data-test='nudge-message']");
    private final SelenideElement cancelButton = $("button[data-test='nudge-cancel']");
    private final SelenideElement applyButton = $("button[data-test='nudge-send']");
}
