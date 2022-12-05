package com.practis.web.selenide.page.company.practisset;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

    private final SelenideElement editPractisSetTitle =
            $("div[data-test='practis-set-page-title']");
    private final SelenideElement assignUsersButton =
            $("button[data-test='assign-practis-set-users']");
    private final SelenideElement editButton = $("button[data-test='edit-practis-set']");
    private final SelenideElement archiveButton = $("button[data-test='archive-practis-set']");
    private final SelenideElement publishedText = $("span[data-test='practis-set-publish-date']");

    private final SelenideElement cancelButton = $("button[data-test='cancel-practis-set']");
    private final SelenideElement saveButton = $("button[data-test='save-practis-set']");

    private final SelenideElement titleField = $("input[data-test='practis-set-title']");
    private final SelenideElement createdByText = $("div[data-test='practis-set-created-by']");
    private final SelenideElement descriptionField =
            $("textarea[data-test='practis-set-description']");

    private final SelenideElement addLabelsButton = $("div[data-test='practis-set-labels-button']");
    private final SelenideElement labelsButtonName = $("div[data-test='practis-set-labels-label']");
    private final SelenideElement pacingDropdown =
            $("div[data-test='practis-set-pacing-selected-value']");

    private final SelenideElement totalDuration =
            $("div[data-test='practis-set-total-duration-value']");
    private final SelenideElement totalReps = $("div[data-test='practis-set-total-reps-value']");
    private final SelenideElement minAccuracy =
            $("div[data-test='practis-set-minimum-accuracy-value']");
    private final SelenideElement totalDurationText =
            $("div[data-test='practis-set-total-duration-text']");
    private final SelenideElement totalRepsReqDText =
            $("div[data-test='practis-set-total-reps-text']");
    private final SelenideElement minAccuracyText =
            $("div[data-test='practis-set-minimum-accuracy-text']");

    // Scenario tab
    private final SelenideElement scenarioTab = $("div[data-test='practis-set-tab-scenarios']");
    private final SelenideElement searchScenarioField =
            $("input[data-test='practis-set-scenarios-search-input']");
    private final SelenideElement searchScenarioIcon =
            $("input[data-test='practis-set-scenarios-search-input-icon']");
    private final SelenideElement filterScenarioButton =
            $("button[data-test='practis-set-scenarios-filters-button']");
    private final SelenideElement firstColumnScenario =
            $("th[data-test='practis-set-scenarios-column']");
    private final SelenideElement secondColumnScenario =
            $("th[data-test='practis-set-scenarios-duration-column']");

    // Challenge tab
    private final SelenideElement challengeTab = $("div[data-test='practis-set-tab-challenges']");
    private final SelenideElement searchChallengeField =
            $("input[data-test='practis-set-challenges-search-input']");
    private final SelenideElement searchChallengeIcon =
            $("input[data-test='practis-set-challenges-search-input-icon']");
    private final SelenideElement filterChallengeButton =
            $("button[data-test='practis-set-challenges-filters-button']");
    private final SelenideElement firstColumnChallenge =
            $("th[data-test='practis-set-challenges-column']");
    private final SelenideElement secondColumnChallenge =
            $("th[data-test='practis-set-challenges-duration-column']");

    // Added Scenarios and Challenges
    private final ElementsCollection contentChallengeRow =
            $$("div[data-test='practis-set-challenge-item']");
    private final ElementsCollection contentScenarioRow =
            $$("div[data-test='practis-set-scenario-item']");

    private final ElementsCollection scenarioTitle =
            $$("div[data-test='practis-set-content-scenario-label']");
    private final ElementsCollection challengeTitle =
            $$("div[data-test='practis-set-content-challenge-label']");
    private final ElementsCollection scenarioName =
            $$("div[data-test='practis-set-content-scenario-title']");
    private final ElementsCollection challengeName =
            $$("div[data-test='practis-set-content-challenge-title']");
    private final ElementsCollection scenarioPreviewButton =
            $$("div[data-test='practis-set-content-scenario-preview']");
    private final ElementsCollection challengePreviewButton =
            $$("div[data-test='practis-set-content-challenge-preview']");
    private final ElementsCollection durationText =
            $$("div[data-test='practis-set-content-scenario-duration']");

    private final ElementsCollection minimumRepsText =
            $$("div[data-test='practis-set-content-scenario-min-reps-label']");
    private final ElementsCollection minimumRepsValue =
            $$("div[data-test='practis-set-content-scenario-min-reps-value']");
    private final ElementsCollection minusRep =
            $$("div[data-test='practis-set-content-scenario-min-reps-minus']");
    private final ElementsCollection plusRep =
            $$("div[data-test='practis-set-content-scenario-min-reps-plus']");

    private final ElementsCollection deleteContentButton =
            $$("div[data-test='practis-set-content-scenario-delete']");
}
