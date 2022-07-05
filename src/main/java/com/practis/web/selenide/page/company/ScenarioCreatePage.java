package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioCreatePage {

  private final SelenideElement addNewScenarioTitle = $("div[data-test='scenario-page-title']");
  private final SelenideElement notPublishTitle = $("span[data-test='scenario-not-published-yet']");

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField = $("textarea[data-test='scenario-description']");

  private final SelenideElement labelsButton = $("div[data-test='scenario-labels-button']");
  private final SelenideElement labelsButtonName = $("div[data-test='scenario-labels-label']");
  private final SelenideElement saveChangesLabelButton =
      $("button[data-test='scenario-labels-save-changes']");
  private final ElementsCollection labelRows = $$("div[data-test='scenario-labels-item']");
  private final ElementsCollection labelName = $$("div[data-test='scenario-labels-item-name']");

  private final SelenideElement downloadAsPdfButton =
      $("div[data-test='download-scenario-as-pdf']");

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


  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement playAllButton = $("button[title='Play All']");
  private final SelenideElement previewAllButton = $("button[title='Preview']");

  private final ElementsCollection customerRepTitles = $$("p.sc-dEJdiX.dJIBfp");

  private final SelenideElement addCustomerLine = $("a[data-test='add-scenario-customer-line']");
  private final SelenideElement addARepLine = $("a[data-test='add-scenario-rep-line']");
  private final ElementsCollection customerField =
      $$("div[placeholder='Write customer’s line here']");
  private final ElementsCollection repField =
      $$("div[placeholder='Write representative’s line here']");
  private final ElementsCollection playButtons = $$("button[data-test*='play-scenario']");
  private final SelenideElement deleteLine = $("div[data-test='delete-scenario-customer-line']");

  private final SelenideElement publishButton = $("button[data-test='publish-scenario']");
  private final SelenideElement saveAsDraftButton = $("button[data-test='save-scenario-as-draft']");
  private final ElementsCollection draggableElements = $$(
      "div[data-rbd-drag-handle-draggable-id*='temp'");

  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    final var labelRow = labelRows.find(Condition.matchText(label));
    final var checkbox = labelRow.$("input[data-test='scenario-labels-item-checkbox']");
    return checkbox.parent();
  }
}
