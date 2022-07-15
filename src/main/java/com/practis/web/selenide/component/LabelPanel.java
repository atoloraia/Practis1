package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LabelPanel {

  private final SelenideElement expandLabelIcon = $("div[data-test='sidebar-labels-expand']");
  private final SelenideElement collapseLabelIcon = $("div[data-test='sidebar-labels-collapse']");

  private final SelenideElement searchLabelField =
      $("input[data-test='sidebar-labels-search-input']");

  private final SelenideElement filterByText = $("p[data-test='sidebar-labels-filter-text']");

  private final SelenideElement noLabelsSelectedText =
      $("div[data-test='sidebar-labels-selected-counter']");
  private final SelenideElement clearLabelsButton = $("div[data-test='sidebar-labels-clear']");

  private final SelenideElement addLabelLink = $("div[data-test='sidebar-labels-add']");
  private final SelenideElement labelInput = $("input[data-test='sidebar-labels-new-input']");
  private final SelenideElement saveButton = $("a[data-test='sidebar-labels-save']");
  private final SelenideElement cancelButton = $("a[data-test='sidebar-labels-cancel']");


  private final ElementsCollection labelRow = $$("div[data-test*='sidebar-labels-item']");
}
