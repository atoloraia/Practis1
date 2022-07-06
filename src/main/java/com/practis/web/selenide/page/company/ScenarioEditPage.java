package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioEditPage {

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField =
      $("textarea[data-test='scenario-description']");
  private final SelenideElement symbolCount = $(".sc-hFxFgV.eYBBhj");
  private final SelenideElement createdField = $(".sc-glhgHn.cOakhy");
  private final SelenideElement publishedField =
      $("span[data-test='scenario-publish-date']");
  private final SelenideElement archiveButton =
      $("button[data-test='archive-scenario']");
  private final SelenideElement editButton =
      $("button[data-test='edit-scenario']");
  private final SelenideElement customerTitle = $(".sc-dEJdiX.dJIBfp");
  private final SelenideElement repTitle = $(".sc-dEJdiX.dJIBfp");
  private final SelenideElement generateForAll =
      $("button[data-test='scenario-generate-for-all']");
  private final SelenideElement playAll = $("button[data-test='scenario-play-all']");
  private final SelenideElement scenarioPreview =
      $("button[data-test='scenario-preview']");
  private final SelenideElement getPdf =
      $("div[data-test='download-scenario-as-pdf']");
  private final SelenideElement labels = $(".sc-fYdmbZ.hUVxUA");
  private final SelenideElement customerLine =
      $("div[data-test='scenario-customer-lines-text']");
  private final SelenideElement repLine =
      $("div[data-test='scenario-rep-lines-text']");
  private final SelenideElement totalDuration =
      $("div[data-test='scenario-total-duration-text']");
  private final SelenideElement customerLineInput =
      $("div[data-test='scenario-customer-line-input]");
  private final SelenideElement repLineInput =
      $("div[data-test='scenario-rep-line-input']");
  private final SelenideElement scenarioPlayCustomer =
      $("button[data-test='play-scenario-customer-line']");
  private final SelenideElement scenarioPlayRep =
      $("button[data-test='play-scenario-rep-line']");


}
