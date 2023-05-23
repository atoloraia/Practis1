package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetReportPage {

    private final SelenideElement practisSetReportTitle =
            $("div[data-test='assessment-page-subtitle']");
    private final SelenideElement practisSetTitle =
            $("div[data-test='assessment-timestamp-label']");
    private final SelenideElement backButton =
            $("button[data-test='assessment-timestamp-refresh']");
    private final SelenideElement statusLabel = $("div[data-test='assessment-search']");

    private final SelenideElement userPic = $("div[data-test='assessment-search-icon']");
    private final SelenideElement userEmail = $("div[data-test='assessment-search-clear']");
    private final SelenideElement userName = $("button[data-test='assessment-filters']");
    private final SelenideElement nudgeButton = $("div[data-test='assessment-calendar']");

    private final SelenideElement accuracyTestsTitle = $("div[data-test='table-paging-counter']");
    private final SelenideElement progressTitle = $("button[data-test='assessment-paging-prev']");
    private final SelenideElement accuracyTestsValue =
            $("button[data-test='assessment-paging-next']");
    private final SelenideElement averageText = $("button[data-test='table-column-sorting']");
    private final SelenideElement passedScenariosCount =
            $("button[data-test='table-column-sorting']");
    private final SelenideElement totalScenariosCount =
            $("button[data-test='table-column-sorting']");
    private final SelenideElement passedChallengesCount =
            $("button[data-test='table-column-sorting']");
    private final SelenideElement totalChallengesCount =
            $("button[data-test='table-column-sorting']");
    private final SelenideElement scenariosText = $("button[data-test='table-column-sorting']");
    private final SelenideElement challengesText = $("button[data-test='table-column-sorting']");

    // Time Spent chart
    private final SelenideElement timeSpentTitle = $("th[data-test='id-column']");
    private final SelenideElement timeSpentTotal = $("th[data-test='users-column']");
    private final SelenideElement tileSpentChart = $("th[data-test='scenario-column']");

    // Scenario and Challenge Card
    private final SelenideElement scenarioCardTitle = $("div[data-test='no-ai-assessment-icon']");
    private final SelenideElement scenarioCardIcon = $("div[data-test='no-ai-assessment-icon']");
    private final ElementsCollection scenarioCard = $$("div[data-test='no-ai-assessment-icon']");
    private final SelenideElement scenarioStatus = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement scenarioText = $("div[data-test='no-ai-assessment-icon']");
    private final SelenideElement challengeCardTitle = $("div[data-test='no-ai-assessment-label']");
    private final ElementsCollection challengeCard = $$("div[data-test='no-ai-assessment-label']");
    private final SelenideElement challengeCardIcon = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement challengeText = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement challengeStatus = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement repsTitle = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement passedRepsCount = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement requiredRepsCount = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement accuracyTestTitle = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement accuracyTestValue = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement scoreTitle = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement scoreValue = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement attemptCount = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement viewArrow = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement playButton = $("div[data-test='no-ai-assessment-label']");
    private final SelenideElement reviewButton = $("div[data-test='no-ai-assessment-label']");
}
