package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class Label {

  private final SelenideElement labelIcon = $("div.sc-dYPeNj.bbWqaM");
  private final SelenideElement addLabelLink = $("div.sc-hBURRC.dJxSfg.sc-hRMXGO");
  private final SelenideElement labelInput = $("input.sc-iqsfdx.iYxixc.sc-gGPAug.fnAlKp");
  private final SelenideElement saveLabelLink = $("a.sc-jRQAMF.cimCkU.sc-JkiRB.dMIDhj");
  private final ElementsCollection labels = $$("input.sc-iqsfdx.wOxzW.sc-dkqQaW.jvODGL");

  public Label openPanel() {
    labelIcon.click();
    return this;
  }

  public Label clickAddLabel() {
    addLabelLink.click();
    return this;
  }

  public Label fillLabelInput(final String value) {
    labelInput.sendKeys(value);
    return this;
  }

  public void save() {
    saveLabelLink.click();
  }

  public SelenideElement getLabel(final String name) {
    return labels.find(attribute("value", name));
  }
}
