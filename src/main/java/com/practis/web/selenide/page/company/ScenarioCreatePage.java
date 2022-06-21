package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioCreatePage {

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField = $("textarea[data-test='scenario-description']");
  private final SelenideElement addLabels = $(".sc-jRSrLI.iZMgcd");

  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement addCustomerLine = $("a[data-test='add-scenario-customer-line']");
  private final SelenideElement addARepLine = $("a[data-test='add-scenario-rep-line']");
  private final ElementsCollection customerField =
      $$("div[placeholder='Write customer’s line here']");
  private final ElementsCollection repField =
      $$("div[placeholder='Write representative’s line here']");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private final SelenideElement deleteLine = $("div[data-test='delete-scenario-customer-line']");

  private final SelenideElement publishButton = $("button[data-test='publish-scenario']");
  private final SelenideElement saveAsDraftButton = $("button[data-test='save-scenario-as-draft']");
  private final ElementsCollection draggableElements = $$(
      "div[data-rbd-drag-handle-draggable-id*='temp'");
}
