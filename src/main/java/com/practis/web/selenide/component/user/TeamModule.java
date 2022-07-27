package com.practis.web.selenide.component.user;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamModule {

  private final SelenideElement searchField = $("input[data-test='teams-searchbox-field']");
  private final SelenideElement selectedText = $("span[data-test='teams-selected-caption']");
  private final SelenideElement selectedAllButton = $("span[data-test='select-all-button']");
  private final SelenideElement unSelectedAllButton = $("span[data-test='unselect-all-button']");
  private final SelenideElement allMembersTeam = $("div[data-test='all-members-item']");
  private final ElementsCollection teamRows = $$("div[data-test='team-item-container']");
  private final ElementsCollection teamCheckbox = $$("input[data-test='team-item-checkbox']");
  private final SelenideElement teamName = $("div[data-test='team-item-title']");
  private final SelenideElement applyButton = $("button[data-test='apply-button']");
  private final SelenideElement cancelButton = $("button[data-test='cancel-button']");
  private final SelenideElement noSearchResultImage =
      $("div[data-test='teams-searchbox-empty-result-icon']");
  private final SelenideElement noSearchResultText =
      $("div[data-test='teams-searchbox-empty-result-label']");

}
