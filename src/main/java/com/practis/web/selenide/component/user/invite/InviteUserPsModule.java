package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InviteUserPsModule {

  private final SelenideElement searchField = $("input[data-test='practisset-searchbox-field']");
  private final SelenideElement noSelectedText = $("span[data-test='practisset-selected-caption']");
  private final SelenideElement dueDatesColumnTitle = $("span[data-test='due-dates-column-title']");
  private final SelenideElement selectedAllButton = $("span[data-test='select-all-button']");
  private final ElementsCollection practisSetRows =
      $$("div[data-test='practisset-item-container']");
  private final ElementsCollection practisSetCheckbox =
      $$("input[data-test='practisset-item-checkbox']");
  private final SelenideElement practisSetName = $("div[data-test='practisset-item-title']");
  private final SelenideElement dueDateValue = $("div[data-test='due-date-value']");
  private final SelenideElement applyButton = $("button[data-test='apply-button']");
  private final SelenideElement cancelButton = $("button[data-test='cancel-button']");
  private final SelenideElement noPractisSetYetText = $(".sc-dkQkSb.iHPkOP");
}
