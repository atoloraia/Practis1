package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetCreatePage {

  private final SelenideElement addNewPractisSetTitle =
      $("div[data-test='practis-set-page-title']");
  private final SelenideElement notPublishedYetText =
      $("span[data-test='practis-set-not-published-yet']");

  private final SelenideElement titleField = $("input[data-test='practis-set-title']");
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

  private final SelenideElement scenarioTab = $("div[data-test='practis-set-tab-scenarios']");
  private final SelenideElement challengeTab = $("div[data-test='practis-set-tab-challenges']");
  private final SelenideElement searchScenarioField =
      $("input[data-test='practis-set-scenarios-search-input']");
  private final SelenideElement filterScenarioButton =
      $("button[data-test='practis-set-scenarios-filters-button']");
  private final SelenideElement searchChallengeField =
      $("input[data-test='practis-set-challenges-search-input']");
  private final SelenideElement filterChallengeButton =
      $("button[data-test='practis-set-challenges-filters-button']");
  private final SelenideElement firstColumnScenario =
      $("th[data-test='practis-set-scenarios-column']");
  private final SelenideElement secondColumnScenario =
      $("th[data-test='practis-set-scenarios-duration-column']");
  private final SelenideElement firstColumnChallenge =
      $("th[data-test='practis-set-challenges-column']");
  private final SelenideElement secondColumnChallenge =
      $("th[data-test='practis-set-challenges-duration-column']");

  private final ElementsCollection challengeItems =
      $$("div[data-test='practis-set-challenge-item']");
  private final ElementsCollection scenarioItems =
      $$("div[data-test='practis-set-scenario-item']");

  private final SelenideElement noContentImage = $("div[data-test='practis-set-no-result-logo']");
  private final SelenideElement noContentText = $("div[data-test='practis-set-no-result-text']");
  private final SelenideElement noContentDescriptionText =
      $("div[data-test='practis-set-no-result-description']");

  private final SelenideElement publishButton = $("button[data-test='publish-practis-set']");
  private final SelenideElement saveAsDraftButton =
      $("button[data-test='save-practis-set-as-draft']");
}
