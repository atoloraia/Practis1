package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioModule {

  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
  private final SelenideElement noSearchResultsIcon = $(".sc-fmBVQj.lmvzIJ");
  private final SelenideElement noSearchResultsText = $(".sc-hgGhFe.linSxz");
}
