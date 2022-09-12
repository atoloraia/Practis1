package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class StatusModule {

  private final SelenideElement statusTitle = $("input[data-test='library-filters-status-title']");

  private final SelenideElement activeStatusCheckbox =
      $("input[data-test='library-active-checkbox']");
  private final SelenideElement activeStatusLabel =
      $("div[data-test='library-active-checkbox-label']");

  private final SelenideElement draftStatusCheckbox = $("div[data-test='library-draft-checkbox']");
  private final SelenideElement draftStatusLabel =
      $("input[data-test='library-draft-checkbox-label']");

  private final SelenideElement archivedStatusCheckbox =
      $("div[data-test='library-archived-checkbox']");
  private final SelenideElement archivedStatusLabel =
      $("input[data-test='library-archived-checkbox-label']");

  private final ElementsCollection status = $$(".sc-iukwUI.djFtvI");
}
