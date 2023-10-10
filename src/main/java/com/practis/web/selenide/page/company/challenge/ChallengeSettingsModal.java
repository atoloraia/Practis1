package com.practis.web.selenide.page.company.challenge;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeSettingsModal {

    private final SelenideElement challengeSettingsTitle =
            $("div[data-test='challenge-settings-modal-title']");
    private final SelenideElement challengeSettingsClose =
            $("div[data-test='challenge-settings-modal-close']");
    private final SelenideElement attemptsTab = $("div[data-test='attempts-tab']");
    private final SelenideElement limitAttemptRadioButton =
            $("input[data-test='limited-attempts-radio']");
    private final SelenideElement limitAttemptRadioButtonView =
            $("div[data-test='limited-attempts-radio-view']");
    private final SelenideElement limitedAttemptsTitle =
            $("div[data-test='limited-attempts-radio-label']");
    private final SelenideElement limitedAttemptsText =
            $("div[data-test='limited-attempts-description']");
    private final SelenideElement limitAttemptRadioButtonChecked =
            $("input[data-test='limited-attempts-radio-checked']");

    private final SelenideElement unlimitedAttemptRadioButtonChecked =
            $("input[data-test='unlimited-attempts-checked']");
    private final SelenideElement unlimitedAttemptRadioButton =
            $("input[data-test='unlimited-attempts']");
    private final SelenideElement unlimitedAttemptRadioButtonView =
            $("div[data-test='unlimited-attempts-view']");
    private final SelenideElement unlimitedAttemptTitle =
            $("div[data-test='unlimited-attempts-label']");
    private final SelenideElement unlimitedAttemptText =
            $("div[data-test='unlimited-attempts-description']");
    private final SelenideElement attemptsWarning = $("div[data-test='attempts-warning']");
}
