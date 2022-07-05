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
  private final SelenideElement customerTitle = $(".sc-dEJdiX.dJIBfp");
  private final SelenideElement repTitle = $(".sc-dEJdiX.dJIBfp");
  private final SelenideElement generateForAll =
      $("button[title='scenario-generate-for-all']");
  private final SelenideElement playAll = $("textarea[data-test='scenario-play-all']");
  private final SelenideElement scenarioPreview =
      $("textarea[data-test='scenario-preview']");
  private final SelenideElement getPdf =
      $("textarea[data-test='download-scenario-as-pdf']");
  private final SelenideElement customerLine =
      $("textarea[data-test='scenario-customer-lines-text']");
  private final SelenideElement repLine =
      $("textarea[data-test='scenario-rep-lines-text']");
  private final SelenideElement totalDuration =
      $("textarea[data-test='scenario-total-duration-text']");
  private final SelenideElement customerLineDrag =
      $("textarea[data-test='scenario-customer-line-drag-handle']");
  private final SelenideElement repLineDrag =
      $("textarea[data-test='scenario-rep-line-drag-handle']");
  private final SelenideElement customerLineInput =
      $("textarea[data-test='scenario-customer-line-input]");
  private final SelenideElement repLineInput =
      $("textarea[data-test='scenario-rep-line-input']");
  private final SelenideElement scenarioPlayCustomer =
      $("textarea[data-test='play-scenario-customer-line']");
  private final SelenideElement scenarioPlayRep =
      $("textarea[data-test='play-scenario-rep-line']");


}
