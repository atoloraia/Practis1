package com.practis.web.selenide.service.company.challenge;

import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeSettingsModal;

public class ChallengeSettingsService {

    /** Open Challenge Settings */
    public void openChallengeSettings() {
        challengeCreatePage().getChallengeSettingsButton().click();
    }

    /** Close Challenge Settings */
    public void closeChallengeSettings() {
        challengeSettingsModal().getChallengeSettingsClose().click();
    }

    /** Change the number of Try */
    public void changeNumberOfTries(String text) {
        challengeSettingsModal().getLimitAttemptField().append(text);
    }

    /** Change the number of Try */
    public void changeToUnlimited() {
        challengeSettingsModal().getUnlimitedAttemptTitle().click();
    }

    /** Click on Apply */
    public void clickOnApply() {
        challengeSettingsModal().getApplyButton().click();
    }
}
