package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LabelModule {

  private final SelenideElement searchField = $("input[data-test='labels-searchbox-field']");
  private final SelenideElement searchFieldIcon = $("div[data-test='labels-searchbox-field-icon']");
  private final SelenideElement selectedText = $("span[data-test='labels-selected-caption']");
  private final SelenideElement selectedAllButton = $("span[data-test='select-all-button']");
  private final SelenideElement unSelectedAllButton = $("span[data-test='unselect-all-button']");
  private final ElementsCollection labelRows = $$("div[data-test='label-item-container']");
  private final ElementsCollection labelCheckbox = $$("input[data-test='label-item-checkbox']");
  private final SelenideElement labelName = $("div[data-test='label-item-input-container']");
  private final SelenideElement applyButton = $("button[data-test='apply-button']");
  private final SelenideElement cancelButton = $("button[data-test='cancel-button']");
  private final SelenideElement noLabelsYetTooltip =
      $("div[data-test='invite-users-new-labels-no-result']");
  private final SelenideElement noLabelsAddedIcon = $(".sc-gWaPPT.epmUKQ");
  private final SelenideElement noLabelsAddedText = $(".sc-cVEVwo.cxBjLK");
  private final SelenideElement noSearchResultImage =
      $("div[data-test='labels-searchbox-empty-result-icon']");
  private final SelenideElement noSearchResultText =
      $("div[data-test='labels-searchbox-empty-result-label']");
  private final SelenideElement disabledStateButton = $(".sc-doMyuq.czFsdI.is-disabled");
}
