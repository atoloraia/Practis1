package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NavigationCompany {

    private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");
    public final SelenideElement libraryNavigationItem = $("a[data-test='sidebar-library']");
    public final SelenideElement feedNavigationItem = $("a[data-test='sidebar-feed']");
    private final SelenideElement teamsNavigationItem = $("a[data-test='sidebar-teams']");
    private final SelenideElement usersNavigationItem = $("a[data-test='sidebar-users']");
    private final SelenideElement aiAssessmentItem = $("a[data-test='sidebar-ai-assessment']");
    private final SelenideElement reportsItem = $("a[data-test='sidebar-reports']");
}
