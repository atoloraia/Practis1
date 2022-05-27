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

  private final SelenideElement labelIcon = $("div[data-test='sidebar-labels-expand']");
  private final SelenideElement addLabelLink = $("div[data-test='sidebar-labels-add']");
  private final SelenideElement labelInput = $("input.sc-fotPbf.foPnlk.sc-igXhDE.gmkQBx");
  private final SelenideElement saveLabelLink = $("a.sc-nVjpj.clvsrj.sc-cjrQaZ.fIhbAu");
  private final ElementsCollection labels = $$("div.sc-fTxPal.bOPYkZ");

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
    return labels.find(attribute("title", name));
  }
}
