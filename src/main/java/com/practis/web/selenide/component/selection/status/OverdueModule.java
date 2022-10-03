package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class OverdueModule {
  private final SelenideElement overdueStatus = $("div[data-test='overdue-check-box-label']");
  private final SelenideElement overdueCheckbox = $("div[data-test='overdue-check-box-view']");
  private final SelenideElement overdueIcon = $("svg[data-test='overdue-flag-icon']");


}
