package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminPage {

  private final SelenideElement adminHeaderText =
      $("div[data-test='administrators-page-page-subtitle']");
  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");

}
