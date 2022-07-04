package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NavigationCompanies {

  public final SelenideElement libraryNavigationItem = $("a[data-test='sidebar-library']");
  public final SelenideElement feedNavigationItem = $("a[data-test='sidebar-feed']");
  private final SelenideElement teamsNavigationItem = $("a[data-test='sidebar-teams']");
  private final SelenideElement usersNavigationItem = $("a[data-test='sidebar-users']");

}
