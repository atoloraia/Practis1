package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LabelModule {

  private final SelenideElement searchField = $("input[data-test='labels-searchbox-field']");
  private final SelenideElement noSelectedText = $("span[data-test='labels-selected-caption']");
  private final SelenideElement selectedAllButton = $("span[data-test='select-all-button']");
  private final ElementsCollection labelRows = $$("div[data-test='label-item-container']");
  private final ElementsCollection labelCheckbox = $$("input[data-test='label-item-checkbox']");
  private final SelenideElement labelName = $("div[data-test='label-item-input-container']");
  private final SelenideElement applyButton = $("button[data-test='apply-button']");
  private final SelenideElement cancelButton = $("button[data-test='cancel-button']");
  private final SelenideElement noLabelsText = $(".sc-dkQkSb");
}
