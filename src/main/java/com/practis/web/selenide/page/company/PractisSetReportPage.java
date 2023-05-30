package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetReportPage {

    private final SelenideElement practisSetReportTitle =
            $("div[data-test='practis-set-report-page-title']");
    private final SelenideElement practisSetTitle =
            $("div[data-test='practis-set-report-page-subtitle']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");
    private final SelenideElement statusLabel = $("div[data-test='practis-set-status']");
    private final SelenideElement dueDateLabel = $("span[data-test='practis-set-due-date']");

    private final SelenideElement userPic = $("div[data-test='practis-set-user-avatar']");
    private final SelenideElement userName = $("span[data-test='practis-set-user-name']");
    private final SelenideElement userEmail = $("div[data-test='practis-set-user-email']");
    private final SelenideElement nudgeButton = $("button[data-test='nudge-button']");

    private final SelenideElement accuracyTestsTitle = $("div[data-test='accuracy-tests-title']");
    private final SelenideElement progressTitle = $("div[data-test='progress-title']");
    private final SelenideElement accuracyTestsEmptyValue =
            $("span[data-test='empty-average-accuracy']");
    private final SelenideElement accuracyTestsValue =
            $("span[data-test='empty-average-accuracy']");
    private final SelenideElement averageText = $("span[data-test='average-accuracy-text']");
    private final SelenideElement passedScenariosCount =
            $("span[data-test='passed-scenario-count']");
    private final SelenideElement totalScenariosCount = $("span[data-test='total-scenario-count']");
    private final SelenideElement passedChallengesCount =
            $("span[data-test='passed-challenge-count']");
    private final SelenideElement totalChallengesCount =
            $("span[data-test='total-challenge-count']");
    private final SelenideElement scenariosText = $("span[data-test='progress-scenarios-text']");
    private final SelenideElement challengesText = $("span[data-test='progress-challenges-text']");

    // Time Spent chart
    private final SelenideElement timeSpentTitle = $("div[data-test='training-time-title']");
    private final SelenideElement emptyTimeSpentIcon =
            $("div[data-test='empty-training-time-icon']");
    private final SelenideElement emptyTimeSpentText =
            $("div[data-test='empty-training-time-text']");
    private final SelenideElement timeSpentTotal = $("div[data-test='total-time-value']");
    private final SelenideElement tileSpentChart = $("div[data-test='training-time-chart']");

    // Scenario and Challenge Card
    private final SelenideElement scenarioCardTitle = $("span[data-test='scenario-item-title']");
    private final SelenideElement scenarioCardIcon = $("div[data-test='scenario-item-icon']");
    private final ElementsCollection scenarioCard = $$("div[data-test='scenario-item']");
    private final SelenideElement scenarioStatus = $("span[data-test='scenario-item-status']");
    private final SelenideElement scenarioText = $("span[data-test='scenario-item-text']");
    private final SelenideElement challengeCardTitle = $("span[data-test='challenge-item-title']");
    private final ElementsCollection challengeCard = $$("div[data-test='challenge-item']");
    private final SelenideElement challengeCardIcon = $("div[data-test='challenge-item-icon']");
    private final SelenideElement challengeText = $("span[data-test='challenge-item-text']");
    private final SelenideElement challengeStatus = $("span[data-test='challenge-item-status']");
    private final SelenideElement repsTitle = $("span[data-test='scenario-reps-text']");
    private final SelenideElement passedRepsCount = $("span[data-test='scenario-reps-count']");
    private final SelenideElement requiredRepsCount =
            $("span[data-test='scenario-reps-total-count']");
    private final SelenideElement accuracyTestTitle =
            $("span[data-test='scenario-accuracy-test-text']");
    private final SelenideElement accuracyTestValue =
            $("span[data-test='scenario-accuracy-value']");
    private final SelenideElement scoreTitle = $("span[data-test='challenge-score-text']");
    private final SelenideElement scoreValue = $("span[data-test='challenge-score-value']");
    private final SelenideElement attemptCount = $("div[data-test='no-ai-assessment-label']");
    private final ElementsCollection viewArrow = $$("div[data-test='practis-set-item-arrow']");
    private final SelenideElement playButton = $("div[data-test='practis-set-item-play']");
    private final SelenideElement reviewButton = $("span[data-test='scenario-review-button']");
}
