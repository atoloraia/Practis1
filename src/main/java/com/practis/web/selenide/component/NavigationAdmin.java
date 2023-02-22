package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class NavigationAdmin {

    public final SelenideElement companyNavigationItem = $("a[data-test='sidebar-companies']");
    public final SelenideElement adminNavigationItem = $("a[data-test='sidebar-administrators']");
    public final SelenideElement logsNavigationItem = $("a[data-test='sidebar-event-log']");
    public final SelenideElement assessmentNavigationItem =
            $("a[data-test='sidebar-ai-assessment']");
}
