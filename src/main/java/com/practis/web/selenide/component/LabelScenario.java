package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class LabelScenario {

  private final SelenideElement addLabelLink =
      $("button[data-test='scenario-labels-save-changes']");
  private final ElementsCollection availableLabels = $$("div.sc-kznUvE.KzcZz");


  public LabelScenario selectLabel(final String label) {
    availableLabels.find(Condition.matchText(label)).$("input[type='checkbox']").parent().click();
    return this;
  }

  public LabelScenario clickAddLabel() {
    addLabelLink.click();
    return this;
  }

}
