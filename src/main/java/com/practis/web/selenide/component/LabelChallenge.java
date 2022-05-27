package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class LabelChallenge {

  private final SelenideElement addLabelLink = $("button.sc-jgrIVw.Cagoq.primary");
  private final ElementsCollection availableLabels = $$("div.sc-hhfCwj.bhvQbG");


  public LabelChallenge selectLabel(final String label) {
    availableLabels.find(Condition.matchText(label)).$("label.sc-edESPO.bVEITr").click();
    return this;
  }

  public LabelChallenge clickAddLabel() {
    addLabelLink.click();
    return this;
  }

}
