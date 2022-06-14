package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class LabelChallenge {

  private final SelenideElement addLabelLink =
      $("button[data-test='chanllenge-labels-save-changes']");
  private final ElementsCollection availableLabels = $$("div.sc-kznUvE.KzcZz");


  public LabelChallenge selectLabel(final String label) {
    availableLabels.find(Condition.matchText(label)).$("input[type='checkbox']").parent().click();
    return this;
  }

  public LabelChallenge clickAddLabel() {
    addLabelLink.click();
    return this;
  }

}
