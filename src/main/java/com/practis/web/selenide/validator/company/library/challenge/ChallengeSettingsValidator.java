package com.practis.web.selenide.validator.company.library.challenge;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeSettingsModal;

public class ChallengeSettingsValidator {

    /** Assert elements on Challenge Settings page. */
    public static void assertElementsOnChallengeSettingsPage() {
        challengeSettingsModal().getChallengeSettingsModal().shouldBe(visible);
        challengeSettingsModal().getChallengeSettingsModal().shouldBe(attribute("width", "790"));

        challengeSettingsModal().getChallengeSettingsTitle().shouldBe(visible);
        challengeSettingsModal()
                .getChallengeSettingsTitle()
                .shouldBe(exactText("Challenge Settings"));
        challengeSettingsModal().getChallengeSettingsClose().shouldBe(visible);
        challengeSettingsModal().getAttemptsTab().shouldBe(visible);
        challengeSettingsModal().getAttemptsTab().shouldBe(exactText("Attempts"));

        challengeSettingsModal().getLimitAttemptRadioButton().shouldBe(hidden);
        challengeSettingsModal().getLimitAttemptRadioButtonView().shouldBe(visible);
        challengeSettingsModal().getLimitedAttemptsTitle().shouldBe(visible);
        challengeSettingsModal().getLimitedAttemptsTitle().shouldBe(exactText("Limit Attempts to"));
        challengeSettingsModal().getLimitAttemptField().shouldBe(visible);
        challengeSettingsModal().getLimitAttemptField().shouldBe(enabled);
        challengeSettingsModal().getLimitAttemptField().shouldBe(attribute("value", "3"));
        challengeSettingsModal().getLimitedAttemptsText().shouldBe(visible);
        challengeSettingsModal()
                .getLimitedAttemptsText()
                .shouldBe(
                        exactText(
                                "Users have limited attempts. After reaching the limit, the App"
                                    + " force them to submit last recording. Recommended value is"
                                    + " 3 attempts."));

        challengeSettingsModal().getUnlimitedAttemptRadioButtonChecked().shouldBe(hidden);
        challengeSettingsModal().getUnlimitedAttemptRadioButtonView().shouldBe(visible);
        challengeSettingsModal().getUnlimitedAttemptTitle().shouldBe(visible);
        challengeSettingsModal()
                .getUnlimitedAttemptTitle()
                .shouldBe(exactText("Unlimited attempts"));
        challengeSettingsModal().getUnlimitedAttemptText().shouldBe(visible);
        challengeSettingsModal()
                .getUnlimitedAttemptText()
                .shouldBe(
                        exactText(
                                "Learners can record as many times as they want before they submit"
                                        + " the challenge."));

        challengeSettingsModal().getAttemptsWarning().shouldBe(visible);
        challengeSettingsModal()
                .getAttemptsWarning()
                .shouldBe(
                        exactText(
                                "Important: The new limitation will affect all newly assigned"
                                    + " Learners and Learners who haven't submitted the challenge"
                                    + " yet. This change won't affect users who already submitted"
                                    + " the challenge."));

        challengeSettingsModal().getApplyButton().shouldBe(visible);
        challengeSettingsModal().getApplyButton().shouldBe(disabled);
        challengeSettingsModal().getApplyButton().shouldBe(exactText("Apply"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("type", "submit"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("width", "130px"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("color", "default"));
    }

    /** Assert elements on Challenge Settings page. */
    public static void assertHiddenChallengeSettings() {
        challengeSettingsModal().getChallengeSettingsModal().shouldBe(hidden);
    }

    /** Assert changed number of tries. */
    public static void assertChangedNumberOfTries() {
        challengeCreatePage().getAttemptLimit().shouldBe(exactText("35"));
    }

    /** Assert pre-changed number of tries to unlimited. */
    public static void assertUnlimitedTriesPreselected() {
        challengeSettingsModal().getLimitAttemptField().shouldBe(attribute("value", "35"));
        challengeSettingsModal().getLimitAttemptField().shouldBe(disabled);
        challengeSettingsModal().getApplyButton().shouldBe(enabled);
    }

    /** Assert changed number of tries. */
    public static void assertUnlimitedTries() {
        challengeCreatePage().getAttemptLimit().shouldBe(exactText("Unlimited"));
    }

    /** Assert changed number of tries to unlimited. */
    public static void assertUnlimitedTriesSelected() {

        challengeSettingsModal().getChallengeSettingsModal().shouldBe(visible);
        challengeSettingsModal().getChallengeSettingsModal().shouldBe(attribute("width", "790"));

        challengeSettingsModal().getChallengeSettingsTitle().shouldBe(visible);
        challengeSettingsModal()
                .getChallengeSettingsTitle()
                .shouldBe(exactText("Challenge Settings"));
        challengeSettingsModal().getChallengeSettingsClose().shouldBe(visible);
        challengeSettingsModal().getAttemptsTab().shouldBe(visible);
        challengeSettingsModal().getAttemptsTab().shouldBe(exactText("Attempts"));

        challengeSettingsModal().getLimitAttemptRadioButton().shouldBe(hidden);
        challengeSettingsModal().getLimitAttemptRadioButtonView().shouldBe(visible);
        challengeSettingsModal().getLimitedAttemptsTitle().shouldBe(visible);
        challengeSettingsModal().getLimitedAttemptsTitle().shouldBe(exactText("Limit Attempts to"));
        challengeSettingsModal().getLimitAttemptField().shouldBe(visible);
        challengeSettingsModal().getLimitAttemptField().shouldBe(empty);
        challengeSettingsModal().getLimitAttemptField().shouldBe(disabled);
        challengeSettingsModal().getLimitedAttemptsText().shouldBe(visible);
        challengeSettingsModal()
                .getLimitedAttemptsText()
                .shouldBe(
                        exactText(
                                "Users have limited attempts. After reaching the limit, the App"
                                    + " force them to submit last recording. Recommended value is"
                                    + " 3 attempts."));

        challengeSettingsModal().getLimitAttemptRadioButtonChecked().shouldBe(hidden);
        challengeSettingsModal().getUnlimitedAttemptRadioButtonView().shouldBe(visible);
        challengeSettingsModal().getUnlimitedAttemptTitle().shouldBe(visible);
        challengeSettingsModal()
                .getUnlimitedAttemptTitle()
                .shouldBe(exactText("Unlimited attempts"));
        challengeSettingsModal().getUnlimitedAttemptText().shouldBe(visible);
        challengeSettingsModal()
                .getUnlimitedAttemptText()
                .shouldBe(
                        exactText(
                                "Learners can record as many times as they want before they submit"
                                        + " the challenge."));

        challengeSettingsModal().getAttemptsWarning().shouldBe(visible);
        challengeSettingsModal()
                .getAttemptsWarning()
                .shouldBe(
                        exactText(
                                "Important: The new limitation will affect all newly assigned"
                                    + " Learners and Learners who haven't submitted the challenge"
                                    + " yet. This change won't affect users who already submitted"
                                    + " the challenge."));

        challengeSettingsModal().getApplyButton().shouldBe(visible);
        challengeSettingsModal().getApplyButton().shouldBe(disabled);
        challengeSettingsModal().getApplyButton().shouldBe(exactText("Apply"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("type", "submit"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("width", "130px"));
        challengeSettingsModal().getApplyButton().shouldBe(attribute("color", "default"));
    }
}
