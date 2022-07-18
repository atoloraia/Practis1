package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioEditPage {

  private final SelenideElement editScenarioTitle = $("div[data-test='scenario-page-title']");
  private final SelenideElement publishedText = $("span[data-test='scenario-publish-date']");

  private final SelenideElement archiveButton = $("button[data-test='archive-scenario']");
  private final SelenideElement editButton = $("button[data-test='edit-scenario']");
  private final SelenideElement saveChangesButton =
      $("button[data-test='save-scenario-changes']");
  private final SelenideElement cancelEditButton = $("button[data-test='cancel-edit']");

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField = $("textarea[data-test='scenario-description']");
  private final SelenideElement symbolCount = $("div[data-test='scenario-description-counter']");
  private final SelenideElement createdText = $("div[data-test='created-by-subtitle']");
  private final SelenideElement getPdf = $("div[data-test='download-scenario-as-pdf']");
  private final SelenideElement labels = $("div[data-test='scenario-labels-label']");

  private final SelenideElement customerLinesTitle =
      $("div[data-test='scenario-customer-lines-text']");
  private final SelenideElement customerLinesValue =
      $("div[data-test='scenario-customer-lines-value']");

  private final SelenideElement repLinesTitle = $("div[data-test='scenario-rep-lines-text']");
  private final SelenideElement repLinesValue = $("div[data-test='scenario-rep-lines-value']");

  private final SelenideElement totalDurationTitle =
      $("div[data-test='scenario-total-duration-text']");
  private final SelenideElement totalDurationValue =
      $("div[data-test='scenario-total-duration-value']");

  private final SelenideElement customerLineInput =
      $("div[data-test='scenario-customer-line-input]");
  private final SelenideElement repLineInput =
      $("div[data-test='scenario-rep-line-input']");

  private final ElementsCollection customerRepTitles = $$("p.sc-gxnCqH.ldwwUE");
  private final SelenideElement generateForAll =
      $("button[data-test='scenario-generate-for-all']");
  private final SelenideElement playAll = $("button[data-test='scenario-play-all']");
  private final SelenideElement scenarioPreview =
      $("button[data-test='scenario-preview']");

  private final SelenideElement recordAudioCustomerButton =
      $("button[data-test='record-scenario-customer-line-audio']");
  private final SelenideElement recordAudioRepButton =
      $("button[data-test='record-scenario-rep-line-audio']");

  private final SelenideElement scenarioPlayCustomer =
      $("button[data-test='play-scenario-customer-line']");
  private final SelenideElement scenarioPlayRep =
      $("button[data-test='play-scenario-rep-line']");

}
