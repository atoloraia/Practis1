package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class KeepTrackPopUp {

    public final SelenideElement keepTrackTitle = $("div[data-test='training-tutorial-title']");
    public final SelenideElement keepTrackDescription =
            $("div[data-test='training-tutorial-description']");
    public final SelenideElement gotItButton = $("button[data-test='got-it-button']");
}
