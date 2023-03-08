package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class OverdueModule {
    private final SelenideElement overdueTitle = $("span[data-test*='overdue']");
    private final SelenideElement overdueStatus = $("div[data-test='overdue-check-box-label']");
    private final SelenideElement overdueText = $("div[data-test='overdue-label']");
    private final SelenideElement overdueCheckbox = $("div[data-test='overdue-check-box-view']");
    private final SelenideElement teamOverdueCheckbox = $("div[data-test='overdue-view']");
    private final SelenideElement overdueIcon = $("svg[data-test='overdue-flag-icon']");
}
