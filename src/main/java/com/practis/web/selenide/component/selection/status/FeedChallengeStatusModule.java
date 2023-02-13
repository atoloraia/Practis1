package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedChallengeStatusModule {

    private final SelenideElement statusTitle = $("span[data-test='status-section-title']");

    private final SelenideElement archivedStatusLabel =
            $("div[data-test='archived-checkbox-label']");
    private final SelenideElement statusCheckbox = $("input[data-test='archived-checkbox']");
}
