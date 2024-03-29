package com.practis.web.selenide.page.company.challenge;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeEditPage {

    private final SelenideElement headerText = $("div[data-test='challenge-page-title']");
    private final SelenideElement publishedText = $("span[data-test='challenge-publish-date']");
    private final SelenideElement archiveButton = $("button[data-test='archive-challenge']");
    private final SelenideElement editButton = $("button[data-test='edit-challenge']");
    private final SelenideElement cancelEditButton = $("button[data-test='cancel-edit-challenge']");
    private final SelenideElement saveChangesButton = $("button[data-test='save-challenge']");
    private final SelenideElement publishButton = $("button[data-test='publish-challenge']");
    private final SelenideElement saveAsDraftButton =
            $("button[data-test='save-challenge-as-draft']");

    private final SelenideElement titleField = $("input[data-test='challenge-title']");
    private final SelenideElement labelsButton = $("div[data-test='challenge-labels-button']");
    private final SelenideElement labelsText = $("div[data-test='challenge-labels-label']");
    private final SelenideElement createdByText = $("div[data-test='challenge-creator-text']");

    private final SelenideElement descriptionField =
            $("textarea[data-test='chanllenge-description']");
    private final SelenideElement descriptionCounterText =
            $("div[data-test='chanllenge-description-counter']");
    private final SelenideElement generateForAllButton =
            $("button[data-test='challenge-generate-for-all']");
    private final SelenideElement playAllButton = $("button[data-test='challenge-play-all']");
    private final SelenideElement customerAvatar = $("div[data-test='challenge-customer-avatar']");
    private final SelenideElement customerText = $("p[data-test='challenge-customer-label']");
    private final SelenideElement repAvatar = $("div[data-test='challenge-rep-logo']");
    private final SelenideElement repText = $("p[data-test='challenge-rep-label']");
    private final SelenideElement dragButton =
            $("div[data-test='challenge-customer-line-drag-handle']");
    private final SelenideElement customerLineText =
            $("div[data-test='challenge-customer-line-title']");
    private final SelenideElement customerLineField = $("div[data-test='challenge-customer-line']");
    private final SelenideElement playCustomerLineButton =
            $("button[data-test='play-challenge-customer-line-audio']");
    private final SelenideElement deleteCustomerLineButton =
            $("button[data-test='delete-challenge-customer-line']");
    private final SelenideElement recordCustomerLineButton =
            $("button[data-test='record-challenge-customer-line-audio']");
    private final SelenideElement generateCustomerLineAudioButton =
            $("button[data-test='generate-challenge-customer-line-audio']");

    private final SelenideElement repLineText = $("span[data-test='challenge-user-auto-reply']");
    private final SelenideElement repSection = $("div[data-test='challenge-rep-line-drag-handle']");
    private final SelenideElement repTitle = $("div[data-test='challenge-rep-line-title']");
    private final SelenideElement repline = $("div[data-test='challenge-rep-line']");
    private final SelenideElement addCustomerLineButton =
            $("a[data-test='add-challenge-customer-line']");

    // Attempts
    private final SelenideElement maxAttemptText = $("div[data-test='max-attempts-text']");
    private final SelenideElement attemptLimit = $("div[data-test='challenge-attempts-limit']");
    private final SelenideElement challengeSettingsButton =
            $("button[data-test='challenge-settings']");
}
