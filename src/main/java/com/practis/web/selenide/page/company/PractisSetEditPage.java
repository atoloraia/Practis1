package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetEditPage {

  private final SelenideElement editPractisSetTitle = $("div[data-test='practis-set-page-title']");
  private final SelenideElement assignUsersButton =
      $("button[data-test='assign-practis-set-users']");
  private final SelenideElement editButton = $("button[data-test='edit-practis-set']");
  private final SelenideElement archiveButton = $("button[data-test='archive-practis-set']");
  private final SelenideElement publishedText = $("span[data-test='practis-set-publish-date']");

  private final SelenideElement titleField = $("input[data-test='practis-set-title']");
  private final SelenideElement createdByText = $(".sc-dUQMbX.biXBqH");
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

  //Scenario tab
  private final SelenideElement scenarioTab = $("div[data-test='practis-set-tab-scenarios']");
  private final SelenideElement searchScenarioField =
      $("input[data-test='practis-set-scenarios-search-input']");
  private final SelenideElement filterScenarioButton =
      $("button[data-test='practis-set-scenarios-filters-button']");
  private final SelenideElement firstColumnScenario =
      $("th[data-test='practis-set-scenarios-column']");
  private final SelenideElement secondColumnScenario =
      $("th[data-test='practis-set-scenarios-duration-column']");

  //Challenge tab
  private final SelenideElement challengeTab = $("div[data-test='practis-set-tab-challenges']");
  private final SelenideElement searchChallengeField =
      $("input[data-test='practis-set-challenges-search-input']");
  private final SelenideElement filterChallengeButton =
      $("button[data-test='practis-set-challenges-filters-button']");
  private final SelenideElement firstColumnChallenge =
      $("th[data-test='practis-set-challenges-column']");
  private final SelenideElement secondColumnChallenge =
      $("th[data-test='practis-set-challenges-duration-column']");

  //Added Scenarios and Challenges
  private final ElementsCollection contentField = $$(".sc-kDIlZc.hWxOsm");

  private final ElementsCollection scenarioTitle = $$(".sc-crMJNM.hAbdJf");
  private final ElementsCollection challengeTitle = $$(".sc-crMJNM.jinoNE");
  private final ElementsCollection scenarioChallengeName = $$(".sc-dZldFf.geKNHN");
  private final ElementsCollection previewButton = $$(".sc-dNiKyU.hYZJvx");
  private final ElementsCollection durationText = $$(".sc-bTeyJZ.cmLbtq");

  private final ElementsCollection minimumRepsText = $$(".sc-jxUdiu.imoPSG");
  private final ElementsCollection minimumRepsValue = $$(".sc-gvnPBw.keakTH");
  private final ElementsCollection minusRep = $$(".sc-kRfLIR.eZtNFZ");
  private final ElementsCollection plusRep = $$(".sc-kRfLIR.eZtNFZ");

  private final ElementsCollection deleteContentButton = $$(".sc-fusXuK.hzkCg");
}
