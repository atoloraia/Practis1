package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LibraryTabs {

    public final SelenideElement practisSetLibraryTab =
            $("a[data-test='library-nav-practis-sets']");
    public final SelenideElement scenarioLibraryTab = $("a[data-test='library-nav-scenarios']");
    public final SelenideElement challengesLibraryTab = $("a[data-test='library-nav-challenges']");
}
